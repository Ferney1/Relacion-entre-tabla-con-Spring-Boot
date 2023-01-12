package com.api_rest.entidades;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "libros", uniqueConstraints = {@UniqueConstraint(columnNames ={"nombre"})})// Indicamos que el nombre del libro debe ser unico lo cual no se puede repetir

public class Libro {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @NotNull
    private String name;

     // Unir la tabla Libro y Biblioteca
    // JsonProperty = ayuda a ignorar el error que se genera con
    // Indica quien es la entidad dueña (JoinColumn)
    @ManyToOne(fetch = FetchType.LAZY,optional = false) // Lazy : carga perezosa
    @JoinColumn(name = "biblioteca_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Biblioteca biblioteca;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }
}
