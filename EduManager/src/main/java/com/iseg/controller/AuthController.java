package com.iseg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iseg.model.Usuario;
import com.iseg.service.UsuarioService;

import jakarta.validation.Valid;

@Controller
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/guardarRegistro")
    public String guardarRegistro(@Valid @ModelAttribute Usuario usuario,
                                  BindingResult result,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            return "registro";
        }

        if (usuarioService.existeUsername(usuario.getUsername())) {
            model.addAttribute("errorUsername", "El nombre de usuario ya existe");
            return "registro";
        }

        if (usuarioService.existeEmail(usuario.getEmail())) {
            model.addAttribute("errorEmail", "El email ya está registrado");
            return "registro";
        }

        usuario.setRole("USER");
        usuario.setActivo(true);
        usuarioService.registrarUsuario(usuario);

        redirectAttributes.addFlashAttribute("exito", 
            "Registro exitoso. Ahora puede iniciar sesión.");
        return "redirect:/login";
    }
}