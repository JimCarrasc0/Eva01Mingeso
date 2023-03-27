package com.mingeso.eva01.controllers;


import com.mingeso.eva01.entities.ProveedorEntity;
import com.mingeso.eva01.repositories.ProveedorRepository;
import com.mingeso.eva01.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @PostMapping("/<nuevo-proveedor")
    public String nuevoProveedor(@RequestParam("Id") Integer Id,
                                 @RequestParam("Nombre") String Nombre,
                                 @RequestParam("Categoria") String Categoria,
                                 @RequestParam("Retencion") Boolean Retencion){
        proveedorService.saveProveedor(Id, Nombre, Categoria, Retencion);
        return "redirect:/nuevo-proveedor";
    }

    @GetMapping("/get-proveedores")
    public ArrayList<ProveedorEntity> listaProveedores(){
        return proveedorService.obtenerProveedores();
    }
}
