package com.pousada.service;

import com.pousada.domain.entity.AcomodacaoItemEntity;
import com.pousada.domain.repository.AcomodacaoItemRepository;
import com.pousada.dto.AcomodacaoItemDTO;
import com.pousada.exception.AcomodacaoItemNaoEncontradaException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AcomodacaoItemService {

    private final ModelMapper modelMapper;
    private final AcomodacaoItemRepository acomodacaoItemRepository;

    public AcomodacaoItemService(ModelMapper modelMapper, AcomodacaoItemRepository acomodacaoItemRepository) {
        this.modelMapper = modelMapper;
        this.acomodacaoItemRepository = acomodacaoItemRepository;
    }

    public AcomodacaoItemDTO criarAcomodacaoItem(AcomodacaoItemDTO acomodacaoItemDTO) {
        AcomodacaoItemEntity acomodacaoItemEntity = modelMapper.map(acomodacaoItemDTO, AcomodacaoItemEntity.class);
        AcomodacaoItemEntity acomodacaoItemEntitySalva = acomodacaoItemRepository.save(acomodacaoItemEntity);
        return modelMapper.map(acomodacaoItemEntitySalva, AcomodacaoItemDTO.class);
    }

    public AcomodacaoItemDTO atualizarAcomodacaoItem(AcomodacaoItemDTO acomodacaoItemDTO) {
        AcomodacaoItemEntity acomodacaoItemEntity = modelMapper.map(acomodacaoItemDTO, AcomodacaoItemEntity.class);
        AcomodacaoItemEntity acomodacaoItemEntitySalva = acomodacaoItemRepository.save(acomodacaoItemEntity);
        return modelMapper.map(acomodacaoItemEntitySalva, AcomodacaoItemDTO.class);
    }

    public void deletarAcomodacaoItemPorId(Long id) {
        boolean acomodacaoItemExiste = acomodacaoItemRepository.existsById(id);

        if (acomodacaoItemExiste) {
            acomodacaoItemRepository.deleteById(id);
        } else {
            throw new AcomodacaoItemNaoEncontradaException("A acomodação item com o ID " + id + " não existe.");
        }
    }
}
