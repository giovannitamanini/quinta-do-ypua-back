package com.pousada.service;

import com.pousada.domain.entity.ComodidadeEntity;
import com.pousada.domain.entity.HospedeEntity;
import com.pousada.domain.repository.HospedeRepository;
import com.pousada.domain.repository.ReservaRepository;
import com.pousada.dto.ComodidadeDTO;
import com.pousada.dto.HospedeDTO;
import com.pousada.exception.AcomodacaoNaoEncontradaException;
import com.pousada.exception.HospedeNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HospedeService {

    private final ModelMapper modelMapper;
    private final HospedeRepository hospedeRepository;
    private final ReservaRepository reservaRepository;

    public HospedeDTO criarHospede(HospedeDTO hospedeDTO) {
        HospedeEntity hospedeEntity = modelMapper.map(hospedeDTO, HospedeEntity.class);
        HospedeEntity hospedeEntitySalvo = hospedeRepository.save(hospedeEntity);
        return modelMapper.map(hospedeEntitySalvo, HospedeDTO.class);
    }

    public HospedeDTO atualizarHospede(HospedeDTO hospedeDTO) {
        HospedeEntity hospedeEntity = modelMapper.map(hospedeDTO, HospedeEntity.class);
        HospedeEntity hospedeEntitySalvo = hospedeRepository.save(hospedeEntity);
        return modelMapper.map(hospedeEntitySalvo, HospedeDTO.class);
    }

    public void deletarHospedePorId(Integer id) {
        if (hospedeRepository.existsById(id)) {
            if(verificarAssociacaoComReserva(id)){
                throw new DataIntegrityViolationException("Hóspede associado a uma reserva");
            }
            hospedeRepository.deleteById(id);
        } else {
            throw new HospedeNaoEncontradoException("A acomodação com o ID " + id + " não existe.");
        }
    }

    public HospedeDTO buscarHospedePorId(Integer id) {
        HospedeEntity hospedeEntity = hospedeRepository.findById(id)
                .orElseThrow(() -> new HospedeNaoEncontradoException("O hóspede com o ID " + id + " não existe."));

        return modelMapper.map(hospedeEntity, HospedeDTO.class);
    }

    public List<HospedeDTO> buscarHospedesPorNome(String nome) {
        List<HospedeEntity> hospedeEntities = hospedeRepository.findByNome(nome);

        if (hospedeEntities.isEmpty())
            throw new HospedeNaoEncontradoException("Nenhum hóspede com o nome " + nome + " está registrado!");

        return hospedeEntities.stream()
                .map(hospedeEntity -> modelMapper.map(hospedeEntity, HospedeDTO.class))
                .collect(Collectors.toList());
    }

    public List<HospedeDTO> buscarTodosHospedes() {
        List<HospedeEntity> hospedeEntities = hospedeRepository.findAll();

        return hospedeEntities.stream()
                .map(hospedeEntity -> modelMapper.map(hospedeEntity, HospedeDTO.class))
                .collect(Collectors.toList());
    }

    public Page<HospedeDTO> buscarHospedesPaginados(Pageable pageable) {
        Page<HospedeEntity> page = hospedeRepository.findAll(pageable);
        return page.map(hospedeEntity -> modelMapper.map(hospedeEntity, HospedeDTO.class));
    }

    private boolean verificarAssociacaoComReserva(Integer id) {
        return reservaRepository.existsByIdAcomodacao(id);
    }

}
