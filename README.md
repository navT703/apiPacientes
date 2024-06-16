Introdução


O departamento de TI (Tecnologia da Informação) de uma clínica médica tem enfrentado desafios significativos relacionados ao gerenciamento de um volume crescente de dados e à complexidade dos sistemas em uso. A administração eficiente das informações de saúde dos pacientes é fundamental para garantir a prestação de cuidados de qualidade, melhorando a tomada de decisões médicas, bem como simplificando os processos internos. Pensando nisso, tomou-se a iniciativa de desenvolver uma API para melhorar o gerenciamento dos pacientes da clínica médica.
O objetivo principal deste projeto é desenvolver uma API Rest que melhore o gerenciamento de informações dos pacientes. A API permitirá operações básicas de CRUD (Create, Read, Update, Delete) para pacientes, possibilitando a integração com outros sistemas existentes e futuros, sendo independente. Com isso, espera melhorar a qualidade do atendimento prestado, simplificar a gestão de dados e melhorar a eficiência operacional da clínica.
Requisitos do sistema:
- Java 17+
- Banco de Dados: PostgreSQL
  Arquitetura:
A API de gerenciamento de pacientes incluirá as seguintes funcionalidades: adição, consulta, alteração e remoção de paciente do sistema.
Ela é composta por 2 pacotes, sendo eles:
- Controller: Tem uma classe PacienteController que lida com todas as requisições, fazendo toda a logica da API.
- Model: Tem os modelos, como a classe Paciente, que é a instância dos pacientes, e a classe PacienteRepository, que acessa ao Banco de Dados.
As configurações de acesso ao banco de dados, como IP, usuário e senha, estão dentro do arquivo /src/main/resources/application.properties.
  Instruções de Uso
Primeiramente mude as configurações para ao acesso ao banco de dados em /src/ main/resources/application.properties, alterando o IP, usário e senha para a conexão.
Os acessos para as operações da API devem ser feitas atráves de requisições HTTP, com diferentes métodos. As operações seguem um modelo de corpo para a

requisição, sendo um JSON com os seguintes campos:
- ID:
Chave: "id" | Valor: Texto contendo o UU-ID do paciente (String);
- Nome:
Chave: "nome" | Valor: Qualquer texto (String);
- Sobrenome:
Chave: "sobrenome" | Valor: Qualquer texto (String);
- Sexo:
Chave: "sexo" | Valor: Uma letra, sendo "H" para Homem ou "M" para Mulher
(Char);
- Data de Nascimento:
Chave: "nascimento" | Valor: Texto, sendo uma data do tipo "YYYY-MM-DD", (String);
- Idade:
Chave: "idade" | Valor : Número inteiro, podendo ser qualquer número >= 0
(Integer); - Altura:
Chave: "altura" | Valor: Número inteiro, sendo a altura da pessoa em centímetros(cm) (Integer);
- Peso:
Chave: "peso" | Valor: Número inteiro, sendo o peso da pessoa em KG(Kilograma) (Integer);
- CPF:
Chave: "cpf" | Valor: Texto com 11 caracteres (String);
- IMC:
Chave: "imc" | Valor: Número racional, sendo >= 0 (Float);
Exemplo de corpo JSON: json
{
"id": "9678bb41-a263-4781-b41b-05725257780e", "nome": "Fulano",
"sobrenome": "De Tal",
"sexo": "H",
"nascimento": "2004-01-10", "idade": 0,
"altura": 180,
"peso": 70,
"cpf": "02427110060",
"imc": 0.0 }

Cada requisição precisa de dados diferentes, ou seja, nem todas precisarão de todos

campos. Essa é apenas um modelo base.
 1. Cadastro de Pacientes:
Permite a adição de novos pacientes ao sistema.
Dados da requisição:
- Metodo: POST
- Corpo(Body): Nome, Sobrenome, Sexo, Data de Nascimento, Idade, Altura, Peso, CPF e IMC.
- Endpoint: /api/v1/pacientes
Exemplo de body: json
{
"nome": "Fulano", "sobrenome": "De Tal", "sexo": "H",
"nascimento": "2002-07-10", "idade": 0,
"altura": 180,
"peso": 70,
"cpf": "02427110060", "imc": 0.0
} 
Observação: Caso o número da Idade ou IMC for 0, o sistema irá calcular sozinho de acordo com as outras informações.
 2. Consulta de Pacientes:
Permite a busca e recuperação de dados de pacientes existentes. Pode ser consultado todos os pacientes ou um especifico:
 2.1 - Consulta de todos os pacientes: Dados da requisição:
- Metodo: GET
- Corpo(Body): Vazio
- Endpoint: /api/v1/pacientes/all
 2.2 - Consulta de paciente especifico:

Dados da requisição:
- Metodo: GET
- Corpo(Body): Vazio
- Endpoint: /api/v1/pacientes/{id} ({id} deverá ser o UU-ID do paciente)
Exemplo de URL para requisição de paciente específico: http://127.0.0.1/api/v1/ pacientes/9678bb41-a263-4781-b41b-05725257780e
 3. Atualização de Dados:
Permite a atualização de informações dos pacientes.
Dados da requisição:
- Metodo: PUT
- Corpo(Body): ID, Nome, Sobrenome, Sexo, Data de Nascimento, Idade, Altura,
Peso, CPF e IMC.
- Endpoint: /api/v1/pacientes/{id}
Exemplo de requisição: http://127.0.0.1/api/v1/pacientes/9678bb41-a263-4781- b41b-05725257780e
Exemplo de body: json
{
"nome": "Fulana", "sobrenome": "De Tal", "sexo": "M",
"nascimento": "2012-01-10", "idade": 0,
"altura": 156,
"peso": 62,
"cpf": "02427110060", "imc": 0.0
} 
 4. Exclusão de Pacientes:
Permite a remoção de pacientes do sistema.
Dados da requisição:
- Metodo: DELETE
- Corpo(Body): Vazio.
- Endpoint: /api/v1/pacientes/{id}

Tecnologias
- Linguagem: Java 17+
- Framework: SprintBoot
- Dependencias: Starter Web, Lambok, DevTools, PostgreSQL Driver, Spring Boot Starter Data JPA, Validation API, Hibernate, Jakarta Validation API.
- Ferramenta de Build: Maven
  Código Fonte
Acesse o código fonte [https://github.com/navT703/apiPacientes/tree/master]()
