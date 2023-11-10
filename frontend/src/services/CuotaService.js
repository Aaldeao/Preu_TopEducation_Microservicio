import axios from "axios";

const CUOTA_API_URL = "http://localhost:8080/Cuota";

class CuotaService {
  generarCuotas(rut) {
    return axios.get(CUOTA_API_URL + `/generar/${rut}`);
  }

  mostarCuota(rut){
    return axios.get(CUOTA_API_URL + `/buscarcuota/${rut}`);
  }
  pagarCuota(idCuota){
    return axios.post(CUOTA_API_URL + `/pagocuota/${idCuota}`)
  }
  atrasadaCuota(idCuota){
    return axios.post(CUOTA_API_URL + `/atrasadacuota/${idCuota}`)
  }
}

export default new CuotaService();