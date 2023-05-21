package cl.mingeso.eva01.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reporte {
    private String proveedorId;
    private String nombreProveedor;
    private Float totalKilos;
    private Float varLeche;
    private Float varGrasa;
    private Float varSolido;
    private Float pagoLeche;
    private Float pagoGrasa;
    private Float pagoSolido;
    private Float freqBonus;

    private Float descLeche;
    private Float descGrasa;
    private Float descSolido;
    private Float pagoTotal;
    private Float retencion;
    private Float pagoFinal;

}
