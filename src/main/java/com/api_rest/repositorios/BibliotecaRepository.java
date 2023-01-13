package com.api_rest.repositorios;

import com.api_rest.entidades.Biblioteca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BibliotecaRepository extends JpaRepository<Biblioteca, Integer>{
}
