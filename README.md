# Finance Control API

Finance Control é uma API desenvolvida em **Java com Spring Boot e MySQL** para gerenciamento de finanças pessoais. A aplicação permite registrar fontes de renda, despesas e acompanhar a movimentação financeira ao longo do tempo, oferecendo consultas por usuário e por período (mês e ano), além de sumarização automática dos valores para facilitar a análise financeira.

O sistema foi projetado seguindo boas práticas de desenvolvimento backend, com arquitetura organizada em camadas e separação clara de responsabilidades. A aplicação utiliza **Spring Boot para construção da API REST**, **Spring Data JPA para persistência de dados** e **MySQL como banco relacional**.

Entre as funcionalidades disponíveis estão o cadastro de usuários, registro de diferentes fontes de renda, registro de despesas e consultas financeiras filtradas por período. As consultas permitem recuperar dados por usuário, mês, ano ou mês e ano combinados, retornando também o total consolidado dos valores encontrados.

A arquitetura da aplicação segue um padrão em camadas para manter o código organizado e escalável. Os **controllers** são responsáveis por expor os endpoints da API, os **services** concentram as regras de negócio, os **repositories** realizam a comunicação com o banco de dados por meio do Spring Data JPA, os **models** representam as entidades persistidas e os **DTOs** são utilizados para entrada e saída de dados da API.

A aplicação também foi estruturada para suportar integrações futuras com **inteligência artificial**, permitindo automatizar tarefas como leitura de boletos, importação de faturas de cartão e categorização automática de despesas, reduzindo a necessidade de lançamentos manuais por parte do usuário.

Para executar o projeto, é necessário ter **Java 21, Maven e MySQL** instalados. Após clonar o repositório, crie um banco de dados MySQL chamado `finance_control` e configure as credenciais no arquivo `application.properties`.

# 📖 Documentação da API
http://localhost:8080/swagger-ui.html

# 👨‍💻 Autor
# Wellerson Ferreira de Carvalho
Desenvolvedor FullStack | Backend Java & Node.js
