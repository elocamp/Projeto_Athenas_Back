package com.example.athenas.repository;

import com.example.athenas.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    boolean existsByCpf(String cpf);
}
