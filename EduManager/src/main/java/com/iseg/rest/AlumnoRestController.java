package com.iseg.rest;

import com.iseg.model.Alumno;
import com.iseg.service.AlumnoService;
import com.iseg.util.CarreraUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoRestController {

    private final AlumnoService alumnoService;

    public AlumnoRestController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping
    public ResponseEntity<List<Alumno>> listarTodos() {
        return ResponseEntity.ok(alumnoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Alumno> alumno = alumnoService.buscarPorId(id);
        if (alumno.isPresent()) {
            return ResponseEntity.ok(alumno.get());
        }
        Map<String, String> error = new HashMap<>();
        error.put("mensaje", "Alumno con ID " + id + " no encontrado");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<?> buscarPorRut(@PathVariable String rut) {
        Optional<Alumno> alumno = alumnoService.buscarPorRut(rut);
        if (alumno.isPresent()) {
            return ResponseEntity.ok(alumno.get());
        }
        Map<String, String> error = new HashMap<>();
        error.put("mensaje", "Alumno con RUT " + rut + " no encontrado");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Alumno>> buscarPorTexto(@RequestParam String texto) {
        return ResponseEntity.ok(alumnoService.buscarPorTexto(texto));
    }

    @GetMapping("/buscar/carrera")
    public ResponseEntity<List<Alumno>> buscarPorCarrera(
            @RequestParam CarreraUtil carrera,
            @RequestParam Boolean matriculado) {
        return ResponseEntity.ok(alumnoService.buscarPorCarreraYMatricula(carrera, matriculado));
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Alumno alumno, 
                                   BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(procesarErrores(result));
        }

        if (alumnoService.existePorRut(alumno.getRut())) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", "El RUT ya está registrado");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }

        Alumno nuevo = alumnoService.guardar(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @Valid @RequestBody Alumno alumno,
                                        BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(procesarErrores(result));
        }

        Optional<Alumno> existente = alumnoService.buscarPorId(id);
        if (!existente.isPresent()) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", "Alumno con ID " + id + " no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        alumno.setId(id);
        return ResponseEntity.ok(alumnoService.guardar(alumno));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Alumno> existente = alumnoService.buscarPorId(id);
        if (!existente.isPresent()) {
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", "Alumno con ID " + id + " no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        alumnoService.eliminar(id);
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Alumno eliminado correctamente");
        return ResponseEntity.ok(respuesta);
    }

    private Map<String, Object> procesarErrores(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        Map<String, String> erroresCampo = new HashMap<>();
        
        result.getFieldErrors().forEach(error -> 
            erroresCampo.put(error.getField(), error.getDefaultMessage())
        );
        
        errores.put("mensaje", "Error de validación");
        errores.put("errores", erroresCampo);
        return errores;
    }
}