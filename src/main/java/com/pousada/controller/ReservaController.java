package com.pousada.controller;

import com.pousada.dto.ReservaDTO;
import com.pousada.exception.AcomodacaoOcupadaException;
import com.pousada.exception.ReservaEmAndamentoOuFinalizadaException;
import com.pousada.service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> criarReserva(@RequestBody ReservaDTO reserva) {
        try {
            reservaService.criarReserva(reserva);
            return ResponseEntity.status(HttpStatus.CREATED).body("Reserva criada com sucesso");
        } catch (AcomodacaoOcupadaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("A acomodação requisitada já possui reserva no período solicitado!");
        }
    }

    @Operation(summary = "Atualiza o cadastro de uma reserva", method = "PUT")
    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarReserva(@RequestBody ReservaDTO reservaDTO) {
        try {
            ReservaDTO updatedReserva = reservaService.atualizarReserva(reservaDTO);
            return ResponseEntity.ok("Reserva atualizada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar reserva!");
        }
    }

    @Operation(summary = "Faz a exclusão do cadastro de uma reserva", method = "DELETE")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deletarItemPorId(@PathVariable Integer id) {
        try {
            reservaService.deletarReservaPorId(id);
            return ResponseEntity.noContent().build();
        } catch (ReservaEmAndamentoOuFinalizadaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Não é possível excluir uma reserva finalizada ou em andamento!");
        }
    }

    @Operation(summary = "Busca uma reserva a partir do ID", method = "GET")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ReservaDTO> buscarReservaPorId(@PathVariable Integer id) {
        ReservaDTO reservaDTO = reservaService.buscarReservaPorId(id);
        if (reservaDTO != null) {
            return ResponseEntity.ok(reservaDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Busca todas as reservas", method = "GET")
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ReservaDTO> buscarTodasReservas() {
        return reservaService.buscarTodasReservas();
    }

    @Operation(summary = "Busca todas as comodidades com paginação", method = "GET")
    @GetMapping("/paginated")
    @ResponseStatus(HttpStatus.OK)
    public Page<ReservaDTO> buscarReservasPaginadas(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return reservaService.buscarReservasPaginadas(pageable);
    }

    @Operation(summary = "Busca todas as reservas que estão com status 'EM_ESPERA'", method = "GET")
    @GetMapping("/em_espera")
    @ResponseStatus(HttpStatus.OK)
    public List<ReservaDTO> buscarReservasEmEspera() {
        return reservaService.buscarReservasEmEspera();
    }
}
