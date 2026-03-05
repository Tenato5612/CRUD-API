# 🛡️ User Auth & Sync API

API RESTful para gestão de usuários, integrando sincronização entre aplicações, autenticação JWT e verificação por e-mail.
A API foi projetada para ser consumida por outras aplicações, permitindo sincronização de dados de usuários, autenticação segura com JWT e controle de verificação de e-mail.

## 🚀 Tecnologias

- [x] **Java 17+** & **Spring Boot 3**
- [x] **Spring Security** (JWT Stateless)
- [x] **Spring Data JPA** (MySQL 8.0)
- [x] **Docker** & **Docker Compose**
- [x] **MailHog** (Testes de e-mail em desenvolvimento)
- [x] **Flyway** (Migrations de banco de dados)
- [x] **OpenAPI 3 (Swagger)** (Documentação interativa)

## 📌 Funcionalidades

- [x] **CRUD de Usuários**: Criação, Leitura, Atualização e Deleção.
- [x] **Auth**: Login seguro com retorno de Token JWT.
- [x] **Verificação de E-mail**: Fluxo de confirmação com expiração automática de registros não validados.
- [x] **Sincronização**: Endpoint específico para consumo de dados por outras aplicações.

## 🔐 Autenticação

### 1. Faça login no endpoint /auth/login.
### 2. Copie o token gerado.
### 3. Adicione no Header das requisições: Authorization: Bearer <seu_token>.

## 🛠️ Infraestrutura de Apoio

### 📧 Testes de E-mail (MailHog) 
Para garantir que o fluxo de confirmação de cadastro está funcionando sem enviar e-mails reais:
### 1. Certifique-se de que o container `mailhog` está rodando.
### 2. Acesse a Dashboard: [http://localhost:8025](http://localhost:8025)
### 3. Todos os e-mails disparados pela API aparecerão nesta interface em tempo real.

### 🗄️ Evolução do Banco (Flyway)

Este projeto utiliza Flyway para gerenciar o esquema do banco de dados. 
### - Os scripts de migração encontram-se em: `src/main/resources/db/migration`
### - Importante: Nunca altere um arquivo SQL já existente. Para novas mudanças, crie um novo arquivo seguindo o padrão de versão `V{número}__{descrição}.sql`.

🛠️ Como Executar 🛠️

### 1. Clonar repositório
git clone https://github.com/Tenato5612/CRUD-API.git
cd user-auth-sync-api
### 2. Subir infraestrutura
docker-compose up -d
### 3. Rodar aplicação
mvn spring-boot:run




