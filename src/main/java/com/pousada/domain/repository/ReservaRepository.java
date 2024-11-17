package com.pousada.domain.repository;

import com.pousada.domain.entity.ReservaEntity;
import com.pousada.enums.StatusReservaEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

    @Query("SELECT COUNT(r) FROM ReservaEntity r WHERE r.statusReserva = 'CANCELADA' AND (r.dataCheckIn BETWEEN :start AND :end)")
    Long countReservasCanceladas(@Param("start") LocalDate start, @Param("end") LocalDate end);

    @Query("SELECT r FROM ReservaEntity r WHERE r.dataCheckIn <= :today AND r.dataCheckOut >= :today")
    List<ReservaEntity> findReservasEmAndamento(@Param("today") LocalDate today);

    @Query("SELECT r.idAcomodacao, COUNT(r) FROM ReservaEntity r WHERE MONTH(r.dataCheckIn) = :mes AND YEAR(r.dataCheckIn) = :ano GROUP BY r.idAcomodacao")
    Map<String, Integer> countReservasPorAcomodacao(@Param("mes") int mes, @Param("ano") int ano);

    List<ReservaEntity> findReservasByDataCheckInBetween(LocalDate startDate, LocalDate endDate);

    List<ReservaEntity> findReservasByDataCheckInBetweenAndStatusReservaIn(LocalDate startDate, LocalDate endDate, List<StatusReservaEnum> statuses);

    Boolean existsByIdAcomodacao(Integer id);
}
