package com.challenge.forohub.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Spring Boot construye la consulta automáticamente basándose en el nombre del metodo
    UserDetails findByCorreoElectronico(String correoElectronico);
}
