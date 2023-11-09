import React, { useState } from "react";
import CuotaService from "../services/CuotaService";

function BuscarcuotasPorRut() {
  const [rut, setRut] = useState("");
  const [cuotas, setCuotas] = useState([]);
  const buscarCuotas = () => {
    CuotaService.mostarCuota(rut)
      .then((response) => {
        setCuotas(response.data);
      })
  };

  return (
    <div align="center" className="container-2">
        <h1>Lista de Cuotas</h1>
        <div className="container-create">
          <label htmlFor="rut ">RUT del Estudiante:</label>
          <input
            type="text"
            id="rut"
            name="rut"
            value={rut}
            onChange={(e) => setRut(e.target.value)}
          />
        </div>
        <button className="btn btn-primary" onClick={buscarCuotas}>Buscar Cuotas</button>
        <table className="content-table">
          <thead>
            <tr>
            <th>Rut</th>
            <th>Cuotas</th>
            <th>Arancel</th>
            <th>Arancel mensual</th>
            <th>Fecha de Pago</th>
            <th>Fecha de Vencimiento</th>
            <th>Estado de pago</th>
            </tr>
          </thead>
          <tbody>
          {cuotas.map((cuota, index) => (
            <tr key={index} className={index % 2 === 0 ? "even" : "odd"}>
                <td>{cuota.rut}</td>
                <td>{cuota.numeroCuota}</td>
                <td>{cuota.arancel}</td>
                <td>{cuota.arancelMensual}</td>
                <td>{cuota.fechaPago}</td>
                <td>{cuota.fechaVencimiento}</td>
                <td>{cuota.estado}</td>
                <td>
                <button className="btn btn-success separador" >Pagar</button>
                <button className="btn btn-danger separador" >Atrasadaento</button>
                <button className="btn btn-info separador" >Descuento</button>

                </td>
            </tr>
            ))}
          </tbody>
        </table>
    </div>

  );
}

export default BuscarcuotasPorRut;
