package com.example.athenas.service;

import com.example.athenas.dto.PessoaDto;
import com.example.athenas.entity.Pessoa;
import com.example.athenas.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public void savePessoa(Pessoa pessoa) {
        repository.save(pessoa);
    }

    public Pessoa createPessoa(PessoaDto pessoaDto) throws Exception {
        if (repository.existsByCpf(pessoaDto.cpf())) {
            throw new Exception("Este CPF já está cadastrado no sistema.");
        }

        if (pessoaDto.altura() < 0) {
            throw new Exception("A Altura não pode ser menor do que 0.");
        }

        if (pessoaDto.peso() < 0) {
            throw new Exception("O Peso não pode ser menor do que 0.");
        }

        if (pessoaDto.dataNascimento().isAfter(LocalDate.now())) {
            throw new Exception("A Data de Nascimento não pode ser superior ao dia atual.");
        }

        if (pessoaDto.sexo() != 'M' && pessoaDto.sexo() != 'H') {
            throw new Exception("O Gênero da pessoa deve ser H ou M");
        }

        Pessoa newPessoa = new Pessoa(pessoaDto);
        savePessoa(newPessoa);
        return newPessoa;
    }

    public List<Pessoa> getAll() throws Exception {
        if (!repository.findAll().isEmpty()) {
            return repository.findAll();
        }
        else {
            throw new Exception(String.valueOf((HttpStatus.NOT_FOUND) + " - Nenhuma pessoa foi registrada no sistema ainda."));
        }
    }

    public Pessoa getPessoaById(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Esta pessoa não foi encontrada."));
    }

    public Pessoa updatePessoaById(Long id, PessoaDto pessoaDto) throws Exception {
        Pessoa pessoa = getPessoaById(id);

        if (repository.existsByCpf(pessoaDto.cpf())) {
            if (pessoaDto.cpf() == pessoa.getCpf()) {
                throw new Exception("Este CPF já está cadastrado no sistema.");
            }
        }

        if (pessoaDto.altura() < 0) {
            throw new Exception("A Altura não pode ser menor do que 0.");
        }

        if (pessoaDto.peso() < 0) {
            throw new Exception("O Peso não pode ser menor do que 0.");
        }

        if (pessoaDto.dataNascimento().isAfter(LocalDate.now())) {
            throw new Exception("A Data de Nascimento não pode ser superior ao dia atual.");
        }

        if (pessoaDto.sexo() != 'M' && pessoaDto.sexo() != 'H') {
            throw new Exception("O Gênero da pessoa deve ser H ou M");
        }

        pessoa.setNome(pessoaDto.nome());
        pessoa.setDataNascimento(pessoaDto.dataNascimento());
        pessoa.setCpf(pessoaDto.cpf());
        pessoa.setSexo(pessoaDto.sexo());
        pessoa.setAltura(pessoaDto.altura());
        pessoa.setPeso(pessoaDto.peso());

        savePessoa(pessoa);
        return pessoa;
    }

    public void deletePessoaById(Long id) throws Exception {
        Pessoa pessoa = getPessoaById(id);
        repository.delete(pessoa);
    }

    public Double calcularPesoIdeal(Double altura, Character sexo) {
        if (sexo == 'M') {
            return (72.7 * altura) - 58;
        } else if (sexo == 'H') {
            return (62.1 * altura) - 44.7;
        } else {
            throw new IllegalArgumentException("Sexo inválido. Deve ser 'M' ou 'H'.");
        }
    }
}
