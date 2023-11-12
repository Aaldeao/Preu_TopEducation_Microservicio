import axios from "axios";

const PRUEBA_API_URL = "http://localhost:8080/Prueba";

class PruebaService {
  subirExcel(formData){
    return axios.post(PRUEBA_API_URL + "/subirExcel" , formData);
  }
  reporte(){
    return axios.get(PRUEBA_API_URL + "/reporte");
  }
}

export default new PruebaService();