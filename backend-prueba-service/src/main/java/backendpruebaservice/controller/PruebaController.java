package backendpruebaservice.controller;

import backendpruebaservice.service.PruebaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/prueba")
@CrossOrigin(origins = "http://localhost:3000/")
public class PruebaController {
    @Autowired
    PruebaService pruebaService;

    @PostMapping("/subirExcel") // Específica la ruta para subir el archivo Excel
    public ResponseEntity<String> subirExcel(@RequestParam("pruebaExcel") MultipartFile pruebaExcel) {
        if (pruebaExcel != null) {
            pruebaService.guardar(pruebaExcel);
            String resultado = pruebaService.cargarCsv(pruebaExcel);
            return ResponseEntity.ok(resultado);
        } else {
            return ResponseEntity.badRequest().body("El archivo es nulo.");
        }
    }
}