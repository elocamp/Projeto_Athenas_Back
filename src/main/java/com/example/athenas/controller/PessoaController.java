package com.example.athenas.controller;

import com.example.athenas.dto.PessoaDto;
import com.example.athenas.entity.Pessoa;
import com.example.athenas.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
@CrossOrigin("*")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @PostMapping
    public ResponseEntity<Pessoa> createPessoa(
            @RequestBody PessoaDto pessoaDto
            ) throws Exception {
        Pessoa newPessoa = service.createPessoa(pessoaDto);
        return new ResponseEntity<>(newPessoa, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> getAll() throws Exception {
        var pessoas = service.getAll();
        return new ResponseEntity<>(pessoas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPessoaById(
            @PathVariable(value = "id") Long id
    ) throws Exception {
        var pessoa = service.getPessoaById(id);
        return new ResponseEntity<>(pessoa, HttpStatus.OK);
    }

    @GetMapping("/{id}/pesoIdeal")
    public ResponseEntity<Double> calcularPesoIdeal(
            @PathVariable(value = "id") Long id
    ) throws Exception {
        Pessoa pessoa = service.getPessoaById(id);
        Double pesoIdeal = service.calcularPesoIdeal(pessoa.getAltura(), pessoa.getSexo());
        return ResponseEntity.ok(pesoIdeal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePessoaById(
            @PathVariable(value = "id") Long id,
            @RequestBody PessoaDto pessoaDto
    ) throws Exception {
        service.updatePessoaById(id, pessoaDto);
        return ResponseEntity.status(HttpStatus.OK).body(" - As informações da pessoa foram atualizadas.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePessoaById(
            @PathVariable(value = "id") Long id
    ) throws Exception {
        service.deletePessoaById(id);
        return ResponseEntity.status(HttpStatus.OK).body(" - A pessoa foi excluída com sucesso.");
    }
}
