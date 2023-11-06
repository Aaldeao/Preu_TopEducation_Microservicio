import React, { useState, useEffect } from "react";
import EstudianteService from "../services/EstudianteService";

function ListarEstudiante() {
  const [estudianteEntity, setEstudianteEntity] = useState([]);
  useEffect(() => {
    EstudianteService.getEstudiante().then((res) => {
      setEstudianteEntity(res.data);
    });
  }, []);

  return (
    <div className="general">
      <div align="center" className="container-2">
        <h1><b>Lista de Estudiantes</b></h1>
        <table className="content-table">
          <thead>
            <tr>
              <th>RUT</th>
              <th>Nombre</th>
              <th>Apellidos</th>
              <th>Fecha de Nacimiento</th>
              <th>Tipo de Colegio</th>
              <th>Nombre de Colegio</th>
              <th>Año de egreso</th>
              <th>Tipo de pago</th>
            </tr>
          </thead>
          <tbody>
            {estudianteEntity.map((estudiante, index) => (
              <tr key={index} className={index % 2 === 0 ? "even" : "odd"}>
                <td>{estudiante.rut}</td>
                <td>{estudiante.nombres}</td>
                <td>{estudiante.apellidos}</td>
                <td>{estudiante.fechaNacimiento}</td>
                <td>{estudiante.tipoColegio}</td>
                <td>{estudiante.nombreColegio}</td>
                <td>{estudiante.anoEgreso}</td>
                <td>{estudiante.tipoDepago}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default ListarEstudiante;