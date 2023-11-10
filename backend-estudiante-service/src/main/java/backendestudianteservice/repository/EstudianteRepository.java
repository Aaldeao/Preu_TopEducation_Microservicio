package backendestudianteservice.repository;

import backendestudianteservice.entity.EstudianteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface EstudianteRepository extends JpaRepository<EstudianteEntity, String> {

    EstudianteEntity findByRut(String rut); // Permite buscar por el rut en la base de datos //
    ArrayList<EstudianteEntity> findAll(); //Obtiene a todos los estudiantes //
}
