package backendpruebaservice.controller;

import backendpruebaservice.model.ReporteEntity;
import backendpruebaservice.service.PruebaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;


@RestController
@RequestMapping("/Prueba")
public class PruebaController {
    @Autowired
    PruebaService pruebaService;
    @PostMapping(value = "/subirExcel")
    public ResponseEntity<String> subirExcel(@RequestPart("prueba") MultipartFile prueba) {
        String resultado = pruebaService.cargarCsv(prueba);
        return ResponseEntity.ok(resultado);
    }
    @GetMapping("/calcularpromediopuntaje/{rut}")
    public double calcularPromedioPuntaje(@PathVariable String rut) {
        return pruebaService.calcularpromediopuntaje(rut);
    }

    @GetMapping("/reporte") // Muestra los reporte de los estudiantes //
    public ResponseEntity<ArrayList<ReporteEntity>> reporte() {
        ArrayList<ReporteEntity> reporte = pruebaService.crearReporte();
        return ResponseEntity.ok(reporte);

    }

}