package cl.mingeso.eva01.repositories;

import cl.mingeso.eva01.entities.ImportarDatosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportarDatosRepository extends JpaRepository <ImportarDatosEntity, Integer> {

    @Query("select e from ImportarDatosEntity e where e.proveedorId =: proveedorId")
    ImportarDatosEntity findByProveedor(@Param("proveedorId") Integer proveedorId);
}
