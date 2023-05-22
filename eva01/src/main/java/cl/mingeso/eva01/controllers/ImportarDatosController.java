package cl.mingeso.eva01.controllers;


import cl.mingeso.eva01.entities.ImportarDatosEntity;
import cl.mingeso.eva01.services.ImportarDatosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;

@Controller
@RequestMapping("/")
public class ImportarDatosController {

    @Autowired
    private ImportarDatosService importar;

    @GetMapping("fileupload")
    public String main(){
        return "subir-archivo";
    }

    @PostMapping("fileupload")
    public RedirectView subirArchivo(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes){
        importar.guardar(file);
        redirectAttributes.addFlashAttribute("mensaje","El archivo se cargó correctamente");
        importar.leerCsv("Acopio.csv");
        return new RedirectView("/fileupload");
    }

    @GetMapping("/fileinfo")
    public String mostrarInfo(Model model){
        ArrayList<ImportarDatosEntity> datos = importar.obtenerDatos();
        model.addAttribute("datos",datos);
        return "info-archivo";
    }
}
