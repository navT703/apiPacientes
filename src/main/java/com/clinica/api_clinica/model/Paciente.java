package com.clinica.api_clinica.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.Period;

@Table(name="paciente")
@Entity(name="paciente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id @GeneratedValue( strategy = GenerationType.UUID)
    private String id;
    private String nome;
    private String sobrenome;
    private char sexo;
    private LocalDate nascimento;
    private Byte idade;
    private short altura;
    private double peso;
    private String cpf;
    private double imc;
    public Paciente(RequestPaciente requestPaciente){
        this.nome = requestPaciente.nome();
        this.sobrenome = requestPaciente.sobrenome();
        this.sexo = requestPaciente.sexo();
        this.nascimento = requestPaciente.nascimento();
        this.idade = requestPaciente.idade();
        this.altura = requestPaciente.altura();
        this.peso = requestPaciente.peso();
        this.cpf = requestPaciente.cpf();
        this.imc = requestPaciente.imc();

        if(this.imc == 0) this.imc = getIMC();
        if(this.idade == 0) this.idade = calcularIdade();
        System.out.println("IDADE: " + this.idade);
        System.out.println("CPF VALIDO: " + validarCPF());
        System.out.println("PESO IDEAL: " + obterPesoIdeal());
        System.out.println("IMC: " + this.imc);
        System.out.println("SITUACAO IMC: " + obterSituacaoIMC());
        System.out.println("CPF OFUSCADO: " + obterCpfObuscado());
    }

    public double obterPesoIdeal(){
        double alturaEmPolegadas = this.altura / 2.54;
        if (this.sexo == 'H') {
            return 50 + 2.3 * (alturaEmPolegadas - 60);
        } else if (this.sexo == 'M') {
            return 45.5 + 2.3 * (alturaEmPolegadas - 60);
        } else {
            throw new IllegalArgumentException("Sexo deve ser 'M' (masculino) ou 'F' (feminino).");
        }
    }

    public String obterCpfObuscado(){
        return "***." + cpf.substring(3, 6) + ".***-**\n";
    }

    public String obterSituacaoIMC(){
        if(imc < 17){
            return "Muito abaixo do peso";
        } else if (imc < 18.5) {
            return "Abaixo do peso";
        } else if (imc < 24.5) {
            return "Peso normal";
        } else if (imc < 30) {
            return  "Acima do peso";
        } else if (imc < 35) {
            return "Obesidade I";
        } else if (imc < 40) {
            return "Obesidade II (severa)";
        } else if (imc >= 40){
            return "Obesidade III (mórbida)";
        } else {
            return "Error";
        }
    }

    private double getIMC(){
        double alturaEmMetros = this.altura / 100.0;
        return this.peso / (alturaEmMetros * alturaEmMetros);
    }

    private byte calcularIdade(){
        return (byte) Period.between(this.nascimento, LocalDate.now()).getYears();
    }

    private boolean validarCPF(){
        if(cpf.length() != 11) return false;

        // Calcula o primeiro dígito verificador
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += (cpf.charAt(i) - '0') * (10 - i);
        }
        int firstVerifier = 11 - (sum % 11);
        if (firstVerifier >= 10) {
            firstVerifier = 0;
        }

        if (firstVerifier != (cpf.charAt(9) - '0')) {
            return false;
        }

        // Calcula o segundo dígito verificador
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += (cpf.charAt(i) - '0') * (11 - i);
        }
        int secondVerifier = 11 - (sum % 11);
        if (secondVerifier >= 10) {
            secondVerifier = 0;
        }

        if (secondVerifier != (cpf.charAt(10) - '0')) {
            return false;
        }

        return true;
    }
}
