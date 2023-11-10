package backendpruebaservice.service;

import backendpruebaservice.entity.PruebaEntity;
import backendpruebaservice.model.EstudianteEntity;
import backendpruebaservice.repository.PruebaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.nio.file.Paths;
import java.util.ArrayList;

@Service
public class PruebaService {
    @Autowired
    PruebaRepository pruebaRepository;
    @Autowired
    RestTemplate restTemplate;
    /*
    // Guarda el archivo subido en mi carpeta raiz //
   private final Logger logg = LoggerFactory.getLogger(PruebaService.class);
   @Generated
   public String guardar(MultipartFile file){
       String filename = file.getOriginalFilename();
       if(filename != null){
           if(!file.isEmpty()){
               try{
                   byte [] bytes = file.getBytes();
                   Path path  = Paths.get(file.getOriginalFilename());
                   Files.write(path, bytes);
                   logg.info("Archivo guardado");
               }
               catch (IOException e){
                   logg.error("ERROR", e);
               }
           }
           return "Archivo guardado con exito!";
       }
       else{
           return "No se pudo guardar el archivo";
       }
   }

// Lee un archivo .csv el cual valida si el formato es el indicado(rut,fecha,puntaje) y luego lo guarda en la base de datos //
    public String cargarCsv(MultipartFile archivoCSV) {
        try {
            Scanner scanner = new Scanner(archivoCSV.getInputStream());
            scanner.useDelimiter(";");

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");

                if (parts.length == 3) { // comprueba si el array tiene un largo 3 //
                    String rut = parts[0];
                    String fechaExamen = parts[1];
                    String puntajeStr = parts[2].trim();// El puntaje debe ser el tercero y elimina el espacio en blanco //
                    Long puntaje = Long.parseLong(puntajeStr); // convierte el puntaje del alumno en Long //

                    EstudianteEntity estudiante = findByRut(rut);
                    PruebaEntity pruebaEntity = new PruebaEntity();
                    pruebaEntity.setRut(rut);
                    pruebaEntity.setFechaExamen(fechaExamen);
                    pruebaEntity.setPuntaje(puntaje);
                    pruebaRepository.save(pruebaEntity);
                }
            }
            scanner.close();
            return "csv cargado";
        } catch (IOException e) {
            e.printStackTrace();
            return "error con el archivo.csv";
        }
    }
// Obtiene lo asociado del rut //
public ArrayList<PruebaEntity> obtenerPruebasPorRut(String rut){
    return pruebaRepository.findByEstudianteRut(rut);
}

// Calcula la cantidad de pruebas asociado al rut(cuantas veces se repite el rut) //
public int calcularCantidadprueba(String rut) {
    ArrayList<PruebaEntity> pruebas = obtenerPruebasPorRut(rut);
    int cantidadPruebas = pruebas.size();
    for (PruebaEntity prueba : pruebas) {
        prueba.setCantidadPrueba(cantidadPruebas);
        pruebaRepository.save(prueba);
    }
    return cantidadPruebas;
}

// Calcula el promedio de los puntajes asociado al rut //
public double calcularpromediopuntaje(String rut){
    int cantidadnotas= calcularCantidadprueba(rut);
    ArrayList<PruebaEntity> pruebas = obtenerPruebasPorRut(rut);
    double sumaNotas = 0;
    for (PruebaEntity prueba : pruebas){
        sumaNotas = sumaNotas + prueba.getPuntaje();
    }
    if (cantidadnotas > 0){
        return sumaNotas / cantidadnotas;
    }
    return sumaNotas;
}


    public EstudianteEntity findByRut(String rut){
        System.out.println("rut: "+rut);
        ResponseEntity<EstudianteEntity> response=restTemplate.exchange("http://localhost:8080/Estudiante/" + rut,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<EstudianteEntity>() {}
        );
        return response.getBody();
    }

    */
}
