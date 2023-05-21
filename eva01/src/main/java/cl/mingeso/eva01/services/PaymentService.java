package cl.mingeso.eva01.services;

import cl.mingeso.eva01.entities.PaymentEntity;
import cl.mingeso.eva01.repositories.PaymentRepository;
import cl.mingeso.eva01.repositories.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {


    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    ProveedorRepository proveedorRepository;

    public PaymentEntity calcularCostoProveedor(String proveedorId){
        PaymentEntity pago = new PaymentEntity();
        Float leche=paymentRepository.getLecheProveedor(proveedorId);

        Float gt = paymentRepository.getGrasaProveedor(proveedorId);

        Float st = paymentRepository.getSolidoProveedor(proveedorId);

        String categoria=proveedorRepository.findCategoryById(proveedorId);

        String retencion=proveedorRepository.findRetencionById(proveedorId);

        ArrayList<String> turnos = paymentRepository.getTurnosProveedor(proveedorId);


        List<Object[]> varLeches = paymentRepository.getVariacionKgLecheByProveedorId(proveedorId);
        List<Object[]> varGrasas = paymentRepository.getVariacionGrasaByProveedorId(proveedorId);
        List<Object[]> varSolidos =paymentRepository.getVariacionSolidoByProveedorId(proveedorId);


        pago.setProveedorId(proveedorId);
        pago.setKilos(leche);


        int precioCategoria = 0;
        float descuento=0;

        /** Precios Categoría:
         * A=700
         * B=550
         * C=400
         * D=250
         **/

        switch (categoria){
            case "A":
                precioCategoria = 700;
            case "B":
                precioCategoria = 550;
            case "C":
                precioCategoria = 400;
            case "D":
                precioCategoria = 250;

                break;
        }

        /** Precios x Grasa:
         * 0-20=30
         * 21-45=80
         * 46-100=120
         **/
        int precioGrasa = 0;

        if(gt <= 20){
            precioGrasa+= 30;
        } else if (gt >= 21 && gt <= 45) {
            precioGrasa+= 80;
        } else if (gt <= 100) {
            precioGrasa+= 120;
        }
        /** Precios x Solido:
         * 0-7=-130
         * 8-18=-90
         * 19-35=95
         * 36-100=150
         **/
        int precioSolido = 0;

        if(st < 7){
            precioSolido+= -130;
        } else if (st >= 8 && st <= 18) {
            precioSolido+= -90;
        }else if (st >= 19 && st <= 35) {
            precioSolido+= 95;
        } else if (st <= 100) {
            precioSolido+= 150;
        }

        Float pagoLeche=leche*precioCategoria;
        Float pagoGrasa=leche*precioGrasa;
        Float pagoSolido=leche*precioSolido;

        float prelim= pagoLeche+pagoGrasa+pagoSolido ;

        /** Precio x turno:
         * MyN= +20% x kilo
         * M= +12% x kilo
         * N= +8% x kilo
         **/

        float precioTurno=0;

        for(String turno: turnos){
            if(turno=="M"){
                precioTurno+=0.12;
            } else{
                precioTurno+=0.08;
            }
        }

        float bonus = leche * precioTurno * precioCategoria;

        float pagoAcopio= prelim + bonus;


        /**Descuentos
         * %variacion negativa kilo
         * 0-8 = 0%
         * 9-25 = 7%
         * 26-45 = 15%
         * 46-100 = 30%
         */
        float descLeche=0;

        float varLeche = 0;
        if (varLeches.size() > 1){
            varLeche =(Float) varLeches.get(varLeches.size()-1)[1];
        }


        if(varLeche<0){
            if (-varLeche >= 9 && -varLeche <= 25) {
                descLeche+= 0.07;
            } else if (-varLeche >= 26 && -varLeche <= 45) {
                descLeche+= 0.15;
            } else if (-varLeche <= 100){
                descLeche+= 0.3;
            }
        }

         /** %variacion negativa grasa
         * 0-15 = 0%
         * 16-25 = 12%
         * 26-40 = 20%
         * 41-100 = 30%
         */

        float descGrasa=0;
        float varGrasa = (Float) varGrasas.get(1)[1];

        if(varGrasa<0){
            if (-varGrasa >= 16 && -varGrasa <= 25) {
                descGrasa+= 0.12;
            } else if (-varGrasa >= 26 && -varGrasa <= 40) {
                descGrasa+= 0.2;
            } else if (-varGrasa <= 100){
                descGrasa+= 0.3;
            }
        }


        /** %variacion negativa solido
        * 0-6 = 0%
        * 7-12 = 18%
        * 13-35 = 27%
        * 36-100 = 45%
        */

        float descSolido=0;
        float varSolido = (Float) varSolidos.get(1)[1];

        if(varSolido<0){
            if (-varSolido >= 7 && -varSolido <= 12) {
                descSolido+= 0.18;
            } else if (-varSolido >= 13 && -varSolido <= 35) {
                descSolido+= 0.27;
            } else if (-varSolido <= 100){
                descSolido+= 0.45;
            }
        }

        float descuentos = (descLeche+descGrasa+descSolido) * pagoAcopio;

        float pagoTotal=pagoAcopio-descuentos;

        /** Retención = 13%
        *
        **/

        float imp=0;

        if(retencion=="Si" && pagoTotal>=950000){
            imp += pagoTotal * 0.13;
        }


        float pagoFinal= pagoTotal-imp;


        pago.setPagoLeche(pagoLeche);
        pago.setPagoGrasa(pagoGrasa);
        pago.setPagoSolido(pagoSolido);
        pago.setFreqBonus(bonus);
        pago.setDescLeche(descLeche);
        pago.setDescGrasa(descGrasa);
        pago.setDescSolido(descSolido);
        pago.setPagoTotal(pagoTotal);
        pago.setRetencion(imp);
        pago.setPagoFinal(pagoFinal);


        return pago;
    }


}
