package com.iseg.model;

import java.time.LocalDate;

import com.iseg.util.CarreraUtil;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "alumnos")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 12)
    @NotBlank(message = "El RUT es obligatorio")
    @Pattern(regexp = "^\\d{1,2}\\.\\d{3}\\.\\d{3}-[\\dkK]$", 
             message = "Formato de RUT inválido (ej: 12.345.678-9)")
    private String rut;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;

    @Column(name = "primer_apellido", nullable = false, length = 50)
    @NotBlank(message = "El primer apellido es obligatorio")
    @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
    private String primerApellido;

    @Column(name = "segundo_apellido", length = 50)
    @Size(max = 50, message = "El segundo apellido no debe superar 50 caracteres")
    private String segundoApellido;

    @Column(name = "fecha_nacimiento", nullable = false)
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    private LocalDate fechaNacimiento;

    @Column(nullable = false, length = 150)
    @NotBlank(message = "La dirección es obligatoria")
    @Size(min = 5, max = 150, message = "La dirección debe tener entre 5 y 150 caracteres")
    private String direccion;

    @Column(nullable = false, length = 80)
    @NotBlank(message = "La comuna es obligatoria")
    private String comuna;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "La carrera es obligatoria")
    private CarreraUtil carrera;

    @Column(nullable = false, unique = true, length = 100)
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Formato de email inválido")
    private String email;

    @Column(nullable = false, length = 15)
    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^\\+?\\d{8,15}$", 
             message = "Formato de teléfono inválido")
    private String telefono;

    @Column(nullable = false)
    @NotNull(message = "Debe indicar si está matriculado")
    private Boolean matriculado;

    // Constructores
    public Alumno() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRut() { return rut; }
    public void setRut(String rut) { this.rut = rut; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPrimerApellido() { return primerApellido; }
    public void setPrimerApellido(String primerApellido) { this.primerApellido = primerApellido; }

    public String getSegundoApellido() { return segundoApellido; }
    public void setSegundoApellido(String segundoApellido) { this.segundoApellido = segundoApellido; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getComuna() { return comuna; }
    public void setComuna(String comuna) { this.comuna = comuna; }

    public CarreraUtil getCarrera() { return carrera; }
    public void setCarrera(CarreraUtil carrera) { this.carrera = carrera; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public Boolean getMatriculado() { return matriculado; }
    public void setMatriculado(Boolean matriculado) { this.matriculado = matriculado; }

    // Método helper para nombre completo
    public String getNombreCompleto() {
        return nombre + " " + primerApellido + " " + 
               (segundoApellido != null ? segundoApellido : "");
    }
}