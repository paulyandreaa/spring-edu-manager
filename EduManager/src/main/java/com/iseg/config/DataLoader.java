package com.iseg.config;

import com.iseg.model.Alumno;
import com.iseg.model.Usuario;
import com.iseg.repository.AlumnoRepository;
import com.iseg.repository.UsuarioRepository;
import com.iseg.util.CarreraUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final AlumnoRepository alumnoRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UsuarioRepository usuarioRepository, 
                      AlumnoRepository alumnoRepository,
                      PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.alumnoRepository = alumnoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        
        // ============ CREAR USUARIO ADMIN ============
        if (!usuarioRepository.existsByUsername("admin")) {
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ADMIN");
            admin.setEmail("admin@edumanager.com");
            admin.setActivo(true);
            usuarioRepository.save(admin);
            System.out.println("✅ Usuario ADMIN creado: admin / admin123");
        }

        // ============ CREAR 10 ALUMNOS DE EJEMPLO ============
        if (alumnoRepository.count() == 0) {
            
            // Alumno 1
            Alumno a1 = new Alumno();
            a1.setRut("12.345.678-9");
            a1.setNombre("Juan Carlos");
            a1.setPrimerApellido("Pérez");
            a1.setSegundoApellido("González");
            a1.setFechaNacimiento(LocalDate.of(2000, 5, 15));
            a1.setDireccion("Av. Providencia 1234, Depto 501");
            a1.setComuna("Providencia");
            a1.setCarrera(CarreraUtil.INGENIERIA_INFORMATICA);
            a1.setEmail("juan.perez@alumno.cl");
            a1.setTelefono("+56912345678");
            a1.setMatriculado(true);
            alumnoRepository.save(a1);

            // Alumno 2
            Alumno a2 = new Alumno();
            a2.setRut("15.678.901-2");
            a2.setNombre("María Fernanda");
            a2.setPrimerApellido("Rodríguez");
            a2.setSegundoApellido("Silva");
            a2.setFechaNacimiento(LocalDate.of(1999, 8, 22));
            a2.setDireccion("Calle Los Leones 567");
            a2.setComuna("Santiago");
            a2.setCarrera(CarreraUtil.MEDICINA);
            a2.setEmail("maria.rodriguez@alumno.cl");
            a2.setTelefono("+56923456789");
            a2.setMatriculado(true);
            alumnoRepository.save(a2);

            // Alumno 3
            Alumno a3 = new Alumno();
            a3.setRut("18.234.567-3");
            a3.setNombre("Pedro Antonio");
            a3.setPrimerApellido("Martínez");
            a3.setSegundoApellido("López");
            a3.setFechaNacimiento(LocalDate.of(2001, 3, 10));
            a3.setDireccion("Av. Libertador 890");
            a3.setComuna("Ñuñoa");
            a3.setCarrera(CarreraUtil.DERECHO);
            a3.setEmail("pedro.martinez@alumno.cl");
            a3.setTelefono("+56934567890");
            a3.setMatriculado(true);
            alumnoRepository.save(a3);

            // Alumno 4
            Alumno a4 = new Alumno();
            a4.setRut("20.456.789-4");
            a4.setNombre("Camila Andrea");
            a4.setPrimerApellido("Sánchez");
            a4.setSegundoApellido("Torres");
            a4.setFechaNacimiento(LocalDate.of(2002, 11, 5));
            a4.setDireccion("Calle Manuel Montt 234");
            a4.setComuna("Providencia");
            a4.setCarrera(CarreraUtil.PSICOLOGIA);
            a4.setEmail("camila.sanchez@alumno.cl");
            a4.setTelefono("+56945678901");
            a4.setMatriculado(false);
            alumnoRepository.save(a4);

            // Alumno 5
            Alumno a5 = new Alumno();
            a5.setRut("11.987.654-5");
            a5.setNombre("Diego Alejandro");
            a5.setPrimerApellido("Herrera");
            a5.setSegundoApellido("Muñoz");
            a5.setFechaNacimiento(LocalDate.of(1998, 7, 18));
            a5.setDireccion("Av. Irarrázaval 1500");
            a5.setComuna("Ñuñoa");
            a5.setCarrera(CarreraUtil.INGENIERIA_CIVIL);
            a5.setEmail("diego.herrera@alumno.cl");
            a5.setTelefono("+56956789012");
            a5.setMatriculado(true);
            alumnoRepository.save(a5);

            // Alumno 6
            Alumno a6 = new Alumno();
            a6.setRut("17.345.678-6");
            a6.setNombre("Valentina Sofía");
            a6.setPrimerApellido("Castro");
            a6.setSegundoApellido("Vargas");
            a6.setFechaNacimiento(LocalDate.of(2000, 1, 25));
            a6.setDireccion("Calle Bellavista 456");
            a6.setComuna("Recoleta");
            a6.setCarrera(CarreraUtil.ARQUITECTURA);
            a6.setEmail("valentina.castro@alumno.cl");
            a6.setTelefono("+56967890123");
            a6.setMatriculado(true);
            alumnoRepository.save(a6);

            // Alumno 7
            Alumno a7 = new Alumno();
            a7.setRut("19.876.543-7");
            a7.setNombre("Sebastián Matías");
            a7.setPrimerApellido("Flores");
            a7.setSegundoApellido("Rojas");
            a7.setFechaNacimiento(LocalDate.of(2001, 9, 12));
            a7.setDireccion("Av. Santa María 789");
            a7.setComuna("Vitacura");
            a7.setCarrera(CarreraUtil.INGENIERIA_COMERCIAL);
            a7.setEmail("sebastian.flores@alumno.cl");
            a7.setTelefono("+56978901234");
            a7.setMatriculado(true);
            alumnoRepository.save(a7);

            // Alumno 8
            Alumno a8 = new Alumno();
            a8.setRut("16.543.210-8");
            a8.setNombre("Isabella Antonella");
            a8.setPrimerApellido("Morales");
            a8.setSegundoApellido("Ortiz");
            a8.setFechaNacimiento(LocalDate.of(1999, 4, 30));
            a8.setDireccion("Calle Apoquindo 321");
            a8.setComuna("Las Condes");
            a8.setCarrera(CarreraUtil.ENFERMERIA);
            a8.setEmail("isabella.morales@alumno.cl");
            a8.setTelefono("+56989012345");
            a8.setMatriculado(false);
            alumnoRepository.save(a8);

            // Alumno 9
            Alumno a9 = new Alumno();
            a9.setRut("14.321.098-9");
            a9.setNombre("Matías Ignacio");
            a9.setPrimerApellido("Vega");
            a9.setSegundoApellido("Cortés");
            a9.setFechaNacimiento(LocalDate.of(2002, 6, 8));
            a9.setDireccion("Av. Vicuña Mackenna 654");
            a9.setComuna("Macul");
            a9.setCarrera(CarreraUtil.PERIODISMO);
            a9.setEmail("matias.vega@alumno.cl");
            a9.setTelefono("+56990123456");
            a9.setMatriculado(true);
            alumnoRepository.save(a9);

            // Alumno 10
            Alumno a10 = new Alumno();
            a10.setRut("13.210.987-K");
            a10.setNombre("Sofía Valentina");
            a10.setPrimerApellido("Ramírez");
            a10.setSegundoApellido("Díaz");
            a10.setFechaNacimiento(LocalDate.of(2000, 12, 20));
            a10.setDireccion("Calle Pedro de Valdivia 987");
            a10.setComuna("Providencia");
            a10.setCarrera(CarreraUtil.PEDAGOGIA);
            a10.setEmail("sofia.ramirez@alumno.cl");
            a10.setTelefono("+56901234567");
            a10.setMatriculado(true);
            alumnoRepository.save(a10);

            System.out.println("✅ 10 alumnos de ejemplo creados exitosamente");
        } else {
            System.out.println("ℹ️ Ya existen alumnos en la base de datos, no se crearon duplicados");
        }
    }
}