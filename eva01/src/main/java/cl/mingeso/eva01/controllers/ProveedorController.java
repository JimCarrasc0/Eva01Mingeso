package cl.mingeso.eva01.controllers;

import cl.mingeso.eva01.entities.ProveedorEntity;
import cl.mingeso.eva01.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping("nuevo-proveedor")
    public String nuevoProveedor(){
        return "nuevo-proveedor";
    }

    @PostMapping("nuevo-proveedor")
    @ResponseBody
    public RedirectView nuevoProveedor(@RequestParam("id") String Id,
                                       @RequestParam("nombre") String Nombre,
                                       @RequestParam("categoria") String Categoria,
                                       @RequestParam("retencion") String Retencion){
        proveedorService.saveProveedor(Id, Nombre, Categoria, Retencion);
        return new RedirectView("/nuevo-proveedor");
    }
    @GetMapping("proveedores")
    public String listaProveedores(Model model){
        List<ProveedorEntity> proveedores = proveedorService.obtenerProveedores();
        model.addAttribute("proveedores", proveedores);
        return "lista-proveedores";
    }

    @GetMapping("proveedor/{id}")
    @ResponseBody
    public ProveedorEntity getProveedor(@PathVariable String id) {
        return proveedorService.getProveedor(id);
    }

    @GetMapping("proveedor/categoria/{id}")
    @ResponseBody
    public String getCategoria(@PathVariable String id) {
        return proveedorService.getCategoria(id);
    }

}
