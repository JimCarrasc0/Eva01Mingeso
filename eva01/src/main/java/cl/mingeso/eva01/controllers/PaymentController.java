package cl.mingeso.eva01.controllers;

import cl.mingeso.eva01.dtos.Reporte;
import cl.mingeso.eva01.entities.PaymentEntity;
import cl.mingeso.eva01.entities.ProveedorEntity;
import cl.mingeso.eva01.repositories.PaymentRepository;
import cl.mingeso.eva01.repositories.ProveedorRepository;
import cl.mingeso.eva01.services.PaymentService;
import cl.mingeso.eva01.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ProveedorService proveedorService;
    PaymentEntity pago;
    @Autowired
    private ProveedorRepository proveedorRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping("pagar")
    public String main(Model model){
        List<ProveedorEntity> proveedores = proveedorService.obtenerProveedores();
        model.addAttribute("proveedores", proveedores);
        return "pagar-proveedor";
    }

    @GetMapping ("pagar/{proveedorId}")
    public RedirectView calcularPago(@PathVariable String proveedorId, Model model){
        pago = paymentService.calcularCostoProveedor(proveedorId);
        return new RedirectView("/realizar-pago");
    }

    @GetMapping("realizar-pago")
    public String realizarPago(Model model){

        Reporte reporte=new Reporte();
        String proveedorId =pago.getProveedorId();

        ProveedorEntity proveedor = proveedorRepository.findProveedorById(proveedorId);

        reporte.setProveedorId(proveedorId);
        reporte.setNombreProveedor(proveedor.getNombre());
        reporte.setTotalKilos(pago.getKilos());

        List<Object[]> varLeches = paymentRepository.getVariacionKgLecheByProveedorId(proveedorId);
        float varLeche = 0;
        if (varLeches.size() > 1){
            varLeche =(Float) varLeches.get(varLeches.size()-1)[1];
        }
        reporte.setVarLeche(varLeche);
        reporte.setVarGrasa((Float) paymentRepository.getVariacionGrasaByProveedorId(proveedorId).get(1)[1]);
        reporte.setVarSolido((Float) paymentRepository.getVariacionSolidoByProveedorId(proveedorId).get(1)[1]);

        reporte.setPagoLeche(pago.getPagoLeche());
        reporte.setPagoGrasa(pago.getPagoGrasa());
        reporte.setPagoSolido(pago.getPagoSolido());
        reporte.setFreqBonus(pago.getFreqBonus());
        reporte.setDescLeche(pago.getDescLeche());
        reporte.setDescGrasa(pago.getDescGrasa());
        reporte.setDescSolido(pago.getDescSolido());
        reporte.setPagoTotal(pago.getPagoTotal());
        reporte.setRetencion(pago.getRetencion());
        reporte.setPagoFinal(pago.getPagoFinal());

        model.addAttribute("reporte",reporte);

        return "info-pago";
    }

}
