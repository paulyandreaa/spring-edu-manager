package com.iseg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public SecurityConfig() {
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // 👈 Deshabilitar CSRF para simplificar
            .authorizeHttpRequests(auth -> auth
                // Rutas PÚBLICAS (cualquiera puede acceder)
                .requestMatchers("/", "/index", "/login", "/loginProcess", 
                                 "/alumno/buscar", "/alumno/buscar/**",
                                 "/css/**", "/js/**", "/images/**",
                                 "/error/**").permitAll()
                // Rutas solo para ADMIN
                .requestMatchers("/alumno/lista", "/alumno/nuevo", "/alumno/guardar", 
                                 "/alumno/editar/**", "/alumno/actualizar/**",
                                 "/alumno/eliminar/**", "/alumno/detalle/**").hasRole("ADMIN")
                // Cualquier otra ruta requiere autenticación
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/loginProcess")
                .defaultSuccessUrl("/alumno/lista", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
            .exceptionHandling(ex -> ex
                .accessDeniedPage("/error/403")
            );

        return http.build();
    }
}