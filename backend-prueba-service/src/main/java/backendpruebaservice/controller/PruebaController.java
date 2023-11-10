package backendpruebaservice.controller;

import backendpruebaservice.service.PruebaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Prueba")
public class PruebaController {
    @Autowired
    PruebaService pruebaService;
    /*
    @GetMapping("/subirArchivoExcel") // Devuelve la vista para subir el archivo excel //
    public String subirArchivo(){
        return "subirArchivoExcel";
    }

    // Recibe el archivo .csv para guardarlo en la base de datos //
    @PostMapping("/SubirExcel")
    public String subirExcel(@RequestParam("archivo_excel") MultipartFile pruebaExcel, Model model) {
        pruebaService.guardar(pruebaExcel);
        String resultado = pruebaService.cargarCsv(pruebaExcel);
        model.addAttribute("resultado", resultado);
        return "index";
    }
     */
}
