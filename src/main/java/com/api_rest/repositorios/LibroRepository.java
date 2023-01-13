package com.api_rest.repositorios;

import com.api_rest.entidades.Biblioteca;
import com.api_rest.entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Integer>{
}
