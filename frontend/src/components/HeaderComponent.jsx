import React from 'react';
import { Nav, Navbar, Container, Dropdown } from 'react-bootstrap';
import { Link } from 'react-router-dom';

const HeaderComponent = () => {
  return (
    <div>
      <header>
        <Navbar bg="dark" variant="dark" expand="md">
          <Container>
            <Link to="/" className="navbar-brand">Preuniversitario Top Education</Link>

            <Dropdown>
              <Dropdown.Toggle variant="dark" id="dropdown-basic">
                Menu
              </Dropdown.Toggle>

              <Dropdown.Menu>
                <Dropdown.Item>
                  <Link to="/" className="nav-link">
                    Inicio
                  </Link>
                </Dropdown.Item>
                <Dropdown.Item>
                  <Link to="/crear-alumno" className="nav-link">
                    Añadir Estudiante
                  </Link>
                </Dropdown.Item>
                <Dropdown.Item>
                  <Link to="/lista-estudiantes" className="nav-link">
                    Lista de Estudiantes
                  </Link>
                </Dropdown.Item>
                <Dropdown.Item>
                  <Link to="/lista-estudiantes" className="nav-link">
                    Generar Cuotas
                  </Link>
                </Dropdown.Item>
                <Dropdown.Item>
                  <Link to="/lista-estudiantes" className="nav-link">
                  Mostrar Cuotas
                  </Link>
                </Dropdown.Item>
                <Dropdown.Item>
                  <Link to="/lista-estudiantes" className="nav-link">
                  Subir Archivo
                  </Link>
                </Dropdown.Item>
                <Dropdown.Item>
                  <Link to="/lista-estudiantes" className="nav-link">
                  Mostrar Reporte
                  </Link>
                </Dropdown.Item>
              </Dropdown.Menu>
              
            </Dropdown>

          </Container>
        </Navbar>
      </header>
    </div>
  );
};

export default HeaderComponent;