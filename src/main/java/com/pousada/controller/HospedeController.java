package com.pousada.controller;

import com.pousada.dto.HospedeDTO;
import com.pousada.service.HospedeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospedes")
@RequiredArgsConstructor
@Tag(name = "Hóspedes")
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
    public void deletarHospedePorId(@PathVariable Long id) {
        hospedeService.deletarHospedePorId(id);
    }

    @Operation(summary = "Busca hóspede a partir do Id", method = "GET")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HospedeDTO buscarHospedePorId(@PathVariable Long id) {
        return hospedeService.buscarHospedePorId(id);
    }

    @Operation(summary = "Busca hóspedes pelo nome", method = "GET")
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<HospedeDTO> buscarHospedesPorNome(@RequestParam(value = "nome") String nome) {
        return hospedeService.buscarHospedesPorNome(nome);
    }

    @Operation(summary = "Busca todos os hóspedes", method = "GET")
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<HospedeDTO> buscarTodosHospedes() {
        return hospedeService.buscarTodosHospedes();
    }

}
