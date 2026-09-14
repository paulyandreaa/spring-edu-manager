package com.iseg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.iseg.model.Alumno;
import com.iseg.repository.AlumnoRepository;
import com.iseg.util.CarreraUtil;

@Service
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;

    public AlumnoService(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    // Listar todos los alumnos
    public List<Alumno> listarTodos() {
        return alumnoRepository.findAll();
    }

    // Buscar alumno por ID
    public Optional<Alumno> buscarPorId(Long id) {
        return alumnoRepository.findById(id);
    }

    // Buscar alumno por RUT
    public Optional<Alumno> buscarPorRut(String rut) {
        return alumnoRepository.findByRut(rut);
    }

    // Guardar alumno (crear o actualizar)
    public Alumno guardar(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    // Eliminar alumno
    public void eliminar(Long id) {
        alumnoRepository.deleteById(id);
    }

    // Verificar si existe un RUT
    public boolean existePorRut(String rut) {
        return alumnoRepository.existsByRut(rut);
    }

    // Verificar si existe un Email
    public boolean existePorEmail(String email) {
        return alumnoRepository.existsByEmail(email);
    }

    // Búsqueda personalizada 1: Por texto (nombre, apellido o RUT)
    public List<Alumno> buscarPorTexto(String texto) {
        return alumnoRepository.buscarPorNombreORut(texto);
    }

    // Búsqueda personalizada 2: Por carrera y matrícula
    public List<Alumno> buscarPorCarreraYMatricula(CarreraUtil carrera, Boolean matriculado) {
        return alumnoRepository.buscarPorCarreraYMatricula(carrera, matriculado);
    }

    // Búsqueda por comuna
    public List<Alumno> buscarPorComuna(String comuna) {
        return alumnoRepository.findByComunaIgnoreCase(comuna);
    }

    // Listar solo alumnos matriculados
    public List<Alumno> listarMatriculados() {
        return alumnoRepository.findByMatriculadoTrue();
    }
}