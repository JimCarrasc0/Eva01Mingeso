package com.mingeso.eva01.services;

import com.mingeso.eva01.entities.ProveedorEntity;
import com.mingeso.eva01.repositories.ProveedorRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProveedorService {
    @Autowired
    ProveedorRepository proveedorRepository;

    /*
    * Método para guardar 1 proveedor
    * ingreso los parámetros solicitados para el correcto ingreso de datos a la tabla.
    */
    public void saveProveedor(Integer Id, String Nombre, String Categoria, Boolean Retencion){
        ProveedorEntity proveedor = new ProveedorEntity(Id,Nombre,Categoria,Retencion);
        proveedorRepository.save(proveedor);
    }

    /*
    * Método para poder obtener el listado completo de proveedores de la tabla.
    */
    public ArrayList<ProveedorEntity> obtenerProveedores(){
        return (ArrayList<ProveedorEntity>) proveedorRepository.findAll();
    }

    /*
     * Método para poder obtener la categoría de un proveedor
     */
    public String getCategoria(Integer Id){
        return proveedorRepository.findCategory(Id);
    }

    /*
     * Método para poder obtener la información de un proveedor
     */
    public ProveedorEntity getProveedor(Integer Id){
        return proveedorRepository.findProveedorById(Id);
    }

}
