package com.iseg.controller;

import com.iseg.model.Alumno;
import com.iseg.service.AlumnoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    private final AlumnoService alumnoService;

    public HomeController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping("/")
    public String index(@RequestParam(required = false) String texto, Model model) {
        if (texto != null && !texto.isEmpty()) {
            List<Alumno> resultados = alumnoService.buscarPorTexto(texto);
            model.addAttribute("resultados", resultados);
            model.addAttribute("texto", texto);
        }
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}