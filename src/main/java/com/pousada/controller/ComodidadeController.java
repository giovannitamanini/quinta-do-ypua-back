package com.pousada.controller;

import com.pousada.dto.ComodidadeDTO;
import com.pousada.dto.HospedeDTO;
import com.pousada.service.ComodidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comodidade")
@RequiredArgsConstructor
@Tag(name = "Comodidade")
public class ComodidadeController {

    @Autowired
    private final ComodidadeService comodidadeService;

    @Operation(summary = "Busca todas as comodidades", method = "GET")
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ComodidadeDTO> buscarTodosComodidades() {
        return comodidadeService.buscarTodasComodidades();
    }

    @Operation(summary = "Busca comodidade a partir do Id", method = "GET")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ComodidadeDTO buscarComodidadePorId(@PathVariable Long id) {
        return comodidadeService.buscarComodidadePorId(id);
    }

//    @Operation(summary = "Busca comodidade a partir do Nome", method = "GET")
//    @GetMapping("/{nome}")
//    @ResponseStatus(HttpStatus.OK)
//    public ComodidadeDTO buscarComodidadePorNome(@PathVariable String nome) {
//        return comodidadeService.buscarComodidadePorNome(nome);
//    }

    @Operation(summary = "Cadastra um novo item", method = "POST")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ComodidadeDTO criarItem(@RequestBody ComodidadeDTO comodidadeDTO) {
        return comodidadeService.criarItem(comodidadeDTO);
    }

    @Operation(summary = "Atualiza o cadastro de um item", method = "PUT")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ComodidadeDTO atualizarItem(@RequestBody ComodidadeDTO comodidadeDTO) {
        return comodidadeService.atualizarItem(comodidadeDTO);
    }

    @Operation(summary = "Faz a exclusão do cadastro de um item", method = "DELETE")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarItemPorId(@PathVariable Long id) {
        comodidadeService.deletarItemPorId(id);
    }
}
