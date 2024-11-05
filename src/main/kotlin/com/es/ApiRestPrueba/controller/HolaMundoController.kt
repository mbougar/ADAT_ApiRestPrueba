package com.es.ApiRestPrueba.controller

import com.es.ApiRestPrueba.model.Saludo
import com.es.ApiRestPrueba.model.Usuario
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

// Las peticiones que entren en el dispatcher las va a repartir al controller adecuado
@RestController // Con la anotación RestController, SpringBoot ya sabe que es un controlador
class HolaMundoController {

    val mapaUsuarios: MutableMap<String, String> = mutableMapOf(
        "diego" to "1234",
        "pepe" to "qwerty"
        )

    @GetMapping("/saludo")
    fun getHolaMundo(
        @RequestParam nombre: String
    ): Saludo {
        return Saludo("Hola", nombre)
    }

    @GetMapping("/user")
    fun getCredentials(
        @RequestParam nombre: String
    ): Any {
        val password = mapaUsuarios[nombre]
        if (password != null) {
            return Usuario(nombre, password)
        }
        return "No existe"
    }

    @DeleteMapping("/user")
    fun userDelete(
        @RequestParam nombre: String
    ): String {
        val usuarioEliminado = mapaUsuarios.remove(nombre)
        return if (usuarioEliminado != null) {
            "Usuario eliminado"
        } else {
            "Usuario no encontrado"
        }
    }

    @PostMapping("/user")
    fun userInsert(
        @RequestParam nombre: String,
        @RequestParam password: String
    ) {
        mapaUsuarios[nombre] = password
    }
}