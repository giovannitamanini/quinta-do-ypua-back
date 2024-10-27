package com.pousada.controller;

import com.pousada.dto.AcomodacaoItemDTO;
import com.pousada.service.AcomodacaoItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/acomodacaoitem")
@RequiredArgsConstructor
@Tag(name = "Itens da acomodação")
public class AcomodacaoItemController {

    @Autowired
    private final AcomodacaoItemService acomodacaoItemService;

    @Operation(summary = "Cadastra um item em uma acomodação", method = "POST")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AcomodacaoItemDTO criarAcomodacaoItem(@RequestBody AcomodacaoItemDTO acomodacaoItemDTO) {
        return acomodacaoItemService.criarAcomodacaoItem(acomodacaoItemDTO);
    }

    @Operation(summary = "Atualiza o cadastro de um item da acomodação", method = "PUT")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public AcomodacaoItemDTO atualizarAcomodacaoItem(@RequestBody AcomodacaoItemDTO acomodacaoItemDTO) {
        return acomodacaoItemService.atualizarAcomodacaoItem(acomodacaoItemDTO);
    }

    @Operation(summary = "Faz a exclusão de um item da acomodação", method = "DELETE")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarAcomodacaoItemPorId(
            @PathVariable Long id) {
        acomodacaoItemService.deletarAcomodacaoItemPorId(id);
    }

}
