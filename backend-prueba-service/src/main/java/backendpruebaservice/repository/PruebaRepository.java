package backendpruebaservice.repository;

import backendpruebaservice.entity.PruebaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PruebaRepository extends JpaRepository<PruebaEntity, Long> {
    ArrayList<PruebaEntity> findByRut(String rut);// La busqueda del rut en la base de datos //
}
