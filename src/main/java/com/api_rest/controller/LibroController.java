package com.api_rest.controller;

import com.api_rest.entidades.Biblioteca;
import com.api_rest.entidades.Libro;
import com.api_rest.repositorios.BibliotecaRepository;
import com.api_rest.repositorios.LibroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private BibliotecaRepository bibliotecaRepository;


    @PostMapping // Obtenemos el Id el cual esta relacionada la biblioteca
    public ResponseEntity<Libro> guadarLibro(@Valid @RequestBody Libro libro) {
        Optional<Biblioteca> bibliotecaOptional = bibliotecaRepository.findById(libro.getBiblioteca().getId());

        if (bibliotecaOptional.isEmpty()){
            return ResponseEntity.unprocessableEntity().build();
        }
        libro.setBiblioteca(bibliotecaOptional.get());
        Libro libroGuardado = libroRepository.save(libro);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(libroGuardado.getId()).toUri();
        return ResponseEntity.created(ubicacion).body(libroGuardado);
    }

    @PutMapping ("/id")
    public ResponseEntity<Libro> actualizarLibro(@Valid @RequestBody Libro libro, @PathVariable Integer id) {
        Optional<Biblioteca> bibliotecaOptional = bibliotecaRepository.findById(libro.getBiblioteca().getId());

        if (bibliotecaOptional.isEmpty()){
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Libro>libroOptional =libroRepository.findById(id);
        if (libroOptional.isEmpty()){
            return ResponseEntity.unprocessableEntity().build();
        }

        libro.setBiblioteca(bibliotecaOptional.get());
        Libro libroGuardado = libroRepository.save(libro);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(libroGuardado.getId()).toUri();
        return ResponseEntity.created(ubicacion).body(libroGuardado);
    }

}
