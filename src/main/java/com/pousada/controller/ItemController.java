package com.pousada.controller;

import com.pousada.dto.ItemDTO;
import com.pousada.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
@Tag(name = "Item")
public class ItemController {

    @Autowired
    private final ItemService itemService;

    @Operation(summary = "Cadastra um novo item", method = "POST")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDTO criarItem(@RequestBody ItemDTO itemDTO) {
        return itemService.criarItem(itemDTO);
    }

    @Operation(summary = "Atualiza o cadastro de um item", method = "PUT")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDTO atualizarItem(@RequestBody ItemDTO itemDTO) {
        return itemService.atualizarItem(itemDTO);
    }

    @Operation(summary = "Faz a exclusão do cadastro de um item", method = "DELETE")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarItemPorId(@PathVariable Long id) {
        itemService.deletarItemPorId(id);
    }
}
