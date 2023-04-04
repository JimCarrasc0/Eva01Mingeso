package com.mingeso.eva01.repositories;

import com.mingeso.eva01.entities.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<ProveedorEntity,Integer> {

    @Query("select e from ProveedorEntity e where e.Nombre =: Nombre")
    ProveedorEntity findByNameCustomQuery(@Param("Nombre") String Nombre);

    @Query("select e.Categoria from ProveedorEntity e where e.id =: Id")
    String findCategory(@Param("Id") Integer id);

    @Query("select e from ProveedorEntity e where e.id = :Id")
    ProveedorEntity findProveedorById(@Param("Id")Integer id);
}
