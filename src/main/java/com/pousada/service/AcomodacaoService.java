package com.pousada.service;

import com.pousada.domain.entity.AcomodacaoEntity;
import com.pousada.domain.repository.AcomodacaoRepository;
import com.pousada.domain.repository.ReservaRepository;
import com.pousada.domain.specification.AcomodacaoSpecification;
import com.pousada.dto.AcomodacaoDTO;
import com.pousada.exception.AcomodacaoNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AcomodacaoService {

    private final ModelMapper modelMapper;
    private final AcomodacaoRepository acomodacaoRepository;
    private final ReservaRepository reservaRepository;

    public AcomodacaoDTO criarAcomodacao(AcomodacaoDTO acomodacaoDTO) {
//        AcomodacaoEntity acomodacaoEntity = AcomodacaoEntity.from(acomodacaoDTO);

        AcomodacaoEntity acomodacaoEntity = modelMapper.map(acomodacaoDTO, AcomodacaoEntity.class);
        AcomodacaoEntity acomodacaoEntitySalva = acomodacaoRepository.save(acomodacaoEntity);
        return modelMapper.map(acomodacaoEntitySalva, AcomodacaoDTO.class);
    }

    public AcomodacaoDTO atualizarAcomodacao(AcomodacaoDTO acomodacaoDTO) {
        AcomodacaoEntity acomodacaoEntity = modelMapper.map(acomodacaoDTO, AcomodacaoEntity.class);
        AcomodacaoEntity acomodacaoEntitySalva = acomodacaoRepository.save(acomodacaoEntity);
        return modelMapper.map(acomodacaoEntitySalva, AcomodacaoDTO.class);
    }

    public void deletarAcomodacaoPorId(Integer id) {
        if (acomodacaoRepository.existsById(id)) {
            if(verificarAssociacaoComReserva(id)){
                throw new DataIntegrityViolationException("Acomodação associada a uma reserva");
            }
            acomodacaoRepository.deleteById(id);
        } else {
            throw new AcomodacaoNaoEncontradaException("A acomodação com o ID " + id + " não existe.");
        }
    }

    public AcomodacaoDTO buscarAcomodacaoPorId(Integer id) {
        AcomodacaoEntity acomodacaoEntity = acomodacaoRepository.findById(id)
                .orElseThrow(() -> new AcomodacaoNaoEncontradaException("A acomodação com ID " + id + " não existe."));

        return modelMapper.map(acomodacaoEntity, AcomodacaoDTO.class);
    }

    public List<AcomodacaoDTO> buscarTodasAcomodacoes() {
        List<AcomodacaoEntity> acomodacaoEntities = acomodacaoRepository.findAll();

        return acomodacaoEntities.stream()
                .map(acomodacaoEntity -> modelMapper.map(acomodacaoEntity, AcomodacaoDTO.class))
                .collect(Collectors.toList());
    }

    public Page<AcomodacaoDTO> buscarAcomodacoesPaginadas(Pageable pageable) {
        Page<AcomodacaoEntity> page = acomodacaoRepository.findAll(pageable);
        return page.map(acomodacaoEntity -> modelMapper.map(acomodacaoEntity, AcomodacaoDTO.class));
    }

    public Page<AcomodacaoDTO> buscarComFiltros(String nome, Integer hospedes, Double diariaMin, Double diariaMax, Pageable pageable) {
        Specification<AcomodacaoEntity> spec = AcomodacaoSpecification.combinarFiltros(nome, hospedes, diariaMin, diariaMax);
        Page<AcomodacaoEntity> resultado = acomodacaoRepository.findAll(spec, pageable);
        return resultado.map(this::toDTO);
    }

    public AcomodacaoDTO toDTO(AcomodacaoEntity acomodacaoEntity) {
        AcomodacaoDTO dto = new AcomodacaoDTO();

        dto.setId(acomodacaoEntity.getId());
        dto.setNome(acomodacaoEntity.getNome());
        dto.setDescricao(acomodacaoEntity.getDescricao());
        dto.setValorDiaria(acomodacaoEntity.getValorDiaria());
        dto.setQtdHospedes(acomodacaoEntity.getQtdHospedes());
        dto.setDataCriacao(acomodacaoEntity.getDataCriacao());
        dto.setDataAtualizacao(acomodacaoEntity.getDataAtualizacao());
        dto.setComodidades(acomodacaoEntity.getComodidades());

        return dto;
    }

    private boolean verificarAssociacaoComReserva(Integer id) {
        return reservaRepository.existsByIdAcomodacao(id);
    }

}
