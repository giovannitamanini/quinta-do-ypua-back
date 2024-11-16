package com.pousada.domain.specification;

import com.pousada.domain.entity.AcomodacaoEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class AcomodacaoSpecification {

    public static Specification<AcomodacaoEntity> comNome(String nome) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isEmpty(nome)) {
                return null;
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
        };
    }

    public static Specification<AcomodacaoEntity> comHospedes(Integer hospedes) {
        return (root, query, criteriaBuilder) -> {
            if (hospedes == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("qtdHospedes"), hospedes);
        };
    }

    public static Specification<AcomodacaoEntity> comDiariaMin(Double diariaMin) {
        return (root, query, criteriaBuilder) -> {
            if (diariaMin == null) {
                return null;
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get("valorDiaria"), diariaMin);
        };
    }

    public static Specification<AcomodacaoEntity> comDiariaMax(Double diariaMax) {
        return (root, query, criteriaBuilder) -> {
            if (diariaMax == null) {
                return null;
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("valorDiaria"), diariaMax);
        };
    }

    /**
     * Método auxiliar que combina todas as specifications dinamicamente.
     * @param nome Nome da acomodação
     * @param hospedes Quantidade de hóspedes
     * @param diariaMin Valor mínimo da diária
     * @param diariaMax Valor máximo da diária
     * @return Specification combinada
     */
    public static Specification<AcomodacaoEntity> combinarFiltros(String nome, Integer hospedes, Double diariaMin, Double diariaMax) {
        Specification<AcomodacaoEntity> spec = Specification.where(null);

        if (nome != null) {
            spec = spec.and(comNome(nome));
        }

        if (hospedes != null) {
            spec = spec.and(comHospedes(hospedes));
        }

        if (diariaMin != null) {
            spec = spec.and(comDiariaMin(diariaMin));
        }

        if (diariaMax != null) {
            spec = spec.and(comDiariaMax(diariaMax));
        }

        return spec;
    }
}
