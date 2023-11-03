package backendestudianteservice.service;

import backendestudianteservice.entity.EstudianteEntity;
import backendestudianteservice.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
