package com.challenge.forohub.controller;

import com.challenge.forohub.domain.usuario.DatosAutenticacionUsuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacion) {
        // Spring requiere que empaquetemos las credenciales en este objeto "Token" (no confundir con el JWT)
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacion.correoElectronico(), datosAutenticacion.contrasena());

        // El AuthenticationManager va y llama a nuestro AutenticacionService para verificar en la base de datos
        var usuarioAutenticado = authenticationManager.authenticate(authToken);

        // Si sale bien, devolvemos un 200 OK (Temporalmente, luego devolveremos el JWT aquí)
        return ResponseEntity.ok().build();
    }
}
