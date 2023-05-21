package cl.mingeso.eva01.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name="leche")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LecheEntity {
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String proveedorId;
    private Float grasa;
    private Float solido;

}
