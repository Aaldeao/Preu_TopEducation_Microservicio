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

    @PostMapping("/pagocuota/{idCuota}") // Al apretar el boton de pagar en la cuota obtenemos el idCuota y se cambiamos el estado //
    public ResponseEntity<String> pagoCuota(@PathVariable Long idCuota) {
        CuotaEntity cuota = cuotaService.obteneridCuota(idCuota);
        cuotaService.pagarCuota(cuota);
        return ResponseEntity.ok ("Cuota del estudiante pagada");
    }

    @PostMapping("/atrasadacuota/{idCuota}")// Al apretar el boton de atrasada en la cuota se cambiamos el estado para luego agregar sus intereses //
    public ResponseEntity<String> pagarCuotaAtrasada(@PathVariable Long idCuota){
        CuotaEntity cuota = cuotaService.obteneridCuota(idCuota);
        cuotaService.pagarCuotaAtrasadas(cuota);
        return ResponseEntity.ok ("Cuota del estudiante Atrasada");
    }
}
