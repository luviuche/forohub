package com.challenge.forohub.controller;

import com.challenge.forohub.domain.usuario.DatosAutenticacionUsuario;
import com.challenge.forohub.domain.usuario.Usuario;
import com.challenge.forohub.infra.security.DatosJWTToken;
import com.challenge.forohub.infra.security.TokenService;
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

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DatosJWTToken> autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacion) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacion.correoElectronico(), datosAutenticacion.contrasena());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);

        // Obtenemos el usuario autenticado y generamos el token
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());

        // Retornamos el token en formato JSON
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }
}
