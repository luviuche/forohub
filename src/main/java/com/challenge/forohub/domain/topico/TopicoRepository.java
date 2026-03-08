package com.challenge.forohub.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    // Spring Boot detecta el nombre del metodo y crea la consulta SQL automáticamente
    boolean existsByTituloAndMensaje(String titulo, String mensaje);
    // Busca si existe el título y mensaje, pero ignorando el ID del tópico que estamos actualizando
    boolean existsByTituloAndMensajeAndIdNot(String titulo, String mensaje, Long id);
}
