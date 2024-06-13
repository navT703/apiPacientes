package com.clinica.api_clinica.model;

import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

public record RequestPaciente(
        String id,
        String nome,
        String sobrenome,
        char sexo,
        LocalDate nascimento,
        Byte idade,
        short altura,
        double peso,
        String cpf,
        double imc
) {}
