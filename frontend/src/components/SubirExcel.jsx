import React, { useState } from 'react';
import axios from 'axios';

const FileUploadComponent = () => {
  const [selectedFile, setSelectedFile] = useState(null);

  const handleFileChange = (event) => {
    setSelectedFile(event.target.files[0]);
  };

  const handleUpload = async () => {
    if (selectedFile) {
      try {
        const formData = new FormData();
        formData.append('pruebaExcel', selectedFile);

        const response = await axios.post('http://localhost:8084/prueba/subirExcel', formData, {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
        });

        console.log('Respuesta del servidor:', response.data);

      } catch (error) {
        console.error('Error al cargar el archivo:', error);
      }
    } else {
      console.error('Selecciona un archivo antes de cargarlo.');
    }
  };

  return (
    <div>
      <h2>Cargar Archivo</h2>
      <input type="file" onChange={handleFileChange} />
      <button onClick={handleUpload}>Subir Archivo</button>
    </div>
  );
};

export default FileUploadComponent;