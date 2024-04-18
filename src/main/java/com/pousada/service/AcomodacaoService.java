package com.pousada.service;

import com.pousada.domain.entity.AcomodacaoEntity;
import com.pousada.domain.repository.AcomodacaoRepository;
import com.pousada.dto.AcomodacaoDTO;
import com.pousada.exception.AcomodacaoNaoEncontradaException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AcomodacaoService {

    private final ModelMapper modelMapper;
    private final AcomodacaoRepository acomodacaoRepository;

    public AcomodacaoService(ModelMapper modelMapper, AcomodacaoRepository acomodacaoRepository) {
        this.modelMapper = modelMapper;
        this.acomodacaoRepository = acomodacaoRepository;
    }

    public AcomodacaoDTO buscarAcomodacaoPorId(Integer id) {
        AcomodacaoEntity acomodacaoEntity = acomodacaoRepository.findById(id)
                .orElseThrow(() -> new AcomodacaoNaoEncontradaException("A acomodação com ID " + id + " não existe."));

        return modelMapper.map(acomodacaoEntity, AcomodacaoDTO.class);
    }

    public List<AcomodacaoDTO> buscarTodasAcomodacoes() {
        List<AcomodacaoEntity> acomodacaoEntities = acomodacaoRepository.findAll();

        if (acomodacaoEntities.isEmpty())
            throw new AcomodacaoNaoEncontradaException("Nenhuma acomodação está registrada!");

        List<AcomodacaoDTO> acomodacaoDTOs = acomodacaoEntities.stream()
                .map(acomodacaoEntity -> modelMapper.map(acomodacaoEntity, AcomodacaoDTO.class))
                .collect(Collectors.toList());

        return acomodacaoDTOs;
    }

}
