package backendcuotaservice.repository;

import backendcuotaservice.entity.CuotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

@Repository
public interface CuotaRepository extends JpaRepository<CuotaEntity, Long> {
    ArrayList<CuotaEntity> findByRut(String rut); // La busqueda del rut en la base de datos //
    Optional<CuotaEntity> findById(Long id); // Busca la cuota mediante su ID en la base de datos //
    @Query("select cuota from CuotaEntity cuota where cuota.estado = 'Pendiente'") // Obtenemos una lista de cuotas que solo tengan el estado pendiente //
    List<CuotaEntity> findCuotasPendintes();
}
