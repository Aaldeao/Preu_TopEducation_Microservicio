import axios from "axios";

const CUOTA_API_URL = "http://localhost:8080/Cuota";

class CuotaService {
  generarCuotas(rut) {
    return axios.get(`${CUOTA_API_URL}/generar/${rut}`);
  }
}

export default new CuotaService();