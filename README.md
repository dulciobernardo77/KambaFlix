# KambaFlix

KambaFlix é uma API REST desenvolvida em Java com Spring Boot para gestão de um catálogo de filmes, categorias e autenticação de utilizadores.

A aplicação permite registrar usuários, autenticar-se com JWT, cadastrar filmes e categorias, consultar o catálogo e manter os dados organizados por categoria.

## Funcionalidades

- Cadastro e autenticação de utilizadores
- Login com geração de token JWT
- Cadastro, consulta, atualização e remoção de filmes
- Gestão de categorias para organização do catálogo
- Persistência com PostgreSQL
- Migrações de banco com Flyway
- Documentação da API com Swagger/OpenAPI
- Segurança baseada em Spring Security

## Stack tecnológica

- Java 21
- Spring Boot 3 / 4.1.1
- Spring Web MVC
- Spring Data JPA
- Spring Security
- PostgreSQL
- Flyway
- JWT (Auth0)
- OpenAPI / Swagger
- Maven

## Estrutura do projeto

```text
KambaFlix/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/KambaFlix/
│   │   │       ├── Config/
│   │   │       ├── Controller/
│   │   │       ├── Entity/
│   │   │       ├── Service/
│   │   │       ├── Repository/
│   │   │       ├── Exceptions/
│   │   │       └── mapper/
│   │   └── resources/
│   │       └── application.yaml
│   └── test/
├── pom.xml
├── mvnw
├── mvnw.cmd
├── README.md
└── SWAGGER_DOCUMENTATION.md
```

## Pré-requisitos

Antes de iniciar a aplicação, certifique-se de ter instalado:

- Java 21
- Maven
- PostgreSQL
- Git

## Configuração do banco de dados

A aplicação utiliza PostgreSQL localmente com as configurações definidas no arquivo `src/main/resources/application.yaml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/KambaFlix
    username: postgres
    password: 1234
```

Crie o banco de dados `KambaFlix` antes de iniciar a aplicação.

## Execução local

1. Clone o repositório:

```bash
git clone https://github.com/dulciobernardo77/KambaFlix.git
cd KambaFlix
```

2. Crie o banco PostgreSQL:

```sql
CREATE DATABASE "KambaFlix";
```

3. Inicie a aplicação:

```bash
./mvnw spring-boot:run
```

Ou no Windows:

```bash
mvnw.cmd spring-boot:run
```

A aplicação estará disponível em:

- API: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger/index.html`
- Documentação OpenAPI JSON: `http://localhost:8080/api/api-docs`

## Endpoints principais

### Autenticação

- `POST /kambaflix/auth/register` — registar utilizador
- `POST /kambaflix/auth/login` — autenticar e obter token JWT

### Filmes

- `GET /kambaflix/movie` — listar filmes
- `GET /kambaflix/movie/{id}` — buscar filme por ID
- `GET /kambaflix/movie/search?categoryId={id}` — filtrar por categoria
- `POST /kambaflix/movie/cadastrar` — criar filme
- `PUT /kambaflix/movie/{id}` — atualizar filme
- `DELETE /kambaflix/movie/{id}` — remover filme

### Categorias

- `GET /kambaflix/category` — listar categorias
- `GET /kambaflix/category/{id}` — buscar categoria por ID
- `POST /kambaflix/category/cadastrar` — criar categoria
- `DELETE /kambaflix/category/{id}` — remover categoria

## Exemplo de uso

### Registrar utilizador

```bash
curl -X POST http://localhost:8080/kambaflix/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "name": "João",
    "email": "joao@email.com",
    "password": "123456"
  }'
```

### Login

```bash
curl -X POST http://localhost:8080/kambaflix/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "joao@email.com",
    "password": "123456"
  }'
```

### Listar filmes com token

```bash
curl -X GET http://localhost:8080/kambaflix/movie \
  -H "Authorization: Bearer <SEU_TOKEN>"
```

## Documentação adicional

Para mais detalhes da API, consulte o arquivo [SWAGGER_DOCUMENTATION.md](SWAGGER_DOCUMENTATION.md).

## Observações

Este projeto foi desenvolvido como uma API de catálogo de filmes com foco em organização, autenticação e documentação de endpoints. Para uso em produção, recomenda-se revisar configurações sensíveis como segredos e credenciais do banco antes de deploy.

