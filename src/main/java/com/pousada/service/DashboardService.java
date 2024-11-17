package com.pousada.service;

import com.pousada.domain.entity.AcomodacaoEntity;
import com.pousada.domain.entity.ReservaEntity;
import com.pousada.domain.repository.AcomodacaoRepository;
import com.pousada.domain.repository.ReservaRepository;
import com.pousada.dto.ReservasPorAcomodacaoDTO;
import com.pousada.enums.StatusReservaEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private AcomodacaoRepository acomodacaoRepository;

    private List<StatusReservaEnum> listStatusValidos = Arrays.asList(StatusReservaEnum.FINALIZADA, StatusReservaEnum.EM_ANDAMENTO);

    public BigDecimal getReceitaMensal(int mes, int ano) {
        LocalDate start = LocalDate.of(ano, mes, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        List<ReservaEntity> reservas = reservaRepository.findReservasByDataCheckInBetweenAndStatusReservaIn(start, end, listStatusValidos);
        return reservas.stream().map(ReservaEntity::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public double getTaxaOcupacao() {
        LocalDate today = LocalDate.now();
        List<AcomodacaoEntity> acomodacoes = acomodacaoRepository.findAll();
        List<ReservaEntity> reservas = reservaRepository.findReservasEmAndamento(today);
        return (double) reservas.size() / acomodacoes.size() * 100;
    }

    public double getTaxaCancelamento(int mes, int ano) {
        LocalDate start = LocalDate.of(ano, mes, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        List<ReservaEntity> reservas = reservaRepository.findReservasByDataCheckInBetween(start, end);
        if(reservas.isEmpty()){
            return 0;
        }
        long reservasCanceladas = reservaRepository.countReservasCanceladas(start, end);

        return (double) reservasCanceladas / reservas.size() * 100;
    }

    public double getTempoMedioEstadia(int mes, int ano) {
        LocalDate start = LocalDate.of(ano, mes, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        List<ReservaEntity> reservas = reservaRepository.findReservasByDataCheckInBetweenAndStatusReservaIn(start, end, listStatusValidos);

        return reservas.stream()
                .mapToLong(ReservaEntity::getQtdDiarias)
                .average()
                .orElse(0.0);
    }

    public List<ReservasPorAcomodacaoDTO> getReservasPorAcomodacao(int mes, int ano) {
        List<AcomodacaoEntity> acomodacoes = acomodacaoRepository.findAll();
        LocalDate start = LocalDate.of(ano, mes, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        List<ReservaEntity> reservas = reservaRepository.findReservasByDataCheckInBetweenAndStatusReservaIn(start, end, listStatusValidos);
        List<ReservasPorAcomodacaoDTO> listReservasPorAcomodacao = new ArrayList<>();

        for (AcomodacaoEntity acomodacao : acomodacoes) {
            String acomodacaoNome = acomodacao.getNome();
            Integer numeroReservas = (int) reservas.stream()
                    .filter(reserva -> reserva.getIdAcomodacao().equals(acomodacao.getId()))
                    .count();

            ReservasPorAcomodacaoDTO dto = new ReservasPorAcomodacaoDTO(acomodacaoNome, numeroReservas);
            listReservasPorAcomodacao.add(dto);
        }

        return listReservasPorAcomodacao;
    }
}
