package com.pousada.service;

import com.pousada.domain.entity.ReservaEntity;
import com.pousada.domain.repository.AcomodacaoRepository;
import com.pousada.domain.repository.ReservaRepository;
import com.pousada.dto.ReservaDTO;
import com.pousada.enums.StatusReservaEnum;
import com.pousada.exception.AcomodacaoOcupadaException;
import com.pousada.exception.ReservaNaoEncontradaException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaService {

    private final ModelMapper modelMapper;
    private final ReservaRepository reservaRepository;
    private final AcomodacaoRepository acomodacaoRepository;

    public ReservaService(ModelMapper modelMapper, ReservaRepository reservaRepository, AcomodacaoRepository acomodacaoRepository) {
        this.modelMapper = modelMapper;
        this.reservaRepository = reservaRepository;
        this.acomodacaoRepository = acomodacaoRepository;
    }

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

    public void deletarReservaPorId(Long id) {
        boolean reservaExiste = reservaRepository.existsById(id);

        if (reservaExiste) {
            reservaRepository.deleteById(id);
        } else {
            throw new ReservaNaoEncontradaException("A reserva com o ID " + id + " não existe.");
        }
    }

    public ReservaDTO buscarReservaPorId(Long id) {
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

    public List<ReservaDTO> buscarReservasEmEspera() {
        List<ReservaEntity> reservaEntities = reservaRepository.buscarReservasEmEspera();

        if (reservaEntities.isEmpty())
            throw new ReservaNaoEncontradaException("Nenhuma reserva em espera!");

        return reservaEntities.stream()
                .map(reservaEntity -> modelMapper.map(reservaEntity, ReservaDTO.class))
                .collect(Collectors.toList());
    }

    private boolean existeReservaNoPeriodo(ReservaDTO novaReserva) {
        ReservaEntity reservaEntity = reservaRepository.buscarReservaPorAcomodacaoEPeriodo(
                novaReserva.getIdAcomodacao(),
                novaReserva.getDataCheckIn(),
                novaReserva.getDataCheckOut()
        );

        if (reservaEntity == null)
            return false;

        return reservaEntity.getStatusReserva() != StatusReservaEnum.EM_ANDAMENTO;
    }

}
