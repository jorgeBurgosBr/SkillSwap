package com.skill_swap.servicios;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skill_swap.entidades.Usuario;
import com.skill_swap.repositorios.UsuarioRepositorio;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Método para obtener todos los usuarios
    @Transactional(readOnly = true)
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepositorio.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepositorio.findById(id);
    }
    
    @Transactional(readOnly = true)
    public Boolean findByEmail(String email) {
        return usuarioRepositorio.findByEmail(email) != null;
    }

    // Método para crear o actualizar un usuario
    @Transactional
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    @Transactional
    public Usuario actualizarUsuario(Long id, Usuario usuario) {
        try {
            Usuario usuarioAModificar = usuarioRepositorio.findById(id).orElseThrow();
            // El id se queda como estaba
            usuarioAModificar.setId(id);
            usuarioAModificar.setNombre(usuario.getNombre());
            usuarioAModificar.setApellido(usuario.getApellido());
            usuarioAModificar.setEmail(usuario.getEmail());
            usuarioAModificar.setFotoDePerfil(usuario.getFotoDePerfil());
            usuarioAModificar.setContrasena(usuario.getContrasena());
            usuarioAModificar.setUrlGitHub(usuario.getUrlGitHub());
            usuarioAModificar.setSkills(usuario.getSkills());
            return usuarioRepositorio.save(usuarioAModificar);
        } catch (NoSuchElementException e) {
            // Manejo de la excepción si el usuario no existe
            return null;
        }
    }

    @Transactional
    public Boolean borrarUsuario(Long id) {
        try {
            usuarioRepositorio.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @Transactional(readOnly = true)
    public Usuario login(String email, String contrasena) {
        return usuarioRepositorio.login(email, contrasena);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioRepositorio.findByEmail(email);

        if (usuarioOptional.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Usuario %s not found", email));
        }

        Usuario usuario = usuarioOptional.orElseThrow();

        List<GrantedAuthority> authorities = usuario.getRoles().stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(usuario.getEmail(),
                usuario.getContrasena(),
                usuario.isEnabled(),
                true,
                true,
                true,
                authorities);
    }
}

