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
@RequestMapping("/acomodacao")
@RequiredArgsConstructor
@Tag(name = "Acomodação")
public class AcomodacaoController {

    @Autowired
    private final AcomodacaoService acomodacaoService;

    @Operation(summary = "Cadastra uma nova acomodação", method = "POST")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AcomodacaoDTO criarAcomodacao(@RequestBody AcomodacaoDTO acomodacaoDTO) {
        return acomodacaoService.criarAcomodacao(acomodacaoDTO);
    }

    @Operation(summary = "Atualiza o cadastro de uma acomodação", method = "PUT")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public AcomodacaoDTO atualizarAcomodacao(@RequestBody AcomodacaoDTO acomodacaoDTO) {
        return acomodacaoService.atualizarAcomodacao(acomodacaoDTO);
    }

    @Operation(summary = "Faz a exclusão do cadastro de uma acomodação", method = "DELETE")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarAcomodacaoPorId(@PathVariable Integer id) {
        acomodacaoService.deletarAcomodacaoPorId(id);
    }

    @Operation(summary = "Busca acomodação a partir do Id", method = "GET")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AcomodacaoDTO buscarAcomodacaoPorId(@PathVariable Integer id) {
        return acomodacaoService.buscarAcomodacaoPorId(id);
    }

    @Operation(summary = "Busca todas as acomodações", method = "GET")
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AcomodacaoDTO> buscarTodasAcomodacoes() {
       return acomodacaoService.buscarTodasAcomodacoes();
    }

}
