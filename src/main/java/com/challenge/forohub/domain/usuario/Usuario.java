package com.challenge.forohub.domain.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String correoElectronico;
    private String contrasena;

    // --- Métodos de la interfaz UserDetails ---

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Por ahora, le asignaremos un rol por defecto a todos los usuarios
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return contrasena; // Le decimos a Spring que use nuestro campo 'contrasena'
    }

    @Override
    public String getUsername() {
        return correoElectronico; // Le decimos a Spring que el "usuario" será el correo
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Retornamos true para indicar que la cuenta no ha expirado
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Retornamos true para indicar que la cuenta no está bloqueada
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Retornamos true para indicar que la contraseña no ha expirado
    }

    @Override
    public boolean isEnabled() {
        return true; // Retornamos true para indicar que el usuario está habilitado
    }
}
