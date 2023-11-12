package backendpruebaservice.service;

import backendpruebaservice.entity.PruebaEntity;
import backendpruebaservice.model.CuotaEntity;
import backendpruebaservice.model.EstudianteEntity;
import backendpruebaservice.model.ReporteEntity;
import backendpruebaservice.repository.PruebaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@Service
public class PruebaService {
    @Autowired
    PruebaRepository pruebaRepository;
    @Autowired
    RestTemplate restTemplate;

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
        return pruebaRepository.findByRut(rut);
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

    // Crea un reporte para cada estudiante con los atributos solicitados y los almacena ReporteEntity //
    public ArrayList<ReporteEntity> crearReporte(){
        List<EstudianteEntity> estudiantes = obtenerTodosLosEstudiantes();
        ArrayList <ReporteEntity> reportes = new ArrayList<>();

        for (EstudianteEntity estudiante : estudiantes){
            List<CuotaEntity> cuotas = obtenerCuotasDeEstudiante(estudiante.getRut());
            int examenesRendidos = calcularCantidadprueba(estudiante.getRut());
            double arancel = calculararancel(estudiante.getRut());
            String tipoDePago = estudiante.getTipoDepago();
            double promedioPuntaje = calcularpromediopuntaje(estudiante.getRut());
            int cuotasPagadas = obtenerCuotasPagadas(estudiante.getRut());
            double montoPagado = obtenerMontoCuotasPagadas(estudiante.getRut());
            int cuotasAtrasadas = obtenerCuotasAtrasadas(estudiante.getRut());
            LocalDate fechaUltimacuota = obtenerFechaultimaCuota(estudiante.getRut());
            double saldoAPagar = montoApagar(estudiante.getRut());

            ReporteEntity reporte = new ReporteEntity();
            reporte.setExamenesRendidos(examenesRendidos);
            reporte.setPromedioPuntaje(promedioPuntaje);
            reporte.setRut(estudiante.getRut());
            reporte.setNombre(estudiante.getNombres());
            reporte.setArancel(arancel);
            reporte.setTipoPago(tipoDePago);
            reporte.setCuotasPactadas(cuotas.size());
            reporte.setCuotasPagadas(cuotasPagadas);
            reporte.setCuotasAtrasadas(cuotasAtrasadas);
            reporte.setMontoPagado(montoPagado);
            reporte.setSaldoaPagar(saldoAPagar);
            reporte.setFechaUltimoCuota(fechaUltimacuota);
            reportes.add(reporte);

        }
        return reportes;
    }

    //Los llamados a otros microservicios//
    public List<EstudianteEntity> obtenerTodosLosEstudiantes() {
        ResponseEntity<List<EstudianteEntity>> response = restTemplate.exchange("http://localhost:8080/Estudiante/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<EstudianteEntity>>() {}
        );
        return response.getBody();
    }

    public List<CuotaEntity> obtenerCuotasDeEstudiante(String rut) {
        ResponseEntity<List<CuotaEntity>> response = restTemplate.exchange(
                "http://localhost:8080/Cuota/buscarcuota/" + rut,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CuotaEntity>>() {}
        );
        return response.getBody();
    }

    public double calculararancel(String rut) {
        ResponseEntity<Double> response = restTemplate.exchange(
                "http://localhost:8080/Cuota/" + rut ,
                HttpMethod.GET,
                null,
                Double.class
        );
        return response.getBody();
    }

    public int obtenerCuotasPagadas(String rut) {
        ResponseEntity<Integer> response = restTemplate.exchange(
                "http://localhost:8080/Cuota/cuotaspagadas/" + rut,
                HttpMethod.GET,
                null,
                Integer.class
        );
        return response.getBody();
    }

    public double obtenerMontoCuotasPagadas(String rut) {
        ResponseEntity<Double> response = restTemplate.exchange(
                "http://localhost:8080/Cuota/montocuotaspagadas/" + rut,
                HttpMethod.GET,
                null,
                Double.class
        );
        return response.getBody();
    }

    public int obtenerCuotasAtrasadas(String rut) {
        ResponseEntity<Integer> response = restTemplate.exchange(
                "http://localhost:8080/Cuota/cuotasatrasadas/" + rut,
                HttpMethod.GET,
                null,
                Integer.class
        );
        return response.getBody();
    }

    public LocalDate obtenerFechaultimaCuota(String rut) {
        ResponseEntity<LocalDate> response = restTemplate.exchange(
                "http://localhost:8080/Cuota/fechaultimacuota/" + rut,
                HttpMethod.GET,
                null,
                LocalDate.class
        );
        return response.getBody();
    }
    public double montoApagar(String rut) {
        ResponseEntity<Double> response = restTemplate.exchange(
                "http://localhost:8080/Cuota/montoapagar/" + rut,
                HttpMethod.GET,
                null,
                Double.class
        );
        return response.getBody();
    }

}
