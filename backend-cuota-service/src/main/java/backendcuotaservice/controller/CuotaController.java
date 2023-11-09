package backendcuotaservice.controller;

import backendcuotaservice.entity.CuotaEntity;
import backendcuotaservice.service.CuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/Cuota")
public class CuotaController {
    @Autowired
    CuotaService cuotaService;

    @GetMapping("/generar/{rut}")
    public ResponseEntity<String> generarCuotas(@PathVariable String rut) {
        cuotaService.cuotasxEstudiante(rut);
        return ResponseEntity.ok("Cuotas generadas para el estudiante: " + rut);
    }

    @GetMapping("/buscarcuota/{rut}")
    public ResponseEntity<ArrayList<CuotaEntity>> obtenerPorRut(@PathVariable String rut){
        ArrayList<CuotaEntity> cuota = cuotaService.obtenerPorRut(rut);
        return ResponseEntity.ok(cuota);

    }
}
