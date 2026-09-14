package com.iseg.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.iseg.model.Usuario;
import com.iseg.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder passwordEncoder;

	public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
	    this.usuarioRepository = usuarioRepository;
	    this.passwordEncoder = passwordEncoder;
	}

    // Método para cargar usuario por username (requerido por Spring Security)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(
                "Usuario no encontrado: " + username));

        return new User(
            usuario.getUsername(),
            usuario.getPassword(),
            usuario.getActivo(),
            true, true, true,
            Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + usuario.getRole())
            )
        );
    }

    // Registrar nuevo usuario (con password hasheado)
    public Usuario registrarUsuario(Usuario usuario) {
        // Hashear password con BCrypt
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    // Verificar si existe un username
    public boolean existeUsername(String username) {
        return usuarioRepository.existsByUsername(username);
    }

    // Verificar si existe un email
    public boolean existeEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }
}