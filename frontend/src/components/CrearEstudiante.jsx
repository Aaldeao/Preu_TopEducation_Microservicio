import React, { useState } from "react";


function CrearEstudiante(props){
    const initialState = {
        rut: "",
        nombres: "",
        apellidos: "",
        fechaNacimiento: "",
        tipoColegio: "",
        nombreColegio: "",
        anoEgreso: "",
        tipoDepago: "",

    };

    const [estudiante, setEstudiante] = useState(initialState);
}
    export default CrearEstudiante;