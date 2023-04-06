//package com.mingeso.eva01.controllers;

/*
import com.mingeso.eva01.entities.ProveedorEntity;
import com.mingeso.eva01.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.util.ArrayList;

@Controller
@RequestMapping
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    //@GetMapping(/"Ingresar-Proveedor")

    @GetMapping("/nuevo-proveedor")
    public String proveedor(){
        return "nuevo-proveedor";
    }
    @PostMapping("/nuevo-proveedor")
    public String nuevoProveedor(@RequestParam("Id") Integer Id,
                                 @RequestParam("Nombre") String Nombre,
                                 @RequestParam("Categoria") String Categoria,
                                 @RequestParam("Retencion") Boolean Retencion){
        proveedorService.saveProveedor(Id, Nombre, Categoria, Retencion);
        return "redirect:/nuevo-proveedor";
    }

    @GetMapping("/get-proveedores")
    public String listaProveedores(Model model) {
        ArrayList<ProveedorEntity> proveedores = proveedorService.obtenerProveedores();
        model.addAttribute("proveedores", proveedores);
        return "lista-proveedores";
    }
}
*/
package com.mingeso.eva01.controllers;

import com.mingeso.eva01.entities.ProveedorEntity;
import com.mingeso.eva01.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @PostMapping("nuevo-proveedor")
    @ResponseBody
    /*public ProveedorEntity nuevoProveedor(@RequestBody ProveedorEntity proveedor) {
        return proveedorService.saveProveedor(proveedor);
    }*/
    public String nuevoProveedor(@RequestParam("Id") Integer Id,
                                 @RequestParam("Nombre") String Nombre,
                                 @RequestParam("Categoria") String Categoria,
                                 @RequestParam("Retencion") String Retencion){
        System.out.println("nuevoprov");
        proveedorService.saveProveedor(Id, Nombre, Categoria, Retencion);
        return "redirect:/nuevo-proveedor";
    }

    /*@GetMapping("lista-proveedores")
    @ResponseBody
    public List<ProveedorEntity> listaProveedores() {
        return proveedorService.obtenerProveedores();
    }*/

    @GetMapping("proveedores")
    public String listaProveedores(Model model){
        List<ProveedorEntity> proveedores = proveedorService.obtenerProveedores();
        model.addAttribute("proveedores", proveedores);
        return "lista-proveedores";
    }

    @GetMapping("proveedor/{id}")
    @ResponseBody
    public ProveedorEntity getProveedor(@PathVariable Integer id) {
        return proveedorService.getProveedor(id);
    }

    @GetMapping("proveedor/categoria/{id}")
    @ResponseBody
    public String getCategoria(@PathVariable Integer id) {
        System.out.println("holaaaa");
        return proveedorService.getCategoria(id);
    }

}
