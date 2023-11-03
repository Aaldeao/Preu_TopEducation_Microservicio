package backendestudianteservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "Estudiante")
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteEntity {
    @Id
    private String rut;
    private String apellidos;
    private String nombres;
    private String fechaNacimiento;
    private String tipoColegio;
    private String nombreColegio;
    private int anoEgreso;
    private int cantidad;
    private String tipoDepago;
}
