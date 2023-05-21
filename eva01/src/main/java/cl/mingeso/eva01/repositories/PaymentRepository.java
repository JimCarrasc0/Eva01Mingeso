package cl.mingeso.eva01.repositories;

import cl.mingeso.eva01.entities.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.ArrayList;
import java.util.List;


public interface PaymentRepository extends JpaRepository<PaymentEntity,Integer> {

    @Query("SELECT SUM(d.kgLeche) as kilosLeche "
            + "FROM ImportarDatosEntity d "
            + "WHERE d.proveedorId =:proveedorId ")

    Float getLecheProveedor(@Param("proveedorId") String proveedorId);

    @Query("SELECT avg(l.grasa) as gt "
            +"FROM LecheEntity l "
            + "WHERE l.proveedorId =:proveedorId ")

    Float getGrasaProveedor(@Param("proveedorId") String proveedorId);

    @Query("SELECT avg(l.solido) as st "
            +"FROM LecheEntity l "
            + "WHERE l.proveedorId =:proveedorId ")

    Float getSolidoProveedor(@Param("proveedorId") String proveedorId);

    @Query("SELECT DISTINCT d.turno as turnos "
        + "FROM ImportarDatosEntity d "
        + "WHERE d.proveedorId =:proveedorId")

    ArrayList<String> getTurnosProveedor(@Param("proveedorId") String proveedorId);

    @Query("SELECT l.kgLeche, " +
            "((l.kgLeche - LAG(l.kgLeche) OVER (ORDER BY l.proveedorId)) / LAG(l.kgLeche) OVER (ORDER BY l.id)) * 100 AS variacion " +
            "FROM ImportarDatosEntity l " +
            "WHERE l.proveedorId = :proveedorId")
    List<Object[]> getVariacionKgLecheByProveedorId(@Param("proveedorId") String proveedorId);


    @Query("SELECT l.grasa, " +
            "((l.grasa - LAG(l.grasa) OVER (ORDER BY l.proveedorId)) / LAG(l.grasa) OVER (ORDER BY l.id)) * 100 AS variacion " +
            "FROM LecheEntity l " +
            "WHERE l.proveedorId = :proveedorId")
    List<Object[]> getVariacionGrasaByProveedorId(@Param("proveedorId") String proveedorId);

    @Query("SELECT l.solido, " +
            "((l.solido - LAG(l.solido) OVER (ORDER BY l.proveedorId)) / LAG(l.solido) OVER (ORDER BY l.id)) * 100 AS variacion " +
            "FROM LecheEntity l " +
            "WHERE l.proveedorId = :proveedorId")
    List<Object[]> getVariacionSolidoByProveedorId(@Param("proveedorId") String proveedorId);


}

