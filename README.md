# 🩺 Hackathon - Microsserviço de Vacina

Este repositório faz parte do projeto de melhoria do SUS, desenvolvido como parte da pós-graduação em Arquitetura e Desenvolvimento Java - FIAP.

Microsserviço responsável pelo gerenciamento de vacinas no sistema da **Carteira Digital de Vacinação**.

---

## 📁 Estrutura do Projeto

- **Java 21**
- **Spring Boot 3**
- **MongoDB**
- **Swagger (springdoc-openapi)**
- **Arquitetura Hexagonal (Ports & Adapters)**

---

## 🚀 Como executar o projeto

### Pré-requisitos

- [Docker e Docker Compose](https://www.docker.com/)
- Java 21
- Maven 3+

### Subindo o MongoDB com Docker Compose

Na raiz do projeto, execute:

```bash
docker-compose up -d
```

Isso iniciará o MongoDB localmente na porta `27018`.

### Rodando o microsserviço

```bash
./mvnw spring-boot:run
```

A aplicação estará disponível em:

```
http://localhost:8081
```

---

## 📌 Endpoints Principais

Acesse a documentação completa da API:

```
http://localhost:8081/swagger-ui.html
```

### Exemplo de endpoints:

- `POST /v1/vacina` - Criar vacina
- `PUT /v1/vacina/{nome}` - Atualizar vacina
- `GET /v1/vacina/{nome}` - Buscar vacina por nome
- `GET /v1/vacina` - Buscar todas as vacinas
- `DELETE /v1/vacina/{nome}` - Remover vacina

---

## 🛠️ Tecnologias Utilizadas

- **Spring Boot**
- **Spring Data MongoDB**
- **WebStruct**
- **Springdoc OpenAPI (Swagger)**
- **Docker**
- **JUnit 5 / Mockito**

---

## 🧪 Testes

Os testes unitários foram implementados com JUnit e Mockito.

Para rodar os testes:

```bash
./mvnw test
```

---

## 👨‍💻 Autores

**Renan**  
[GitHub - MrRenan](https://github.com/MrRenan)

**Samuel**
[GitHub - SamuelXIsidorio](https://github.com/SamuelXIsidorio)


**Renato**
[GitHub - urpdrum](https://github.com/urpdrum)

---

## 📄 Licença

Este projeto é apenas para fins acadêmicos. Nenhum uso comercial autorizado.