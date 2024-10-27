package com.pousada.service;

import com.pousada.domain.entity.ItemEntity;
import com.pousada.domain.repository.ItemRepository;
import com.pousada.dto.ItemDTO;
import com.pousada.exception.ItemNaoEncontradoException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ModelMapper modelMapper;
    private final ItemRepository itemRepository;

    public ItemService(ModelMapper modelMapper, ItemRepository itemRepository) {
        this.modelMapper = modelMapper;
        this.itemRepository = itemRepository;
    }

    public ItemDTO criarItem(ItemDTO itemDTO) {
        ItemEntity itemEntity = modelMapper.map(itemDTO, ItemEntity.class);
        ItemEntity itemEntitySalvo = itemRepository.save(itemEntity);
        return modelMapper.map(itemEntitySalvo, ItemDTO.class);
    }

    public ItemDTO atualizarItem(ItemDTO itemDTO) {
        ItemEntity itemEntity = modelMapper.map(itemDTO, ItemEntity.class);
        ItemEntity itemEntitySalvo = itemRepository.save(itemEntity);
        return modelMapper.map(itemEntitySalvo, ItemDTO.class);
    }

    public void deletarItemPorId(Long id) {
        boolean itemExiste = itemRepository.existsById(id);

        if (itemExiste) {
            itemRepository.deleteById(id);
        } else {
            throw new ItemNaoEncontradoException("O item com o ID " + id + " não existe.");
        }
    }
}
