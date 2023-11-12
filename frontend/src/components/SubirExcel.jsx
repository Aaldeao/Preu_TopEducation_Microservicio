import React, { useState } from 'react';
import { useNavigate } from "react-router-dom";
import PruebaService from "../services/PruebaService";

const SubirArchivo = () => {
  const [selectedFile, setSelectedFile] = useState(null);
  const navigate = useNavigate();
    const navigateHome = () => {
        navigate("/");
    };


  const archivoHandler = async () => {
    if (selectedFile) {
      const formData = new FormData();
      formData.append('prueba', selectedFile);

      try {
        const response = await PruebaService.subirExcel(formData);
        if (response.status === 200) {
          console.log('Archivo subido exitosamente');
          navigateHome();
        } else {
          alert('Error al subir el archivo');
          console.error('Error al subir el archivo');
        }
      } catch (e) {
        console.error(e);
      }
    }
  };

  const handleFileChange = (e) => {
    const archivo = e.target.files[0];
    setSelectedFile(archivo);
  };

  return (
    <div align="center" className="container-2">
      <h2>Subir archivo</h2>
      <input type="file" accept=".csv" onChange={handleFileChange} />
      <button onClick={archivoHandler}>Subir</button>
    </div>
  );
};

export default SubirArchivo;