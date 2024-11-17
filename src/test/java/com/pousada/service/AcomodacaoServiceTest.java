package com.pousada.service;

import com.pousada.domain.entity.AcomodacaoEntity;
import com.pousada.domain.repository.AcomodacaoRepository;
import com.pousada.domain.repository.ReservaRepository;
import com.pousada.dto.AcomodacaoDTO;
import com.pousada.exception.AcomodacaoNaoEncontradaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AcomodacaoServiceTest {

    @Mock
    private AcomodacaoRepository acomodacaoRepository;

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AcomodacaoService acomodacaoService;

    private AcomodacaoDTO acomodacaoDTO;
    private AcomodacaoEntity acomodacaoEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        acomodacaoDTO = new AcomodacaoDTO();
        acomodacaoDTO.setId(1);
        acomodacaoDTO.setNome("Acomodação Teste");

        acomodacaoEntity = new AcomodacaoEntity();
        acomodacaoEntity.setId(1);
        acomodacaoEntity.setNome("Acomodação Teste");
    }

    @Test
    void testCriarAcomodacao() {
        when(modelMapper.map(acomodacaoDTO, AcomodacaoEntity.class)).thenReturn(acomodacaoEntity);
        when(acomodacaoRepository.save(acomodacaoEntity)).thenReturn(acomodacaoEntity);
        when(modelMapper.map(acomodacaoEntity, AcomodacaoDTO.class)).thenReturn(acomodacaoDTO);

        AcomodacaoDTO result = acomodacaoService.criarAcomodacao(acomodacaoDTO);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Acomodação Teste", result.getNome());
        verify(acomodacaoRepository, times(1)).save(acomodacaoEntity);
    }

    @Test
    void testAtualizarAcomodacao() {
        when(modelMapper.map(acomodacaoDTO, AcomodacaoEntity.class)).thenReturn(acomodacaoEntity);
        when(acomodacaoRepository.save(acomodacaoEntity)).thenReturn(acomodacaoEntity);
        when(modelMapper.map(acomodacaoEntity, AcomodacaoDTO.class)).thenReturn(acomodacaoDTO);

        AcomodacaoDTO result = acomodacaoService.atualizarAcomodacao(acomodacaoDTO);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Acomodação Teste", result.getNome());
        verify(acomodacaoRepository, times(1)).save(acomodacaoEntity);
    }

    @Test
    void testDeletarAcomodacaoPorIdComSucesso() {
        when(acomodacaoRepository.existsById(1)).thenReturn(true);
        when(reservaRepository.existsByIdAcomodacao(1)).thenReturn(false);

        acomodacaoService.deletarAcomodacaoPorId(1);

        verify(acomodacaoRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeletarAcomodacaoPorIdComReserva() {
        when(acomodacaoRepository.existsById(1)).thenReturn(true);
        when(reservaRepository.existsByIdAcomodacao(1)).thenReturn(true);

        DataIntegrityViolationException exception = assertThrows(DataIntegrityViolationException.class, () ->
                acomodacaoService.deletarAcomodacaoPorId(1));

        assertEquals("Acomodação associada a uma reserva", exception.getMessage());
    }

    @Test
    void testDeletarAcomodacaoPorIdNaoEncontrada() {
        when(acomodacaoRepository.existsById(1)).thenReturn(false);

        AcomodacaoNaoEncontradaException exception = assertThrows(AcomodacaoNaoEncontradaException.class, () ->
                acomodacaoService.deletarAcomodacaoPorId(1));

        assertEquals("A acomodação com o ID 1 não existe.", exception.getMessage());
    }

    @Test
    void testBuscarAcomodacaoPorIdComSucesso() {
        when(acomodacaoRepository.findById(1)).thenReturn(Optional.of(acomodacaoEntity));
        when(modelMapper.map(acomodacaoEntity, AcomodacaoDTO.class)).thenReturn(acomodacaoDTO);

        AcomodacaoDTO result = acomodacaoService.buscarAcomodacaoPorId(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Acomodação Teste", result.getNome());
    }

    @Test
    void testBuscarAcomodacaoPorIdNaoEncontrada() {
        when(acomodacaoRepository.findById(1)).thenReturn(Optional.empty());

        AcomodacaoNaoEncontradaException exception = assertThrows(AcomodacaoNaoEncontradaException.class, () ->
                acomodacaoService.buscarAcomodacaoPorId(1));

        assertEquals("A acomodação com ID 1 não existe.", exception.getMessage());
    }

    @Test
    void testBuscarTodasAcomodacoes() {
        List<AcomodacaoEntity> acomodacaoEntities = Collections.singletonList(acomodacaoEntity);
        when(acomodacaoRepository.findAll()).thenReturn(acomodacaoEntities);
        when(modelMapper.map(acomodacaoEntity, AcomodacaoDTO.class)).thenReturn(acomodacaoDTO);

        List<AcomodacaoDTO> result = acomodacaoService.buscarTodasAcomodacoes();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Acomodação Teste", result.get(0).getNome());
    }

    @Test
    void testBuscarAcomodacoesPaginadas() {
        Page<AcomodacaoEntity> page = new PageImpl<>(Collections.singletonList(acomodacaoEntity));
        Pageable pageable = PageRequest.of(0, 10);
        when(acomodacaoRepository.findAll(pageable)).thenReturn(page);
        when(modelMapper.map(acomodacaoEntity, AcomodacaoDTO.class)).thenReturn(acomodacaoDTO);

        Page<AcomodacaoDTO> result = acomodacaoService.buscarAcomodacoesPaginadas(pageable);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals("Acomodação Teste", result.getContent().get(0).getNome());
    }

}
