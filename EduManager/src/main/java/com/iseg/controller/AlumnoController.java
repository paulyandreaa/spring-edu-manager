package com.iseg.controller;

import com.iseg.model.Alumno;
import com.iseg.service.AlumnoService;
import com.iseg.util.CarreraUtil;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {

    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    // ============ RUTAS PÚBLICAS (cualquiera puede buscar) ============

    @GetMapping("/buscar")
    public String mostrarBuscar(Model model) {
        model.addAttribute("carreras", CarreraUtil.values());
        return "alumno/buscar";
    }

    @GetMapping("/buscar/texto")
    public String buscarPorTexto(@RequestParam String texto, Model model) {
        List<Alumno> resultados = alumnoService.buscarPorTexto(texto);
        model.addAttribute("resultados", resultados);
        model.addAttribute("texto", texto);
        model.addAttribute("carreras", CarreraUtil.values());
        return "alumno/buscar";
    }

    @GetMapping("/buscar/carrera")
    public String buscarPorCarrera(@RequestParam CarreraUtil carrera,
                                   @RequestParam Boolean matriculado,
                                   Model model) {
        List<Alumno> resultados = alumnoService
            .buscarPorCarreraYMatricula(carrera, matriculado);
        model.addAttribute("resultados", resultados);
        model.addAttribute("carreraSeleccionada", carrera);
        model.addAttribute("matriculadoSeleccionado", matriculado);
        model.addAttribute("carreras", CarreraUtil.values());
        return "alumno/buscar";
    }

    // ============ RUTAS SOLO PARA ADMIN ============

    @GetMapping("/lista")
    public String listar(Model model) {
        model.addAttribute("alumnos", alumnoService.listarTodos());
        return "alumno/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("alumno", new Alumno());
        model.addAttribute("carreras", CarreraUtil.values());
        return "alumno/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Alumno alumno,
                         BindingResult result,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            model.addAttribute("carreras", CarreraUtil.values());
            return "alumno/form";
        }

        if (alumnoService.existePorRut(alumno.getRut())) {
            model.addAttribute("errorRut", "El RUT ya está registrado");
            model.addAttribute("carreras", CarreraUtil.values());
            return "alumno/form";
        }

        if (alumnoService.existePorEmail(alumno.getEmail())) {
            model.addAttribute("errorEmail", "El email ya está registrado");
            model.addAttribute("carreras", CarreraUtil.values());
            return "alumno/form";
        }

        alumnoService.guardar(alumno);
        redirectAttributes.addFlashAttribute("exito", "✅ Alumno registrado correctamente");
        return "redirect:/alumno/lista";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Alumno alumno = alumnoService.buscarPorId(id)
            .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
        model.addAttribute("alumno", alumno);
        model.addAttribute("carreras", CarreraUtil.values());
        return "alumno/form";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Long id,
                            @Valid @ModelAttribute Alumno alumno,
                            BindingResult result,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            model.addAttribute("carreras", CarreraUtil.values());
            return "alumno/form";
        }

        alumno.setId(id);
        alumnoService.guardar(alumno);
        redirectAttributes.addFlashAttribute("exito", "✅ Alumno actualizado correctamente");
        return "redirect:/alumno/lista";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        alumnoService.eliminar(id);
        redirectAttributes.addFlashAttribute("exito", "🗑️ Alumno eliminado correctamente");
        return "redirect:/alumno/lista";
    }

    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        Alumno alumno = alumnoService.buscarPorId(id)
            .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
        model.addAttribute("alumno", alumno);
        return "alumno/detalle";
    }
}