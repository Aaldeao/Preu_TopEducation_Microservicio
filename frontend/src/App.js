import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import Main from './components/Main';
import HeaderComponent from './components/HeaderComponent';
import CrearEstudiante from './components/CrearEstudiante';

function App() {
  return (
    <div>
      <Router>
        <HeaderComponent />
        <div className="container">
          <Routes> 
            <Route path="/" element={<Main />} />
            <Route path="/crear-estudiante" element={<CrearEstudiante />} />
          </Routes>
        </div>
      </Router>
    </div>
  );
}

export default App;
