package com.pousada.service;

import com.pousada.domain.entity.AcomodacaoEntity;
import com.pousada.domain.entity.ComodidadeEntity;
import com.pousada.domain.repository.AcomodacaoRepository;
import com.pousada.domain.repository.ComodidadeRepository;
import com.pousada.dto.AcomodacaoDTO;
import com.pousada.dto.ComodidadeDTO;
import com.pousada.enums.TipoComodidadeEnum;
import com.pousada.exception.ComodidadeNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ComodidadeService {

    private final ModelMapper modelMapper;
    private final ComodidadeRepository comodidadeRepository;
    private final AcomodacaoRepository acomodacaoRepository;

    public ComodidadeDTO buscarComodidadePorId(Integer id) {
        ComodidadeEntity comodidadeEntity = comodidadeRepository.findById(id)
                .orElseThrow(() -> new ComodidadeNaoEncontradaException("A comodidade com o ID " + id + " não existe."));

        return modelMapper.map(comodidadeEntity, ComodidadeDTO.class);
    }

    public ComodidadeDTO buscarComodidadePorDescricao(String descricao) {
        ComodidadeEntity comodidadeEntity = comodidadeRepository.findByDescricao(descricao)
                .orElseThrow(() -> new ComodidadeNaoEncontradaException("A comodidade com a descrição " + descricao + " não existe."));

        return modelMapper.map(comodidadeEntity, ComodidadeDTO.class);
    }

    public List<ComodidadeDTO> buscarTodasComodidades() {
        List<ComodidadeEntity> comodidadeEntities = comodidadeRepository.findAll();

        return comodidadeEntities.stream()
                .map(comodidadeEntity -> modelMapper.map(comodidadeEntity, ComodidadeDTO.class))
                .collect(Collectors.toList());
    }

    public ComodidadeDTO criarItem(ComodidadeDTO comodidadeDTO) {
        ComodidadeEntity comodidadeEntity = modelMapper.map(comodidadeDTO, ComodidadeEntity.class);
        comodidadeEntity.setAcomodacoes(Collections.emptyList());
        ComodidadeEntity comodidadeEntitySalvo = comodidadeRepository.save(comodidadeEntity);
        return modelMapper.map(comodidadeEntitySalvo, ComodidadeDTO.class);
    }

    public ComodidadeDTO atualizarItem(ComodidadeDTO comodidadeDTO) {
        ComodidadeEntity comodidadeEntity = modelMapper.map(comodidadeDTO, ComodidadeEntity.class);
        ComodidadeEntity comodidadeEntitySalvo = comodidadeRepository.save(comodidadeEntity);
        return modelMapper.map(comodidadeEntitySalvo, ComodidadeDTO.class);
    }

    public void deletarItemPorId(Integer id) {
        Optional<ComodidadeEntity> comodidade = comodidadeRepository.findById(id);
        if (comodidade.isPresent()) {
            if(!comodidade.get().getAcomodacoes().isEmpty()){
                throw new DataIntegrityViolationException("Comodidade associada a uma reserva");
            }
            comodidadeRepository.deleteById(id);
        } else {
            throw new ComodidadeNaoEncontradaException("O item com o ID " + id + " não existe.");
        }
    }

    public Page<ComodidadeDTO> buscarComodidadesPaginadas(Pageable pageable) {
        Page<ComodidadeEntity> page = comodidadeRepository.findAll(pageable);
        return page.map(comodidadeEntity -> modelMapper.map(comodidadeEntity, ComodidadeDTO.class));
    }

    public Page<ComodidadeDTO> buscarComodidadesComFiltro(String descricao, TipoComodidadeEnum tipo, Pageable pageable) {
        Page<ComodidadeEntity> comodidadeEntities;

        if (descricao != null && !descricao.isEmpty() && tipo != null) {
            comodidadeEntities = comodidadeRepository.findByDescricaoAndTipo(descricao, tipo, pageable);
        } else if (descricao != null && !descricao.isEmpty()) {
            comodidadeEntities = comodidadeRepository.findByDescricao(descricao, pageable);
        } else if (tipo != null) {
            comodidadeEntities = comodidadeRepository.findByTipo(tipo, pageable);
        } else {
            comodidadeEntities = comodidadeRepository.findAll(pageable);
        }

        return comodidadeEntities.map(comodidadeEntity -> modelMapper.map(comodidadeEntity, ComodidadeDTO.class));
    }
}
