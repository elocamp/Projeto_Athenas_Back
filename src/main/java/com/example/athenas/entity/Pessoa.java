package com.example.athenas.entity;

import com.example.athenas.dto.PessoaDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity(name = "pessoas")
@Table(name = "pessoas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "od")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private LocalDate dataNascimento;

    private String cpf;

    private Character sexo;

    private Double altura;

    private Double peso;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Pessoa(PessoaDto dto) {
        this.nome = dto.nome();
        this.dataNascimento = dto.dataNascimento();
        this.cpf = dto.cpf();
        this.sexo = dto.sexo();
        this.altura = dto.altura();
        this.peso = dto.peso();
    }
}
