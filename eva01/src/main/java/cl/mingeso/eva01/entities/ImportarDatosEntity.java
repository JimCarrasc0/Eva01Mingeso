package cl.mingeso.eva01.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.sql.Date;


@Entity
@Table(name="datos")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ImportarDatosEntity {
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date fecha;
    private String turno;
    private String proveedorId;
    private Float kgLeche;

}
