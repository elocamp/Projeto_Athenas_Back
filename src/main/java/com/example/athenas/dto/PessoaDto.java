package com.example.athenas.dto;
import java.time.LocalDate;

public record PessoaDto(
        String nome,
        LocalDate dataNascimento,
        String cpf,
        Character sexo,
        Double altura,
        Double peso
) {
}
