package cl.mingeso.eva01.repositories;

import cl.mingeso.eva01.entities.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<ProveedorEntity,String> {

    @Query("select e from ProveedorEntity e where e.Nombre =:Nombre")
    ProveedorEntity findByNameCustomQuery(@Param("Nombre") String Nombre);

    @Query("select e.Categoria from ProveedorEntity e where e.proveedorId =:proveedorId")
    String findCategoryById(@Param("proveedorId")String proveedorId);

    @Query("select e from ProveedorEntity e where e.proveedorId =:proveedorId")
    ProveedorEntity findProveedorById(@Param("proveedorId")String proveedorId);

    @Query("select e.Retencion from ProveedorEntity e where e.proveedorId =:proveedorId")
    String findRetencionById(@Param("proveedorId")String proveedorId);

}
