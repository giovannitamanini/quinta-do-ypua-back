package com.pousada.service;

import com.pousada.domain.entity.AcomodacaoEntity;
import com.pousada.domain.entity.ComodidadeEntity;
import com.pousada.domain.repository.AcomodacaoRepository;
import com.pousada.domain.repository.ComodidadeRepository;
import com.pousada.dto.ComodidadeDTO;
import com.pousada.enums.TipoComodidadeEnum;
import com.pousada.exception.ComodidadeNaoEncontradaException;
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
public class ComodidadeServiceTest {

    @Mock
    private ComodidadeRepository comodidadeRepository;

    @Mock
    private AcomodacaoRepository acomodacaoRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ComodidadeService comodidadeService;

    private ComodidadeDTO comodidadeDTO;
    private ComodidadeEntity comodidadeEntity;

    @BeforeEach
    void setUp() {
        comodidadeDTO = new ComodidadeDTO();
        comodidadeDTO.setId(1);
        comodidadeDTO.setDescricao("WiFi");
        comodidadeDTO.setTipo(TipoComodidadeEnum.OUTROS);

        comodidadeEntity = new ComodidadeEntity();
        comodidadeEntity.setId(1);
        comodidadeEntity.setDescricao("WiFi");
        comodidadeEntity.setTipo(TipoComodidadeEnum.OUTROS);
    }

    @Test
    void testCriarItem() {
        when(modelMapper.map(comodidadeDTO, ComodidadeEntity.class)).thenReturn(comodidadeEntity);
        when(comodidadeRepository.save(comodidadeEntity)).thenReturn(comodidadeEntity);
        when(modelMapper.map(comodidadeEntity, ComodidadeDTO.class)).thenReturn(comodidadeDTO);

        ComodidadeDTO result = comodidadeService.criarItem(comodidadeDTO);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("WiFi", result.getDescricao());
        assertEquals(TipoComodidadeEnum.OUTROS, result.getTipo());
        verify(comodidadeRepository, times(1)).save(comodidadeEntity);
    }

    @Test
    void testAtualizarItem() {
        when(modelMapper.map(comodidadeDTO, ComodidadeEntity.class)).thenReturn(comodidadeEntity);
        when(comodidadeRepository.save(comodidadeEntity)).thenReturn(comodidadeEntity);
        when(modelMapper.map(comodidadeEntity, ComodidadeDTO.class)).thenReturn(comodidadeDTO);

        ComodidadeDTO result = comodidadeService.atualizarItem(comodidadeDTO);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("WiFi", result.getDescricao());
        assertEquals(TipoComodidadeEnum.OUTROS, result.getTipo());
        verify(comodidadeRepository, times(1)).save(comodidadeEntity);
    }

    @Test
    void testDeletarItemPorIdComSucesso() {
        Integer id = 1;
        ComodidadeEntity comodidade = new ComodidadeEntity();
        comodidade.setId(id);
        comodidade.setAcomodacoes(new ArrayList<>());

        when(comodidadeRepository.findById(id)).thenReturn(Optional.of(comodidade));

        comodidadeService.deletarItemPorId(id);

        verify(comodidadeRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeletarItemPorIdComAcomodacao() {
        comodidadeEntity.setAcomodacoes(Collections.singletonList(mock(AcomodacaoEntity.class)));
        when(comodidadeRepository.findById(1)).thenReturn(Optional.of(comodidadeEntity));

        DataIntegrityViolationException exception = assertThrows(DataIntegrityViolationException.class, () ->
                comodidadeService.deletarItemPorId(1));

        assertEquals("Comodidade associada a uma reserva", exception.getMessage());
    }

    @Test
    void testDeletarItemPorIdNaoEncontrado() {
        when(comodidadeRepository.findById(1)).thenReturn(Optional.empty());

        ComodidadeNaoEncontradaException exception = assertThrows(ComodidadeNaoEncontradaException.class, () ->
                comodidadeService.deletarItemPorId(1));

        assertEquals("O item com o ID 1 não existe.", exception.getMessage());
    }

    @Test
    void testBuscarComodidadePorIdComSucesso() {
        when(comodidadeRepository.findById(1)).thenReturn(Optional.of(comodidadeEntity));
        when(modelMapper.map(comodidadeEntity, ComodidadeDTO.class)).thenReturn(comodidadeDTO);

        ComodidadeDTO result = comodidadeService.buscarComodidadePorId(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("WiFi", result.getDescricao());
    }

    @Test
    void testBuscarComodidadePorIdNaoEncontrada() {
        when(comodidadeRepository.findById(1)).thenReturn(Optional.empty());

        ComodidadeNaoEncontradaException exception = assertThrows(ComodidadeNaoEncontradaException.class, () ->
                comodidadeService.buscarComodidadePorId(1));

        assertEquals("A comodidade com o ID 1 não existe.", exception.getMessage());
    }

    @Test
    void testBuscarComodidadePorDescricaoComSucesso() {
        when(comodidadeRepository.findByDescricao("WiFi")).thenReturn(Optional.of(comodidadeEntity));
        when(modelMapper.map(comodidadeEntity, ComodidadeDTO.class)).thenReturn(comodidadeDTO);

        ComodidadeDTO result = comodidadeService.buscarComodidadePorDescricao("WiFi");

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("WiFi", result.getDescricao());
    }

    @Test
    void testBuscarComodidadePorDescricaoNaoEncontrada() {
        when(comodidadeRepository.findByDescricao("WiFi")).thenReturn(Optional.empty());

        ComodidadeNaoEncontradaException exception = assertThrows(ComodidadeNaoEncontradaException.class, () ->
                comodidadeService.buscarComodidadePorDescricao("WiFi"));

        assertEquals("A comodidade com a descrição WiFi não existe.", exception.getMessage());
    }

    @Test
    void testBuscarTodasComodidades() {
        List<ComodidadeEntity> comodidadeEntities = Collections.singletonList(comodidadeEntity);
        when(comodidadeRepository.findAll()).thenReturn(comodidadeEntities);
        when(modelMapper.map(comodidadeEntity, ComodidadeDTO.class)).thenReturn(comodidadeDTO);

        List<ComodidadeDTO> result = comodidadeService.buscarTodasComodidades();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("WiFi", result.get(0).getDescricao());
    }

    @Test
    void testBuscarComodidadesPaginadas() {
        Page<ComodidadeEntity> page = new PageImpl<>(Collections.singletonList(comodidadeEntity));
        Pageable pageable = PageRequest.of(0, 10);
        when(comodidadeRepository.findAll(pageable)).thenReturn(page);
        when(modelMapper.map(comodidadeEntity, ComodidadeDTO.class)).thenReturn(comodidadeDTO);

        Page<ComodidadeDTO> result = comodidadeService.buscarComodidadesPaginadas(pageable);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals("WiFi", result.getContent().get(0).getDescricao());
    }

    @Test
    void testBuscarComodidadesComFiltro() {
        Page<ComodidadeEntity> page = new PageImpl<>(Collections.singletonList(comodidadeEntity));
        Pageable pageable = PageRequest.of(0, 10);
        when(comodidadeRepository.findByDescricaoAndTipo("WiFi", TipoComodidadeEnum.OUTROS, pageable)).thenReturn(page);
        when(modelMapper.map(comodidadeEntity, ComodidadeDTO.class)).thenReturn(comodidadeDTO);

        Page<ComodidadeDTO> result = comodidadeService.buscarComodidadesComFiltro("WiFi", TipoComodidadeEnum.OUTROS, pageable);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals("WiFi", result.getContent().get(0).getDescricao());
    }
}
