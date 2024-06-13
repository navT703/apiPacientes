package com.clinica.api_clinica.controllers;

import com.clinica.api_clinica.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository repository;
    @GetMapping("/all")
    public ResponseEntity getAllPacientes(){
        var AllPacientes = repository.findAll();
        return ResponseEntity.ok(AllPacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity getPacienteById(@PathVariable String id){
        Optional<Paciente> optionalPaciente = repository.findById(id);
        if(optionalPaciente.isPresent()){
            return ResponseEntity.ok(optionalPaciente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity createPaciente(@RequestBody @Validated RequestPaciente data){
        Paciente aux = new Paciente(data);
        repository.save(aux);
        System.out.println(data);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updatePaciente(@RequestBody @Validated RequestPaciente data, @PathVariable String id){
        Optional<Paciente> optionalPaciente = repository.findById(id);
        if(optionalPaciente.isPresent()){
            Paciente paciente = optionalPaciente.get();
            paciente.setNome(data.nome());
            paciente.setSobrenome(data.sobrenome());
            paciente.setSexo(data.sexo());
            paciente.setNascimento(data.nascimento());
            paciente.setIdade(data.idade());
            paciente.setAltura(data.altura());
            paciente.setPeso(data.peso());
            paciente.setCpf(data.cpf());
            paciente.setImc(data.imc());
            repository.save(paciente);
            return ResponseEntity.ok(paciente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePaciente(@PathVariable String id){
        Optional<Paciente> optionalPaciente = repository.findById(id);
        if(optionalPaciente.isPresent()){
            repository.delete(optionalPaciente.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}