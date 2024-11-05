package com.es.ApiRestPrueba.controller

import com.es.ApiRestPrueba.model.Libro
import org.springframework.web.bind.annotation.*

@RestController
class LibroController {

    val libros = mutableListOf<Libro>(
        Libro(1, "uno", "uno", "uno"),
        Libro(2, "dos", "dos", "dos"),
        Libro(3, "tres", "tres", "tres")
    )

    // Vamos a hacer un Crud de Libro
    // Vamos a hacer un metodo que acepte la C (Crear) -> POST
    // Vamos a hacer un metodo que acepte la R (Leer) -> GET
    // Vamos a hacer un metodo que acepte la U (Actualizar) -> PUT / PATCH
    // Vamos a hacer un metodo que acepte la D (Eliminar) -> DELETE

    @PostMapping("/libros")
    fun insert(
        @RequestBody libroNuevo: Libro
    ): Libro? {
        return if (libros.none { it.id == libroNuevo.id }) {
            libros.add(libroNuevo)
            libroNuevo
        } else {
            null
        }
    }

    @GetMapping("/libros/{id}")
    fun getById(
        @PathVariable id: String
    ): Libro? {
        println(id)
        val idL = id.toLong()

        return libros.find { it.id == idL }
    }

    @PutMapping("/libros")
    fun update(
        @RequestBody libroActualizar: Libro
    ): Libro? {
        val libroEnLista = libros.find { it.id == libroActualizar.id }
        return if (libroEnLista != null) {
            libroEnLista.genero = libroActualizar.genero
            libroEnLista.titulo = libroActualizar.titulo
            libroEnLista.autor = libroActualizar.autor
            libroActualizar
        } else {
            null
        }
    }
    
    @DeleteMapping("/libros/{id}")
    fun deleteById(
        @PathVariable id: String
    ): Libro? {
        val idL = id.toLong()

        val libroEnLista = libros.find { it.id == idL }
        return if (libroEnLista != null) {
            libros.remove(libroEnLista)
            libroEnLista
        } else {
            null
        }
    }
}