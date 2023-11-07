package backendestudianteservice.service;

import backendestudianteservice.entity.EstudianteEntity;
import backendestudianteservice.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class EstudianteService {

    @Autowired // Es una instancia de EstudianteRepository //
    EstudianteRepository estudianteRepository;
    /*
    @Autowired
    CuotaService cuotaService;
    @Autowired
    PruebaService pruebaService;
    */
    public EstudianteEntity ingresarestudiante(EstudianteEntity estudiante){ // Guarda en la base de datos los datos del estudiante //
        return estudianteRepository.save(estudiante);
    }

    public ArrayList<EstudianteEntity>listaEstudiante(){ // Lista de estudiante guardado en la base de datos //
        return estudianteRepository.findAll();
    }

    public EstudianteEntity buscarRut(String rut){
        return estudianteRepository.findByRut(rut);
    }
}
