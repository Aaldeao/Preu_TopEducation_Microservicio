package backendcuotaservice.service;

import backendcuotaservice.entity.CuotaEntity;
import backendcuotaservice.model.EstudianteEntity;
import backendcuotaservice.repository.CuotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class CuotaService {
    @Autowired
    CuotaRepository cuotaRepository;
    @Autowired
    RestTemplate restTemplate;

    // Crea un cuota //
    public CuotaEntity creacuota(int numero, EstudianteEntity estudiante){
        CuotaEntity cuota = new CuotaEntity();
        //cuota.setEstudiante(estudiante);
        cuota.setEstado("Pendiente");
        cuota.setArancel(calculararancel(estudiante));
        cuota.setNumeroCuota(numero);
        cuota.setFechaEmision(LocalDate.now());
        calcularcuotamensuales(estudiante);
        cuota.setFechaPago(cuota.getFechaEmision().plusMonths(1).withDayOfMonth(5));
        cuota.setFechaVencimiento(cuota.getFechaEmision().plusMonths(1).withDayOfMonth(11));
        return cuota;
    }

    // Calcula el arancel con los descuentos correspondientes sobre su tipo de colegio y los años de egreso//
    public double calculararancel(EstudianteEntity estudiante){
        double arancel = 1500000;
        int egreso= LocalDate.now().getYear() - estudiante.getAnoEgreso();

        if ("Municipal".equalsIgnoreCase(estudiante.getTipoColegio())){ // por ser un tipo de colegio municipal tiene 20% //
            if(egreso < 1){
                arancel = arancel * 0.65; // 20% + 15% //
            } else if (egreso <= 2) {
                arancel = arancel * 0.72; // 20% + 8% //

            } else if (egreso <= 4) {
                arancel = arancel * 0.76; // 20% + 4% //

            }
        }else if ("Subvencionado".equalsIgnoreCase(estudiante.getTipoColegio())){ // por ser un tipo de colegio municipal tiene 10% //

            if(egreso<1){
                arancel = arancel * 0.75; // 10% + 15% //
            } else if (egreso <= 2) {
                arancel = arancel * 0.82; // 10% + 8% //

            } else if (egreso <= 4) {
                arancel = arancel * 0.86; // 10% + 4% //

            }
        }else if ("Privado".equalsIgnoreCase(estudiante.getTipoColegio())){ // El tipo colegio Privado solo tiene el descuento por los años de egreso //
            if(egreso<1){
                arancel = arancel * 0.85; // 15% //
            } else if (egreso <= 2) {
                arancel = arancel * 0.92; // 8% //

            } else if (egreso <= 4) {
                arancel = arancel * 0.96; // 4% //
            }
        }
        return arancel;
    }

    // Calcula el arancel mensual que deberia pagar dependiendo de las cuotas escogidas //
    public double calcularcuotamensuales( EstudianteEntity estudiante){
        double arancel  = calculararancel(estudiante);
        double arancelMensual = 0;
        if (arancel>0 && estudiante.getCantidad()>0){
            arancelMensual = arancel / estudiante.getCantidad();
        }
        return Math.round(arancelMensual); // redondea el valor decimal al numero entero mas cercano //
    }

    // Guarda el arancel y su arancel mensual del estudiante //
    public CuotaEntity guardarcuota(CuotaEntity cuota) {
        return cuotaRepository.save(cuota);
    }

    // Genera las cuotas mediante a la cantidad asociada que solicia la persona y las guarda  //

    public void cuotasxEstudiante(String rut) {
        EstudianteEntity estudiante = findByRut(rut, 0);
        if (estudiante != null) {
            int cantidadCuotas = estudiante.getCantidad();
            LocalDate fechaEmision = LocalDate.now();
            double cuotaMensual = calcularcuotamensuales(estudiante);
            for (int i = 0; i < cantidadCuotas; i++) {
                CuotaEntity cuota = creacuota(i + 1, estudiante);
                cuota.setArancelMensual(cuotaMensual);
                cuota.setFechaEmision(fechaEmision);
                cuota.setFechaPago(cuota.getFechaEmision().plusMonths(1).withDayOfMonth(5));
                cuota.setFechaVencimiento(cuota.getFechaEmision().plusMonths(1).withDayOfMonth(11));
                guardarcuota(cuota);
                fechaEmision = cuota.getFechaEmision().plusMonths(1);
            }
        }
    }
    /*
    // Obtener las cuotas asociadas por el rut //
    public ArrayList<CuotaEntity> obtenerPorRut(String rut){
        return cuotaRepository.findByEstudianteRut(rut);
    }
    */
    public EstudianteEntity findByRut(String rut, int cantidad){
        System.out.println("rut: "+rut);
        ResponseEntity<EstudianteEntity>response=restTemplate.exchange("http://localhost:8080/Estudiante/" + rut+ "?cantidad=" + cantidad,
        HttpMethod.GET,
                null,
                new ParameterizedTypeReference<EstudianteEntity>() {}
        );
        return response.getBody();
    }
}
