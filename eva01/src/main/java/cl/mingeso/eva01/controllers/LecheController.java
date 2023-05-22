package cl.mingeso.eva01.controllers;

import cl.mingeso.eva01.entities.LecheEntity;
import cl.mingeso.eva01.services.LecheService;
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
public class LecheController {
    @Autowired
    private LecheService importar;

    @GetMapping("milkupload")
    public String main(){
        return "subir-leche";
    }

    @PostMapping("milkupload")
    public RedirectView subirArchivo(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes){
        importar.guardar(file);
        redirectAttributes.addFlashAttribute("mensaje","El archivo se cargó correctamente");
        importar.leerCsv("Grasa.csv");
        return new RedirectView("/milkupload");
    }
    @GetMapping("milkinfo")
    public String mostrarInfo(Model model){
        ArrayList<LecheEntity> leche = importar.obtenerDatos();
        model.addAttribute("leche",leche);
        return "info-leche";
    }
}
