package com.pousada.controller;

import com.pousada.dto.ReservaDTO;
import com.pousada.service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reserva")
@RequiredArgsConstructor
@Tag(name = "Reserva")
public class ReservaController {

    private final ReservaService reservaService;

    @Operation(summary = "Cadastra uma nova reserva", method = "POST")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservaDTO criarReserva(@RequestBody ReservaDTO reserva) {
        return reservaService.criarReserva(reserva);
    }

    @Operation(summary = "Atualiza o cadastro de uma reserva", method = "PUT")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ReservaDTO atualizarReserva(@RequestBody ReservaDTO reservaDTO) {
        return reservaService.atualizarReserva(reservaDTO);
    }

    @Operation(summary = "Faz a exclusão do cadastro de uma reserva", method = "DELETE")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarReservaPorId(@PathVariable Long id) {
        reservaService.deletarReservaPorId(id);
    }

    @Operation(summary = "Busca uma reserva a partir do ID", method = "GET")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReservaDTO buscarReservaPorId(@PathVariable Long id) {
        return reservaService.buscarReservaPorId(id);
    }

    @Operation(summary = "Busca todas as reservas", method = "GET")
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<ReservaDTO> buscarTodasReservas() {
        return reservaService.buscarTodasReservas();
    }

    @Operation(summary = "Busca todas as reservas que estão com status 'EM_ESPERA'", method = "GET")
    @GetMapping("/em_espera")
    @ResponseStatus(HttpStatus.OK)
    public List<ReservaDTO> buscarReservasEmEspera() {
        return reservaService.buscarReservasEmEspera();
    }
}
