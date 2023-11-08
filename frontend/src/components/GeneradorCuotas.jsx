import React, { useState } from "react";
import CuotaService from "../services/CuotaService";

function GenerarCuotas() {
    const [rut, setRut] = useState('');

    const generarCuotas = () => {
        CuotaService.generarCuotas(rut)
            .then((response) => {
                console.log("Cuotas generadas con éxito:", response.data);
            });
    }

    return (
    <div align="center" className="container-2">
      <h1>Generador de Cuotas</h1>
      <div className="container-create">
        <label htmlFor="rut">RUT del Estudiante:</label>
        <input
          type="text"
          className="form-control"
          id="rut"
          name="rut"
          value={rut}
          onChange={(e) => setRut(e.target.value)}
          required
        />
      </div>
      <button className="btn btn-primary" onClick={generarCuotas}>
        Generar Cuotas
      </button>
    </div>
  );
}

export default GenerarCuotas;