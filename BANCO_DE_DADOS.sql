-- Criar
CREATE TABLE public.paciente (
	id varchar DEFAULT gen_random_uuid() NOT NULL,
	nome varchar(255) NOT NULL,
	sobrenome varchar(255) NOT NULL,
	sexo bpchar(1) NOT NULL,
	nascimento date NOT NULL,
	idade int4 NULL,
	altura int4 NOT NULL,
	peso float8 NOT NULL,
	cpf varchar(11) NOT NULL,
	imc float8 NULL,
	CONSTRAINT paciente_cpf_key UNIQUE (cpf),
	CONSTRAINT paciente_pkey PRIMARY KEY (id)
);