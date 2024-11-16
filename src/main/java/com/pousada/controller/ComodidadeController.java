package com.pousada.controller;

import com.pousada.dto.ComodidadeDTO;
import com.pousada.enums.TipoComodidadeEnum;
import com.pousada.service.ComodidadeService;
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
import org.springframework.web.server.ResponseStatusException;

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
    public ComodidadeDTO buscarComodidadePorId(@PathVariable Integer id) {
        return comodidadeService.buscarComodidadePorId(id);
    }

    @Operation(summary = "Busca comodidade a partir do Nome", method = "GET")
    @GetMapping("/{descricao}")
    @ResponseStatus(HttpStatus.OK)
    public ComodidadeDTO buscarComodidadePorDescricao(@PathVariable String descricao) {
        return comodidadeService.buscarComodidadePorDescricao(descricao);
    }

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
    public ResponseEntity<String> deletarItemPorId(@PathVariable Integer id) {
        try {
            comodidadeService.deletarItemPorId(id);
            return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Não é possível excluir uma comodidade que está associada a uma acomodação.");
        }
    }

    @Operation(summary = "Busca todas as comodidades com paginação", method = "GET")
    @GetMapping("/paginated")
    @ResponseStatus(HttpStatus.OK)
    public Page<ComodidadeDTO> buscarComodidadesPaginadas(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return comodidadeService.buscarComodidadesPaginadas(pageable);
    }

    @Operation(summary = "Busca comodidades com filtros opcionais", method = "GET")
    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public Page<ComodidadeDTO> buscarComodidadesComFiltro(@RequestParam(required = false) String descricao,
                                                          @RequestParam(required = false) String tipo,
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "20") int size) {
        TipoComodidadeEnum tipoEnum = null;

        if (tipo != null && !tipo.isEmpty()) {
            try {
                tipoEnum = TipoComodidadeEnum.valueOf(tipo);
            } catch (IllegalArgumentException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo de comodidade inválido");
            }
        }

        Pageable pageable = PageRequest.of(page, size);

        return comodidadeService.buscarComodidadesComFiltro(descricao, tipoEnum, pageable);
    }
}
