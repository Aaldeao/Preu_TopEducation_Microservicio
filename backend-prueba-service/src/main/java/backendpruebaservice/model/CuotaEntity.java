package backendpruebaservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuotaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCuota;
    private String rut;
    private int numeroCuota;
    private double arancel;
    private double arancelMensual;
    private LocalDate fechaEmision;
    private LocalDate fechaPago;
    private LocalDate fechaVencimiento;
    private String estado;
    private boolean descuentoPrueba;
}