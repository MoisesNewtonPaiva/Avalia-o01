# / README.md

# Sistema Monolítico para Gerenciamento de Funcionários e Departamentos

Descrição do Projeto
Esta é uma aplicação monolítica construída com **Spring Boot** que gerencia funcionários e departamentos. O sistema oferece uma **API REST** para operações CRUD completas e uma **interface web com Thymeleaf** para visualização e manipulação dos dados. 

## Tecnologias Utilizadas 
* Spring Boot 
* Spring Web (REST + MVC)
* Spring Data JPA 
* Thymeleaf 
* MariaDB (Produção)
* H2 (Testes) 
* Lombok 
* Maven

## Como Executar a Aplicação

1.  **Pré-requisitos:**
    * Java 17 ou superior
    * Maven 3.x
    * Um servidor de banco de dados MariaDB em execução (opcional, pode-se usar o perfil de teste com H2).

2.  **Configuração do Banco de Dados:**
    * Crie um banco de dados no MariaDB chamado `empresa`.
    * Abra o arquivo `src/main/resources/application.properties` e atualize as propriedades `spring.datasource.username` e `spring.datasource.password` com suas credenciais. 

3.  **Executando com Maven:**
    Navegue até a raiz do projeto e execute o comando:
    ```bash
    mvn spring-boot:run
    ```
    * A interface web estará disponível em `http://localhost:8080`.
    * A API REST estará disponível no mesmo endereço, com os endpoints `/departamentos` e `/funcionarios`.

4.  **Executando com Perfil de Teste (H2):**
    Para usar o banco de dados em memória H2, execute o comando:
    ```bash
    mvn spring-boot:run -Dspring-boot.run.profiles=test
    ```
    * O console do H2 estará disponível em `http://localhost:8080/h2-console`.
    * Use a URL JDBC: `jdbc:h2:mem:testdb`, usuário `sa`, sem senha.

##  Exemplos de Requisições para a API REST

### Departamentos

* **Listar todos:** `GET /departamentos`
    ```bash
    curl http://localhost:8080/departamentos
    ```

* **Criar um novo:** `POST /departamentos`
    ```bash
    curl -X POST -H "Content-Type: application/json" -d '{"nome":"RH","localizacao":"Bloco C"}' http://localhost:8080/departamentos
    ```

### Funcionários

* **Listar todos:** `GET /funcionarios`
    ```bash
    curl http://localhost:8080/funcionarios
    ```

* **Criar um novo (vinculado ao departamento com ID 1):** `POST /funcionarios`
    ```bash
    curl -X POST -H "Content-Type: application/json" -d '{"nome":"Ana","email":"ana@email.com","dataAdmissao":"2024-01-15","departamento":{"id":1}}' http://localhost:8080/funcionarios
    ```

