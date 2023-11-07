package backendcuotaservice.controller;

import backendcuotaservice.model.EstudianteEntity;
import backendcuotaservice.service.CuotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Cuota")
public class CuotaController {
    @Autowired
    CuotaService cuotaService;

    @GetMapping("/{rut}")
    public ResponseEntity<String> ObtenerRut(@PathVariable("rut") String rut, @RequestParam("cantidad") int cantidad) {
        EstudianteEntity estudiante = cuotaService.findByRut(rut);
        estudiante.setCantidad(cantidad);
        cuotaService.cuotasxEstudiante(estudiante);
        return ResponseEntity.ok("Cuotas creadas");
    }
}
