import React, { Component } from 'react';
import PruebaService from "../services/PruebaService";

class Reporte extends Component {
  constructor(props) {
    super(props);
    this.state = {
      reporteData: [],
    };
  }

  componentDidMount() {
    PruebaService.reporte().then((response) => {
      this.setState({ reporteData: response.data });
    });
  }

  render() {
    return (
        <div className="general">
            <div align="center" className="container-2">
                <h1 className="center">Reportes de los estudiante</h1>
                <table className="table">
                <thead className="thead-dark">
                    <tr>
                    <th>Rut</th>
                    <th>Nombre</th>
                    <th>Examenes rendidos</th>
                    <th>Promedio puntaje examenes</th>
                    <th>Total arancel a pagar</th>
                    <th>Tipo de pago</th>
                    <th>Total de cuotas pactadas</th>
                    <th>Cuotas pagadas</th>
                    <th>Cuotas con retraso</th>
                    <th>Monto total pagado</th>
                    <th>Saldo por pagar</th>
                    <th>Fecha de la ultima cuota de pago</th>
                    </tr>
                </thead>

                <tbody>
                    {this.state.reporteData.map((reporte) => (
                    <tr key={reporte.rut}>
                        <td>{reporte.rut}</td>
                        <td>{reporte.nombre}</td>
                        <td>{reporte.examenesRendidos}</td>
                        <td>{reporte.promedioPuntaje}</td>
                        <td>{reporte.arancel}</td>
                        <td>{reporte.tipoPago}</td>
                        <td>{reporte.cuotasPactadas}</td>
                        <td>{reporte.cuotasPagadas}</td>
                        <td>{reporte.cuotasAtrasadas}</td>
                        <td>{reporte.montoPagado}</td>
                        <td>{reporte.saldoaPagar}</td>
                        <td>{reporte.fechaUltimoCuota}</td>
                    </tr>
                    ))}
                </tbody>
                </table>
            <div className="d-flex justify-content-center align-items-center"></div>
            </div>
        </div>
    );
  }
}

export default Reporte;