package com.pousada.controller;

import com.pousada.dto.AcomodacaoDTO;
import com.pousada.service.AcomodacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> deletarAcomodacaoPorId(@PathVariable Integer id) {
        try {
            acomodacaoService.deletarAcomodacaoPorId(id);
            return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Não é possível excluir uma acomodação que está associada a uma reserva.");
        }
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

    @Operation(summary = "Busca todas as acomodações com paginação", method = "GET")
    @GetMapping("/paginated")
    @ResponseStatus(HttpStatus.OK)
    public Page<AcomodacaoDTO> buscarAcomodacoesPaginadas(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return acomodacaoService.buscarAcomodacoesPaginadas(pageable);
    }

}
