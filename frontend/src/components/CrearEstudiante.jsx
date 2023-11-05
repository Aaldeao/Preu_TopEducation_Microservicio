import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Swal from 'sweetalert2';
import EstudianteService from "../services/EstudianteService";

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
    const navigate = useNavigate();
    const navigateHome = () => {
        navigate("/");
    };

    const changeRutHandler = event => {
        setEstudiante({ ...estudiante, rut: event.target.value });
    };

    const changeNombresHandler = event => {
        setEstudiante({ ...estudiante, nombres: event.target.value });
    };

    const changeApellidosHandler = event => {
        setEstudiante({ ...estudiante, apellidos: event.target.value });
    };

    const changeFechaNacimientoHandler = event => {
        setEstudiante({ ...estudiante, fechaNacimiento: event.target.value });
    };

    const changeTipoColegioHandler = event => {
        setEstudiante({ ...estudiante, tipoColegio: event.target.value });
    };

    const changeNombreColegioHandler = event => {
        setEstudiante({ ...estudiante, nombreColegio: event.target.value });
    };

    const changeAnoEgresoHandler = event => {
        setEstudiante({ ...estudiante, anoEgreso: event.target.value });
    };

    const changeTipoDepagoHandler = event => {
        setEstudiante({ ...estudiante, tipoDepago: event.target.value });
    };

    const ingresarEstudiante = (event) => {
        Swal.fire({
            title: "¿Confirma los datos del estudiante?",
            text: "No podran editarse en caso de equivocación",
            icon: "question",
            showDenyButton: true,
            confirmButtonText: "Confirmar",
            confirmButtonColor: "rgb(68, 194, 68)",
            denyButtonText: "Cancelar",
            denyButtonColor: "rgb(190, 54, 54)",
        }).then((result) => {
            if (result.isConfirmed) {
                let newEstudiante = {
                    rut: estudiante.rut,
                    nombres: estudiante.nombres,
                    apellidos: estudiante.apellidos,
                    fechaNacimiento: estudiante.fechaNacimiento,
                    tipoColegio: estudiante.tipoColegio,
                    nombreColegio: estudiante.nombreColegio,
                    anoEgreso: estudiante.anoEgreso,
                    tipoDepago: estudiante.tipoDepago,
                };
                console.log(newEstudiante);
                EstudianteService.createEstudiante(newEstudiante);

                Swal.fire({
                    title: "Enviado",
                    timer: 2000,
                    icon: "success",
                    timerProgressBar: true,
                    didOpen: () => {
                        Swal.showLoading()
                    },
                });
                navigateHome();
            }
        });
    };

    return (
        <div className="general">
            <div className="container-create">
                <Form>
                    <Form.Group className="mb-3" controlId="rut" value={estudiante.rut} onChange={changeRutHandler}>
                        <Form.Label className="agregar">Rut:</Form.Label>
                        <Form.Control className="agregar" type="text" name="rut" />
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="nombres" value={estudiante.nombres} onChange={changeNombresHandler}>
                        <Form.Label className="agregar">Nombres:</Form.Label>
                        <Form.Control className="agregar" type="text" name="nombres" />
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="apellidos" value={estudiante.apellidos} onChange={changeApellidosHandler}>
                        <Form.Label className="agregar">Apellidos:</Form.Label>
                        <Form.Control className="agregar" type="text" name="apellidos" />
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="fechaNacimiento" value={estudiante.fechaNacimiento} onChange={changeFechaNacimientoHandler}>
                        <Form.Label className="agregar">Fecha de Nacimiento:</Form.Label>
                        <Form.Control className="agregar" type="date" name="fechaNacimiento" />
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="tipoColegio" value={estudiante.tipoColegio} onChange={changeTipoColegioHandler}>
                        <Form.Label className="agregar">Tipo Colegio:</Form.Label>
                        <Form.Select as="select" name="tipoColegio">
                            <option value="Municipal">Municipal</option>
                            <option value="Subvencionado">Subvencionado</option>
                            <option value="Privado">Privado</option>
                        </Form.Select>
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="nombreColegio" value={estudiante.nombreColegio} onChange={changeNombreColegioHandler}>
                        <Form.Label className="agregar">Nombre del Colegio:</Form.Label>
                        <Form.Control className="agregar" type="text" name="nombreColegio" />
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="anoEgreso" value={estudiante.anoEgreso} onChange={changeAnoEgresoHandler}>
                        <Form.Label className="agregar">Año de Egreso del Colegio:</Form.Label>
                        <Form.Control className="agregar" type="text" name="anoEgreso" />
                    </Form.Group>

                    <Form.Group className="mb-3" controlId="tipoDepago" value={estudiante.tipoDepago} onChange={changeTipoDepagoHandler}>
                        <Form.Label className="agregar">Tipo de Pago:</Form.Label>
                        <Form.Select className="agregar" type="text" name="tipoDepago">
                            <option value="Contado">Contado</option>
                            <option value="Cuotas">Cuotas</option>
                        </Form.Select>
                    </Form.Group>

                    <Button className="boton" onClick={ingresarEstudiante}>Añadir Estudiante</Button>
                </Form>
            </div>
        </div>
    );
}
    export default CrearEstudiante;