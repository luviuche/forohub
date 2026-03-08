package com.challenge.forohub.controller;

import com.challenge.forohub.domain.curso.Curso;
import com.challenge.forohub.domain.curso.CursoRepository;
import com.challenge.forohub.domain.topico.*;
import com.challenge.forohub.domain.usuario.Usuario;
import com.challenge.forohub.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistro, UriComponentsBuilder uriBuilder) {

        // Regla de Negocio: No permitir tópicos duplicados
        if (topicoRepository.existsByTituloAndMensaje(datosRegistro.titulo(), datosRegistro.mensaje())) {
            return ResponseEntity.badRequest().body("Ya existe un tópico con el mismo título y mensaje.");
        }

        // Buscamos el Autor y el Curso en la base de datos
        // (En un caso real, manejaríamos la excepción si no existen, por ahora usamos orElseThrow genérico)
        Usuario autor = usuarioRepository.findById(datosRegistro.autorId())
                .orElseThrow(() -> new IllegalArgumentException("El autor con ID " + datosRegistro.autorId() + " no existe."));
        Curso curso = cursoRepository.findById(datosRegistro.cursoId())
                .orElseThrow(() -> new IllegalArgumentException("El curso con ID " + datosRegistro.cursoId() + " no existe."));

        // Creamos la entidad y la guardamos
        Topico topico = new Topico(datosRegistro, autor, curso);
        topicoRepository.save(topico);

        // Preparamos la respuesta HTTP 201 (Created) con la URL del nuevo recurso
        DatosRespuestaTopico datosRespuesta = new DatosRespuestaTopico(topico);
        URI url = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(url).body(datosRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listadoTopicos(@PageableDefault(size = 10, sort = {"fechaCreacion"}) Pageable paginacion) {
        // Obtenemos todos los tópicos de la base de datos y los convertimos a nuestro DTO
        Page<DatosListadoTopico> page = topicoRepository.findAll(paginacion).map(DatosListadoTopico::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> detallarTopico(@PathVariable Long id) {
        // Buscamos el tópico por su ID usando el Optional que nos devuelve Spring Data JPA
        var topicoOptional = topicoRepository.findById(id);

        // Verificamos si se encontró en la base de datos
        if (topicoOptional.isPresent()) {
            var topico = topicoOptional.get();
            // Si existe, devolvemos un 200 OK con los datos del tópico
            return ResponseEntity.ok(new DatosRespuestaTopico(topico));
        }

        // Si no existe, devolvemos un error 404 Not Found
        return ResponseEntity.notFound().build();
    }
}
