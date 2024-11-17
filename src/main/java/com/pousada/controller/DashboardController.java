package com.pousada.controller;

import com.pousada.dto.ReservasPorAcomodacaoDTO;
import com.pousada.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/receita")
    public ResponseEntity<BigDecimal> getReceitaMensal(@RequestParam int mes, @RequestParam int ano) {
        return ResponseEntity.ok(dashboardService.getReceitaMensal(mes, ano));
    }

    @GetMapping("/taxa-ocupacao")
    public ResponseEntity<Double> getTaxaOcupacao() {
        return ResponseEntity.ok(dashboardService.getTaxaOcupacao());
    }

    @GetMapping("/taxa-cancelamento")
    public ResponseEntity<Double> getTaxaCancelamento(@RequestParam int mes, @RequestParam int ano) {
        return ResponseEntity.ok(dashboardService.getTaxaCancelamento(mes, ano));
    }

    @GetMapping("/tempo-medio-estadia")
    public ResponseEntity<Double> getTempoMedioEstadia(@RequestParam int mes, @RequestParam int ano) {
        return ResponseEntity.ok(dashboardService.getTempoMedioEstadia(mes, ano));
    }

    @GetMapping("/reservas-por-acomodacao")
    public ResponseEntity<List<ReservasPorAcomodacaoDTO>> getReservasPorAcomodacao(@RequestParam int mes, @RequestParam int ano) {
        List<ReservasPorAcomodacaoDTO> reservasPorAcomodacao = dashboardService.getReservasPorAcomodacao(mes, ano);
        return ResponseEntity.ok(reservasPorAcomodacao);
    }

}
