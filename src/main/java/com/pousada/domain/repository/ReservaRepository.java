package com.pousada.domain.repository;

import com.pousada.domain.entity.ReservaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends CrudRepository<ReservaEntity, Long> {

    List<ReservaEntity> findAll();

    @Query(value = "SELECT * FROM reserva WHERE id_acomodacao = :idAcomodacao AND (" +
            ":checkIn >= check_in AND :checkOut <= check_out " +
            "OR :checkIn <= check_in AND :checkOut >= check_out " +
            "OR :checkIn > check_in AND :checkIn < check_out " +
            "OR :checkOut > check_in AND :checkOut < check_out)",
            nativeQuery = true)
    ReservaEntity buscarReservaPorAcomodacaoEPeriodo(
            @Param("idAcomodacao") Integer idAcomodacao,
            @Param("checkIn") LocalDate checkIn,
            @Param("checkOut") LocalDate checkOut);

    @Query(value = "SELECT * FROM reserva WHERE status_reserva = 'EM_ESPERA'",
            nativeQuery = true)
    List<ReservaEntity> buscarReservasEmEspera();
}
