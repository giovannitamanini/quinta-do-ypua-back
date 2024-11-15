package com.pousada.controller;

import com.pousada.dto.ComodidadeDTO;
import com.pousada.dto.HospedeDTO;
import com.pousada.service.HospedeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospede")
@RequiredArgsConstructor
@Tag(name = "Hóspede")
public class HospedeController {

    private final HospedeService hospedeService;

    @Operation(summary = "Cadastra um novo hóspede", method = "POST")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HospedeDTO criarHospede(@RequestBody HospedeDTO hospedeDTO) {
        return hospedeService.criarHospede(hospedeDTO);
    }

    @Operation(summary = "Atualiza o cadastro de um hóspede", method = "PUT")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HospedeDTO atualizarHospede(@RequestBody HospedeDTO hospedeDTO) {
        return hospedeService.atualizarHospede(hospedeDTO);
    }

    @Operation(summary = "Faz a exclusão do cadastro de um hóspede", method = "DELETE")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deletarHospedePorId(@PathVariable Integer id) {
        try {
            hospedeService.deletarHospedePorId(id);
            return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Não é possível excluir um hóspede que está associado a uma reserva.");
        }
    }

    @Operation(summary = "Busca hóspede a partir do Id", method = "GET")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HospedeDTO buscarHospedePorId(@PathVariable Integer id) {
        return hospedeService.buscarHospedePorId(id);
    }

    @Operation(summary = "Busca hóspedes pelo nome", method = "GET")
    @GetMapping("/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public List<HospedeDTO> buscarHospedesPorNome(@RequestParam(value = "nome") String nome) {
        return hospedeService.buscarHospedesPorNome(nome);
    }

    @Operation(summary = "Busca todos os hóspedes", method = "GET")
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<HospedeDTO> buscarTodosHospedes() {
        return hospedeService.buscarTodosHospedes();
    }

    @Operation(summary = "Busca todas as comodidades com paginação", method = "GET")
    @GetMapping("/paginated")
    @ResponseStatus(HttpStatus.OK)
    public Page<HospedeDTO> buscarHospedesPaginados(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return hospedeService.buscarHospedesPaginados(pageable);
    }

}
