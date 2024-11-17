package com.pousada.service;

import com.pousada.domain.entity.ReservaEntity;
import com.pousada.domain.repository.AcomodacaoRepository;
import com.pousada.domain.repository.ReservaRepository;
import com.pousada.dto.ReservaDTO;
import com.pousada.enums.StatusReservaEnum;
import com.pousada.exception.AcomodacaoOcupadaException;
import com.pousada.exception.ReservaEmAndamentoOuFinalizadaException;
import com.pousada.exception.ReservaNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReservaService {

    private final ModelMapper modelMapper;
    private final ReservaRepository reservaRepository;
    private final AcomodacaoRepository acomodacaoRepository;

    public ReservaDTO criarReserva(ReservaDTO reservaDTO) {
        if (existeReservaNoPeriodo(reservaDTO)) {
            throw new AcomodacaoOcupadaException("A acomodação requisitada já possui reserva no período solicitado!");
        }
        ReservaEntity reservaEntity = modelMapper.map(reservaDTO, ReservaEntity.class);
        ReservaEntity reservaEntitySalva = reservaRepository.save(reservaEntity);

        return modelMapper.map(reservaEntitySalva, ReservaDTO.class);
    }

    public ReservaDTO atualizarReserva(ReservaDTO reservaDTO) {
        ReservaEntity reservaEntity = modelMapper.map(reservaDTO, ReservaEntity.class);
        ReservaEntity reservaEntitySalva = reservaRepository.save(reservaEntity);
        return modelMapper.map(reservaEntitySalva, ReservaDTO.class);
    }

    public void deletarReservaPorId(Integer id) {
        if (reservaRepository.existsById(id)) {
            ReservaEntity reserva = reservaRepository.findById(id)
                    .orElseThrow(() -> new ReservaNaoEncontradaException("A reserva com o ID " + id + " não existe."));

            if (Arrays.asList("EM_ANDAMENTO", "FINALIZADA").contains(String.valueOf(reserva.getStatusReserva()))) {
                throw new ReservaEmAndamentoOuFinalizadaException("Não é possível excluir uma reserva finalizada ou em andamento.");
            }

            reservaRepository.deleteById(id);
        } else {
            throw new ReservaNaoEncontradaException("A reserva com o ID " + id + " não existe.");
        }
    }

    public ReservaDTO buscarReservaPorId(Integer id) {
        ReservaEntity reservaEntity = reservaRepository.findById(id)
                .orElseThrow(() -> new ReservaNaoEncontradaException("A reserva com o ID " + id + " não existe."));

        return modelMapper.map(reservaEntity, ReservaDTO.class);
    }

    public List<ReservaDTO> buscarTodasReservas() {
        List<ReservaEntity> reservaEntities = reservaRepository.findAll();

        return reservaEntities.stream()
                .map(reservaEntity -> modelMapper.map(reservaEntity, ReservaDTO.class))
                .collect(Collectors.toList());
    }

    public Page<ReservaDTO> buscarReservasPaginadas(Pageable pageable) {
        Page<ReservaEntity> page = reservaRepository.findAll(pageable);
        return page.map(reservaEntity -> modelMapper.map(reservaEntity, ReservaDTO.class));
    }

    public List<ReservaDTO> buscarReservasEmEspera() {
        List<ReservaEntity> reservaEntities = reservaRepository.buscarReservasEmEspera();

        if (reservaEntities.isEmpty())
            throw new ReservaNaoEncontradaException("Nenhuma reserva em espera!");

        return reservaEntities.stream()
                .map(reservaEntity -> modelMapper.map(reservaEntity, ReservaDTO.class))
                .collect(Collectors.toList());
    }

    public Page<ReservaDTO> buscarReservasComFiltros(String acomodacao, StatusReservaEnum statusReserva, Pageable pageable) {
        Page<ReservaEntity> page;

        System.out.println("acomodacao " + acomodacao);
        System.out.println("statusReserva " + statusReserva);

        if (acomodacao != null && !acomodacao.equals("Todas") && statusReserva != null) {
            Integer idAcomodacao = acomodacaoRepository.findByNome(acomodacao).getId();
            System.out.println("idAcomodacao " + idAcomodacao);
            page = reservaRepository.buscarComFiltros(idAcomodacao, statusReserva, pageable);
        } else if (acomodacao != null && !acomodacao.equals("Todas")) {
            Integer idAcomodacao = acomodacaoRepository.findByNome(acomodacao).getId();
            System.out.println("idAcomodacao2 " + idAcomodacao);
            page = reservaRepository.findByIdAcomodacao(idAcomodacao, pageable);
        } else if (statusReserva != null) {
            page = reservaRepository.findByStatusReserva(statusReserva, pageable);
        } else {
            page = reservaRepository.findAll(pageable);
        }

        return page.map(ReservaEntity -> modelMapper.map(ReservaEntity, ReservaDTO.class));
    }

    public boolean existeReservaNoPeriodo(ReservaDTO novaReserva) {
        ReservaEntity reservaEntity = reservaRepository.buscarReservaPorAcomodacaoEPeriodo(
                novaReserva.getIdAcomodacao(),
                novaReserva.getDataCheckIn(),
                novaReserva.getDataCheckOut()
        );

        if (reservaEntity == null){
            return false;
        }

        return reservaEntity.getStatusReserva() != StatusReservaEnum.EM_ANDAMENTO;
    }

}
