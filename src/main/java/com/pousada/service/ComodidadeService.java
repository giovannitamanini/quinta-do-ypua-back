package com.pousada.service;

import com.pousada.domain.entity.ComodidadeEntity;
import com.pousada.domain.entity.HospedeEntity;
import com.pousada.domain.repository.ComodidadeRepository;
import com.pousada.dto.ComodidadeDTO;
import com.pousada.dto.HospedeDTO;
import com.pousada.exception.ComodidadeNaoEncontradaException;
import com.pousada.exception.HospedeNaoEncontradoException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComodidadeService {

    private final ModelMapper modelMapper;
    private final ComodidadeRepository comodidadeRepository;

    public ComodidadeService(ModelMapper modelMapper, ComodidadeRepository comodidadeRepository) {
        this.modelMapper = modelMapper;
        this.comodidadeRepository = comodidadeRepository;
    }

    public ComodidadeDTO buscarComodidadePorId(Long id) {
        ComodidadeEntity comodidadeEntity = comodidadeRepository.findById(id)
                .orElseThrow(() -> new ComodidadeNaoEncontradaException("A comodidade com o ID " + id + " não existe."));

        return modelMapper.map(comodidadeEntity, ComodidadeDTO.class);
    }

//    public ComodidadeDTO buscarComodidadePorNome(String nome) {
//        ComodidadeEntity comodidadeEntity = comodidadeRepository.findByNome(nome)
//                .orElseThrow(() -> new ComodidadeNaoEncontradaException("A comodidade com o nome " + nome + " não existe."));
//
//        return modelMapper.map(comodidadeEntity, ComodidadeDTO.class);
//    }

    public List<ComodidadeDTO> buscarTodasComodidades() {
        List<ComodidadeEntity> comodidadeEntities = comodidadeRepository.findAll();

        return comodidadeEntities.stream()
                .map(comodidadeEntity -> modelMapper.map(comodidadeEntity, ComodidadeDTO.class))
                .collect(Collectors.toList());
    }

    public ComodidadeDTO criarItem(ComodidadeDTO comodidadeDTO) {
        ComodidadeEntity comodidadeEntity = modelMapper.map(comodidadeDTO, ComodidadeEntity.class);
        ComodidadeEntity comodidadeEntitySalvo = comodidadeRepository.save(comodidadeEntity);
        return modelMapper.map(comodidadeEntitySalvo, ComodidadeDTO.class);
    }

    public ComodidadeDTO atualizarItem(ComodidadeDTO comodidadeDTO) {
        ComodidadeEntity comodidadeEntity = modelMapper.map(comodidadeDTO, ComodidadeEntity.class);
        ComodidadeEntity comodidadeEntitySalvo = comodidadeRepository.save(comodidadeEntity);
        return modelMapper.map(comodidadeEntitySalvo, ComodidadeDTO.class);
    }

    public void deletarItemPorId(Long id) {
        boolean itemExiste = comodidadeRepository.existsById(id);

        if (itemExiste) {
            comodidadeRepository.deleteById(id);
        } else {
            throw new ComodidadeNaoEncontradaException("O item com o ID " + id + " não existe.");
        }
    }
}
