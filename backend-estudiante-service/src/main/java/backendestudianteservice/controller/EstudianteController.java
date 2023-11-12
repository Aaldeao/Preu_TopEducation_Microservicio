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

    @GetMapping("/{rut}")
    public ResponseEntity<EstudianteEntity> obtenerEstudiantePorRut(@PathVariable("rut") String rut) {
        EstudianteEntity estudiante = estudianteService.buscarRut(rut);
        return ResponseEntity.ok(estudiante);
    }
}
