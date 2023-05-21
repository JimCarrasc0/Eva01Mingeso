package cl.mingeso.eva01.services;

import cl.mingeso.eva01.entities.LecheEntity;
import cl.mingeso.eva01.repositories.LecheRepository;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Service
public class LecheService {
    @Autowired
    private LecheRepository datosLeche;

    private final Logger logg = LoggerFactory.getLogger(LecheService.class);

    public ArrayList<LecheEntity> obtenerDatos(){
        return (ArrayList<LecheEntity>) datosLeche.findAll();
    }

    @Generated
    public String guardar(MultipartFile file){
        String nombre = file.getOriginalFilename();
        if(nombre!=null){
            if(!file.isEmpty()){
                try{
                    byte [] bytes = file.getBytes();
                    Path path = Paths.get(file.getOriginalFilename());
                    Files.write(path, bytes);
                    logg.info("Archivo Guardado");

                }catch (IOException e){
                    logg.error("ERROR", e);
                }
            }
            System.out.println("aaaaaaaaaa");
            return "Archivo guardado con éxito";
        }
        else {
            return "No se pudo guardar el archivo";
        }
    }

    public void guardarDatos(LecheEntity datos){
        datosLeche.save(datos);
    }

    public void guardarDB(String proveedorId, String grasa, String solido){
        LecheEntity datos = new LecheEntity();

        datos.setProveedorId(proveedorId);
        datos.setGrasa(Float.parseFloat(grasa));
        datos.setSolido(Float.parseFloat(solido));

        guardarDatos(datos);
    }

    @Generated
    public void leerCsv(String direccion){
        String texto="";
        BufferedReader bf = null;

        try {
            bf = new BufferedReader(new FileReader(direccion));
            String temp="";
            String bfRead;
            int count = 1;
            while((bfRead=bf.readLine())!=null){
                if(count==1){
                    count=0;
                }else {
                    guardarDB(bfRead.split(";")[0], bfRead.split(";")[1], bfRead.split(";")[2]);
                    temp= temp + "\n" + bfRead;
                }
            }

        }catch (IOException e){
            logg.error("Error",e);
        }
    }

    public void eliminarDatos(ArrayList<LecheEntity> datos){
        datosLeche.deleteAll(datos);
    }

}
