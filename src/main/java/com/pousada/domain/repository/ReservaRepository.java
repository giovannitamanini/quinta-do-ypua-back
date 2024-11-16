package com.pousada.domain.repository;

import com.pousada.domain.entity.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Integer> {

    List<ReservaEntity> findAll();

    @Query(value = "SELECT * FROM reserva WHERE id_acomodacao = :idAcomodacao AND (" +
            ":dataCheckIn >= data_check_in AND :dataCheckOut <= data_check_out " +
            "OR :dataCheckIn <= data_check_in AND :dataCheckOut >= data_check_out " +
            "OR :dataCheckIn > data_check_in AND :dataCheckIn < data_check_out " +
            "OR :dataCheckOut > data_check_in AND :dataCheckOut < data_check_out)",
            nativeQuery = true)
    ReservaEntity buscarReservaPorAcomodacaoEPeriodo(
            @Param("idAcomodacao") Integer idAcomodacao,
            @Param("dataCheckIn") LocalDate dataCheckIn,
            @Param("dataCheckOut") LocalDate dataCheckOut);

    @Query(value = "SELECT * FROM reserva WHERE status_reserva = 'EM_ESPERA'",
            nativeQuery = true)
    List<ReservaEntity> buscarReservasEmEspera();

    Boolean existsByIdAcomodacao(Integer id);
}
