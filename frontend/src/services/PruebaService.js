import axios from "axios";

const PRUEBA_API_URL = "http://localhost:8084/prueba";
const prueba = axios.create({ baseURL: PRUEBA_API_URL });

class PruebaService {
  async subirExcel(formData) {
    try {
      const res = await prueba.post("/subirExcel", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      });
      return res.data;
    } catch (error) {
      throw error;
    }
  }
}

export default new PruebaService();