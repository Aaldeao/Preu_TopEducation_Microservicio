package backendestudianteservice.controller;

import backendestudianteservice.entity.EstudianteEntity;
import backendestudianteservice.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/Estudiante")
public class EstudianteController {

    @Autowired // Es una instancia de EstudianteService //
    EstudianteService estudianteService;
    /*
    @GetMapping("/") // devuelve la vista del inicio //
    public String Inicio(){
        return "index";
    }
     @GetMapping("/Formulario") // muestra la vista del formulario y recibe los datos //
    public String IngresarEstudiante(Model model){
        model.addAttribute("estudiante", new EstudianteEntity());
        return "Formulario";
    }
    */

    @PostMapping("/")// procesa y guardarlo en la base de datos //
    public ResponseEntity<String> guardarEstudiante(@RequestBody EstudianteEntity estudiante){
        estudianteService.ingresarestudiante(estudiante);
        return ResponseEntity.ok("Estudiante guardado");
    }

    @GetMapping // Muestra a todos los estudiantes registrados //
    public ResponseEntity<ArrayList<EstudianteEntity>> lista(){
        ArrayList<EstudianteEntity> estudianteEntities = estudianteService.listaEstudiante();
        return ResponseEntity.ok(estudianteEntities);
    }

}
