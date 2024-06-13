package com.clinica.api_clinica.model;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PacienteRepository extends  JpaRepository<Paciente, String>{}
