package com.pousada.service;

import com.pousada.domain.entity.ReservaEntity;
import com.pousada.domain.repository.ReservaRepository;
import com.pousada.dto.ReservaDTO;
import com.pousada.enums.StatusReservaEnum;
import com.pousada.exception.AcomodacaoOcupadaException;
import com.pousada.exception.ReservaEmAndamentoOuFinalizadaException;
import com.pousada.exception.ReservaNaoEncontradaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservaServiceTest {

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ReservaService reservaService;

    private ReservaDTO reservaDTO;
    private ReservaEntity reservaEntity;

    @BeforeEach
    void setUp() {
        reservaDTO = new ReservaDTO();
        reservaDTO.setIdAcomodacao(1);
        reservaDTO.setDataCheckIn(LocalDate.of(2024, 12, 1));
        reservaDTO.setDataCheckOut(LocalDate.of(2024, 12, 5));
        reservaDTO.setStatusReserva(StatusReservaEnum.PENDENTE);

        reservaEntity = new ReservaEntity();
        reservaEntity.setIdAcomodacao(1);
        reservaEntity.setDataCheckIn(LocalDate.of(2024, 12, 1));
        reservaEntity.setDataCheckOut(LocalDate.of(2024, 12, 5));
        reservaEntity.setStatusReserva(StatusReservaEnum.PENDENTE);
    }

    @Test
    void testCriarReservaComSucesso() {
        when(reservaRepository.save(any(ReservaEntity.class))).thenReturn(reservaEntity);
        when(modelMapper.map(any(ReservaDTO.class), eq(ReservaEntity.class))).thenReturn(reservaEntity);
        when(modelMapper.map(any(ReservaEntity.class), eq(ReservaDTO.class))).thenReturn(reservaDTO);
        when(reservaRepository.buscarReservaPorAcomodacaoEPeriodo(anyInt(), any(), any())).thenReturn(null); // Não há conflitos de período

        ReservaDTO reservaCriada = reservaService.criarReserva(reservaDTO);

        assertNotNull(reservaCriada);
        assertEquals(reservaDTO.getIdAcomodacao(), reservaCriada.getIdAcomodacao());
        assertEquals(reservaDTO.getDataCheckIn(), reservaCriada.getDataCheckIn());
        assertEquals(reservaDTO.getDataCheckOut(), reservaCriada.getDataCheckOut());
        assertEquals(reservaDTO.getStatusReserva(), reservaCriada.getStatusReserva());

        verify(reservaRepository, times(1)).save(any(ReservaEntity.class));
    }

    @Test
    void testCriarReservaComAcomodacaoOcupada() {
        ReservaEntity reservaExistente = new ReservaEntity();
        reservaExistente.setIdAcomodacao(1);
        reservaExistente.setDataCheckIn(LocalDate.of(2024, 12, 1));
        reservaExistente.setDataCheckOut(LocalDate.of(2024, 12, 5));
        reservaExistente.setStatusReserva(StatusReservaEnum.PENDENTE);

        when(reservaRepository.buscarReservaPorAcomodacaoEPeriodo(anyInt(), any(), any())).thenReturn(reservaExistente);

        assertThrows(AcomodacaoOcupadaException.class, () -> reservaService.criarReserva(reservaDTO));

        verify(reservaRepository, never()).save(any(ReservaEntity.class));
    }

    @Test
    void testDeletarReservaPorIdReservaNaoExistente() {
        when(reservaRepository.existsById(1)).thenReturn(false);

        assertThrows(ReservaNaoEncontradaException.class, () -> reservaService.deletarReservaPorId(1));
    }

    @Test
    void testDeletarReservaPorIdComStatusEmAndamento() {
        reservaEntity.setStatusReserva(StatusReservaEnum.EM_ANDAMENTO);
        when(reservaRepository.existsById(1)).thenReturn(true);
        when(reservaRepository.findById(1)).thenReturn(Optional.of(reservaEntity));

        assertThrows(ReservaEmAndamentoOuFinalizadaException.class, () -> reservaService.deletarReservaPorId(1));
    }

    @Test
    void testDeletarReservaPorIdComStatusFinalizada() {
        reservaEntity.setStatusReserva(StatusReservaEnum.FINALIZADA);
        when(reservaRepository.existsById(1)).thenReturn(true);
        when(reservaRepository.findById(1)).thenReturn(Optional.of(reservaEntity));

        assertThrows(ReservaEmAndamentoOuFinalizadaException.class, () -> reservaService.deletarReservaPorId(1));
    }

    @Test
    void testBuscarReservaPorIdComSucesso() {
        when(reservaRepository.findById(1)).thenReturn(Optional.of(reservaEntity));
        when(modelMapper.map(reservaEntity, ReservaDTO.class)).thenReturn(reservaDTO);

        ReservaDTO result = reservaService.buscarReservaPorId(1);

        assertNotNull(result);
        assertEquals(reservaDTO.getId(), result.getId());
        assertEquals(reservaDTO.getIdAcomodacao(), result.getIdAcomodacao());
    }

    @Test
    void testBuscarReservaPorIdReservaNaoEncontrada() {
        when(reservaRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ReservaNaoEncontradaException.class, () -> reservaService.buscarReservaPorId(1));
    }
}
