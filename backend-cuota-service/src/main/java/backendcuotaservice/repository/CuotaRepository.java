package backendcuotaservice.repository;

import backendcuotaservice.entity.CuotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface CuotaRepository extends JpaRepository<CuotaEntity, Long> {
    //ArrayList<CuotaEntity> findByEstudianteRut(String rut); // La busqueda del rut en la base de datos //
    //Optional<CuotaEntity> findById(Long id); // Busca la cuota mediante su ID en la base de datos //
}
