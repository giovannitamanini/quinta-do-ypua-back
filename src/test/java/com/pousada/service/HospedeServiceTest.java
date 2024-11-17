package com.pousada.service;

import com.pousada.domain.entity.HospedeEntity;
import com.pousada.domain.repository.HospedeRepository;
import com.pousada.domain.repository.ReservaRepository;
import com.pousada.dto.HospedeDTO;
import com.pousada.exception.HospedeNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HospedeServiceTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private HospedeRepository hospedeRepository;

    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private HospedeService hospedeService;

    private HospedeEntity hospedeEntity;
    private HospedeDTO hospedeDTO;

    @BeforeEach
    public void setUp() {
        hospedeEntity = new HospedeEntity();
        hospedeEntity.setId(1);
        hospedeEntity.setNome("João");
        hospedeEntity.setEmail("joao@example.com");

        hospedeDTO = new HospedeDTO();
        hospedeDTO.setId(1);
        hospedeDTO.setNome("João");
        hospedeDTO.setEmail("joao@example.com");
    }

    @Test
    public void testCriarHospede() {
        when(modelMapper.map(hospedeDTO, HospedeEntity.class)).thenReturn(hospedeEntity);
        when(hospedeRepository.save(hospedeEntity)).thenReturn(hospedeEntity);
        when(modelMapper.map(hospedeEntity, HospedeDTO.class)).thenReturn(hospedeDTO);

        HospedeDTO result = hospedeService.criarHospede(hospedeDTO);

        assertNotNull(result);
        assertEquals("João", result.getNome());
        verify(hospedeRepository, times(1)).save(hospedeEntity);
    }

    @Test
    public void testAtualizarHospede() {
        when(modelMapper.map(hospedeDTO, HospedeEntity.class)).thenReturn(hospedeEntity);
        when(hospedeRepository.save(hospedeEntity)).thenReturn(hospedeEntity);
        when(modelMapper.map(hospedeEntity, HospedeDTO.class)).thenReturn(hospedeDTO);

        HospedeDTO result = hospedeService.atualizarHospede(hospedeDTO);

        assertNotNull(result);
        assertEquals("João", result.getNome());
        verify(hospedeRepository, times(1)).save(hospedeEntity);
    }

    @Test
    public void testDeletarHospedePorId_HospedeExistente() {
        when(hospedeRepository.existsById(1)).thenReturn(true);
        when(reservaRepository.existsByIdAcomodacao(1)).thenReturn(false);

        hospedeService.deletarHospedePorId(1);

        verify(hospedeRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeletarHospedePorId_HospedeNaoExistente() {
        when(hospedeRepository.existsById(1)).thenReturn(false);

        HospedeNaoEncontradoException exception = assertThrows(HospedeNaoEncontradoException.class, () -> {
            hospedeService.deletarHospedePorId(1);
        });

        assertEquals("A acomodação com o ID 1 não existe.", exception.getMessage());
    }

    @Test
    public void testBuscarHospedePorId_HospedeExistente() {
        when(hospedeRepository.findById(1)).thenReturn(Optional.of(hospedeEntity));
        when(modelMapper.map(hospedeEntity, HospedeDTO.class)).thenReturn(hospedeDTO);

        HospedeDTO result = hospedeService.buscarHospedePorId(1);

        assertNotNull(result);
        assertEquals("João", result.getNome());
    }

    @Test
    public void testBuscarHospedePorId_HospedeNaoExistente() {
        when(hospedeRepository.findById(1)).thenReturn(Optional.empty());

        HospedeNaoEncontradoException exception = assertThrows(HospedeNaoEncontradoException.class, () -> {
            hospedeService.buscarHospedePorId(1);
        });

        assertEquals("O hóspede com o ID 1 não existe.", exception.getMessage());
    }

    @Test
    public void testBuscarHospedesPorNome() {
        when(hospedeRepository.findByNome("João")).thenReturn(Arrays.asList(hospedeEntity));
        when(modelMapper.map(hospedeEntity, HospedeDTO.class)).thenReturn(hospedeDTO);

        List<HospedeDTO> result = hospedeService.buscarHospedesPorNome("João");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("João", result.get(0).getNome());
    }

    @Test
    public void testBuscarHospedesPorNome_HospedeNaoExistente() {
        when(hospedeRepository.findByNome("João")).thenReturn(Arrays.asList());

        HospedeNaoEncontradoException exception = assertThrows(HospedeNaoEncontradoException.class, () -> {
            hospedeService.buscarHospedesPorNome("João");
        });

        assertEquals("Nenhum hóspede com o nome João está registrado!", exception.getMessage());
    }

    @Test
    public void testBuscarTodosHospedes() {
        when(hospedeRepository.findAll()).thenReturn(Arrays.asList(hospedeEntity));
        when(modelMapper.map(hospedeEntity, HospedeDTO.class)).thenReturn(hospedeDTO);

        List<HospedeDTO> result = hospedeService.buscarTodosHospedes();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("João", result.get(0).getNome());
    }

    @Test
    public void testBuscarHospedesPaginados() {
        Pageable pageable = PageRequest.of(0, 10);
        when(hospedeRepository.findAll(pageable)).thenReturn(Page.empty());

        Page<HospedeDTO> result = hospedeService.buscarHospedesPaginados(pageable);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testDeletarHospedePorId_HospedeComReserva() {
        when(hospedeRepository.existsById(1)).thenReturn(true);
        when(reservaRepository.existsByIdAcomodacao(1)).thenReturn(true);

        DataIntegrityViolationException exception = assertThrows(DataIntegrityViolationException.class, () -> {
            hospedeService.deletarHospedePorId(1);
        });

        assertEquals("Hóspede associado a uma reserva", exception.getMessage());
    }
}
