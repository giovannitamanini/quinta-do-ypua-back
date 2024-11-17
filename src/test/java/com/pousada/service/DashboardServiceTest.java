package com.pousada.service;

import com.pousada.domain.entity.AcomodacaoEntity;
import com.pousada.domain.entity.ReservaEntity;
import com.pousada.domain.repository.AcomodacaoRepository;
import com.pousada.domain.repository.ReservaRepository;
import com.pousada.dto.ReservasPorAcomodacaoDTO;
import com.pousada.enums.StatusReservaEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DashboardServiceTest {

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private AcomodacaoRepository acomodacaoRepository;

    @InjectMocks
    private DashboardService dashboardService;

    private AcomodacaoEntity acomodacao;
    private ReservaEntity reserva1, reserva2;

    @BeforeEach
    public void setup() {
        acomodacao = new AcomodacaoEntity();
        acomodacao.setId(1);
        acomodacao.setNome("Acomodação 1");

        reserva1 = new ReservaEntity();
        reserva1.setIdAcomodacao(1);
        reserva1.setValorTotal(BigDecimal.valueOf(100));
        reserva1.setDataCheckIn(LocalDate.of(2024, 11, 1));
        reserva1.setDataCheckOut(LocalDate.of(2024, 11, 5));
        reserva1.setStatusReserva(StatusReservaEnum.FINALIZADA);
        reserva1.setQtdDiarias(4);

        reserva2 = new ReservaEntity();
        reserva2.setIdAcomodacao(1);
        reserva2.setValorTotal(BigDecimal.valueOf(150));
        reserva2.setDataCheckIn(LocalDate.of(2024, 11, 10));
        reserva2.setDataCheckOut(LocalDate.of(2024, 11, 15));
        reserva2.setStatusReserva(StatusReservaEnum.EM_ANDAMENTO);
        reserva2.setQtdDiarias(5);
    }

    @Test
    public void testGetReceitaMensal() {
        LocalDate start = LocalDate.of(2024, 11, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        List<ReservaEntity> reservas = Arrays.asList(reserva1, reserva2);

        when(reservaRepository.findReservasByDataCheckInBetweenAndStatusReservaIn(start, end, Arrays.asList(StatusReservaEnum.FINALIZADA, StatusReservaEnum.EM_ANDAMENTO)))
                .thenReturn(reservas);

        BigDecimal receita = dashboardService.getReceitaMensal(11, 2024);

        assertEquals(BigDecimal.valueOf(250), receita);
    }

    @Test
    public void testGetTaxaOcupacao() {
        List<AcomodacaoEntity> acomodacoes = Arrays.asList(acomodacao);
        List<ReservaEntity> reservas = Arrays.asList(reserva1, reserva2);

        when(acomodacaoRepository.findAll()).thenReturn(acomodacoes);
        when(reservaRepository.findReservasEmAndamento(LocalDate.now())).thenReturn(reservas);

        double taxaOcupacao = dashboardService.getTaxaOcupacao();

        assertEquals(200.0, taxaOcupacao);
    }

    @Test
    public void testGetTaxaCancelamento() {
        LocalDate start = LocalDate.of(2024, 11, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        List<ReservaEntity> reservas = Arrays.asList(reserva1, reserva2);

        when(reservaRepository.findReservasByDataCheckInBetween(start, end)).thenReturn(reservas);
        when(reservaRepository.countReservasCanceladas(start, end)).thenReturn(0L);

        double taxaCancelamento = dashboardService.getTaxaCancelamento(11, 2024);

        assertEquals(0.0, taxaCancelamento);
    }

    @Test
    public void testGetTempoMedioEstadia() {
        LocalDate start = LocalDate.of(2024, 11, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        List<ReservaEntity> reservas = Arrays.asList(reserva1, reserva2);

        when(reservaRepository.findReservasByDataCheckInBetweenAndStatusReservaIn(start, end, Arrays.asList(StatusReservaEnum.FINALIZADA, StatusReservaEnum.EM_ANDAMENTO)))
                .thenReturn(reservas);

        double tempoMedioEstadia = dashboardService.getTempoMedioEstadia(11, 2024);

        assertEquals(4.5, tempoMedioEstadia);
    }

    @Test
    public void testGetReservasPorAcomodacao() {
        LocalDate start = LocalDate.of(2024, 11, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        List<ReservaEntity> reservas = Arrays.asList(reserva1, reserva2);
        List<AcomodacaoEntity> acomodacoes = Arrays.asList(acomodacao);

        when(acomodacaoRepository.findAll()).thenReturn(acomodacoes);
        when(reservaRepository.findReservasByDataCheckInBetweenAndStatusReservaIn(start, end, Arrays.asList(StatusReservaEnum.FINALIZADA, StatusReservaEnum.EM_ANDAMENTO)))
                .thenReturn(reservas);

        List<ReservasPorAcomodacaoDTO> reservasPorAcomodacao = dashboardService.getReservasPorAcomodacao(11, 2024);

        assertEquals(1, reservasPorAcomodacao.size());
        assertEquals("Acomodação 1", reservasPorAcomodacao.get(0).getAcomodacaoNome());
        assertEquals(2, reservasPorAcomodacao.get(0).getNumeroReservas());
    }
}
