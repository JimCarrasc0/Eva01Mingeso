package com.mingeso.eva01.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "Proveedores")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProveedorEntity {
    @Id
    @NonNull
    private Integer Id;
    private String Nombre;
    private String Categoria;
    private Boolean Retencion;
}
