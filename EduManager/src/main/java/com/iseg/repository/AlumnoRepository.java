package com.iseg.repository;

import com.iseg.model.Alumno;
import com.iseg.util.CarreraUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    // Buscar por RUT
    Optional<Alumno> findByRut(String rut);
    
    // Buscar por Email
    Optional<Alumno> findByEmail(String email);
    
    // Verificar si existe un RUT
    boolean existsByRut(String rut);
    
    // Verificar si existe un Email
    boolean existsByEmail(String email);
    
    // Búsqueda personalizada 1: Por nombre, apellido o RUT
    @Query("SELECT a FROM Alumno a WHERE " +
           "LOWER(a.nombre) LIKE LOWER(CONCAT('%', :texto, '%')) OR " +
           "LOWER(a.primerApellido) LIKE LOWER(CONCAT('%', :texto, '%')) OR " +
           "LOWER(a.segundoApellido) LIKE LOWER(CONCAT('%', :texto, '%')) OR " +
           "LOWER(a.rut) LIKE LOWER(CONCAT('%', :texto, '%'))")
    List<Alumno> buscarPorNombreORut(@Param("texto") String texto);

    // Búsqueda personalizada 2: Por carrera y estado de matrícula
    @Query("SELECT a FROM Alumno a WHERE a.carrera = :carrera AND a.matriculado = :matriculado")
    List<Alumno> buscarPorCarreraYMatricula(
        @Param("carrera") CarreraUtil carrera, 
        @Param("matriculado") Boolean matriculado
    );

    // Búsqueda por comuna
    List<Alumno> findByComunaIgnoreCase(String comuna);
    
    // Listar alumnos matriculados
    List<Alumno> findByMatriculadoTrue();
}