package com.pousada.service;

import com.pousada.domain.entity.HospedeEntity;
import com.pousada.domain.repository.HospedeRepository;
import com.pousada.dto.HospedeDTO;
import com.pousada.exception.HospedeNaoEncontradoException;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HospedeService {

    private final ModelMapper modelMapper;
    private final HospedeRepository hospedeRepository;

    public HospedeService(ModelMapper modelMapper, HospedeRepository hospedeRepository) {
        this.modelMapper = modelMapper;
        this.hospedeRepository = hospedeRepository;
    }

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

    public HospedeDTO buscarHospedePorId(Long id) {
        HospedeEntity hospedeEntity = hospedeRepository.findById(id)
                .orElseThrow(() -> new HospedeNaoEncontradoException("O hóspede com o ID " + id + " não existe."));

        return modelMapper.map(hospedeEntity, HospedeDTO.class);
    }

    public List<HospedeDTO> buscarHospedesPorNome(String nome) {
        List<HospedeEntity> hospedeEntities = hospedeRepository.buscarHospedesPorNome(nome);

        if (hospedeEntities.isEmpty())
            throw new HospedeNaoEncontradoException("Nenhum hóspede com o nome " + nome + " está registrado!");

        List<HospedeDTO> hospedeDTOs = hospedeEntities.stream()
                .map(hospedeEntity -> modelMapper.map(hospedeEntity, HospedeDTO.class))
                .collect(Collectors.toList());

        return hospedeDTOs;
    }

    public List<HospedeDTO> buscarTodosHospedes() {
        List<HospedeEntity> hospedeEntities = hospedeRepository.findAll();

//        if (hospedeEntities.isEmpty())
//            throw new HospedeNaoEncontradoException("Nenhum hóspede está registrado!");

        List<HospedeDTO> hospedeDTOs = hospedeEntities.stream()
                .map(hospedeEntity -> modelMapper.map(hospedeEntity, HospedeDTO.class))
                .collect(Collectors.toList());

        return hospedeDTOs;
    }

//    public ResponseEntity<Object> deletarHospedePorId(Long id) {
//        boolean hospedeExiste = hospedeRepository.existsById(id);
//
//        if (hospedeExiste) {
//            hospedeRepository.deleteById(id);
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.notFound().build();
//    }

    public void deletarHospedePorId(Long id) {
        boolean hospedeExiste = hospedeRepository.existsById(id);

        if (hospedeExiste) {
            hospedeRepository.deleteById(id);
        } else {
            throw new HospedeNaoEncontradoException("O hóspede com o ID " + id + " não existe.");
        }
    }


}
