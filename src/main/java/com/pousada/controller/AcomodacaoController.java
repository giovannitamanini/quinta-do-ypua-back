package com.pousada.controller;

import com.pousada.dto.AcomodacaoDTO;
import com.pousada.service.AcomodacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "Acomodações")
public class AcomodacaoController {

    @Autowired
    private final AcomodacaoService acomodacaoService;

    @Operation(summary = "Busca acomodações da pousada a partir do Id da acomodação", method = "GET")
    @GetMapping("/acomodacoes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AcomodacaoDTO buscarAcomodacaoPorId(@PathVariable Integer id) {
        return acomodacaoService.buscarAcomodacaoPorId(id);
    }

    @Operation(summary = "Busca todas as acomodações da pousada", method = "GET")
    @GetMapping("/acomodacoes")
    @ResponseStatus(HttpStatus.OK)
    public List<AcomodacaoDTO> buscarTodasAcomodacoes() {
       return acomodacaoService.buscarTodasAcomodacoes();
    }

}
