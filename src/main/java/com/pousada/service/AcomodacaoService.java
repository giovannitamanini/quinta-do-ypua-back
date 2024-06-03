package com.pousada.service;

import com.pousada.domain.entity.AcomodacaoEntity;
import com.pousada.domain.entity.HospedeEntity;
import com.pousada.domain.repository.AcomodacaoRepository;
import com.pousada.dto.AcomodacaoDTO;
import com.pousada.dto.HospedeDTO;
import com.pousada.exception.AcomodacaoNaoEncontradaException;
import com.pousada.exception.HospedeNaoEncontradoException;
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

    public AcomodacaoDTO criarAcomodacao(AcomodacaoDTO acomodacaoDTO) {
        AcomodacaoEntity acomodacaoEntity = modelMapper.map(acomodacaoDTO, AcomodacaoEntity.class);
        AcomodacaoEntity acomodacaoEntitySalva = acomodacaoRepository.save(acomodacaoEntity);
        return modelMapper.map(acomodacaoEntitySalva, AcomodacaoDTO.class);
    }

    public AcomodacaoDTO atualizarAcomodacao(AcomodacaoDTO acomodacaoDTO) {
        AcomodacaoEntity acomodacaoEntity = modelMapper.map(acomodacaoDTO, AcomodacaoEntity.class);
        AcomodacaoEntity acomodacaoEntitySalva = acomodacaoRepository.save(acomodacaoEntity);
        return modelMapper.map(acomodacaoEntitySalva, AcomodacaoDTO.class);
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

    public void deletarAcomodacaoPorId(Integer id) {
        boolean acomodacaoExiste = acomodacaoRepository.existsById(id);

        if (acomodacaoExiste) {
            acomodacaoRepository.deleteById(id);
        } else {
            throw new AcomodacaoNaoEncontradaException("A acomodacao com o ID " + id + " não existe.");
        }
    }

}
