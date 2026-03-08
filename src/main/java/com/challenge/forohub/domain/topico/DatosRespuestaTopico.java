package com.challenge.forohub.domain.topico;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        String status,
        Long autorId,
        Long cursoId,
        LocalDateTime fechaCreacion
) {
    // Un constructor extra para mapear desde la Entidad al DTO fácilmente
    public DatosRespuestaTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getStatus().toString(),
                topico.getAutor().getId(), topico.getCurso().getId(), topico.getFechaCreacion());
    }
}
