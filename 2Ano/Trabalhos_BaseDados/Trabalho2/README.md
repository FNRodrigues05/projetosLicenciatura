# Projeto de Bases de Dados - Loja Virtual de Livros Eletrónicos

Este repositório contém os ficheiros desenvolvidos no âmbito da disciplina de Bases de Dados da Universidade de Évora. O objetivo do trabalho foi o desenho, modelação e implementação de uma base de dados relacional para gerir alugueres e encomendas de uma loja virtual de livros eletrónicos.

## Descrição do Projeto

O sistema foi desenhado para organizar a informação de uma plataforma de aluguer de livros, abrangendo as seguintes entidades e funcionalidades:

* **Leitores**: Gestão de registos (nome, NIF, email, nacionalidade) com diferenciação entre leitores regulares e frequentes, baseada no histórico de alugueres.
* **Livros e Autores**: Gestão de obras, ISBNs, autores associados e géneros literários.
* **Exemplares**: Controlo de cópias digitais dos livros, com links de download associados.
* **Alugueres e Encomendas**: Registo de transações, faturas e avaliações dos utilizadores sobre os livros.

## Conteúdo do Repositório

* `Comandos SQL+Respostas SQL.txt`: Contém os scripts SQL para a criação da base de dados, inserção de dados de teste (povoamento) e as *queries* desenvolvidas para responder às perguntas do guião.
* `Relatório BD.pdf`: Relatório teórico detalhado contendo:
    * Diagrama Entidade-Relacionamento (E-R).
    * Transformação para Relações (esquema relacional).
    * Dependências funcionais e cálculo da cobertura canónica.
    * Normalização (Forma Normal de Boyce-Codd e 3ª Forma Normal).

## Tecnologias

* **Sistema de Gestão de Base de Dados**: PostgreSQL.
* **Ferramenta de Modelação**: Modelagem E-R e relacional conforme as normas da disciplina.

## Como Utilizar

1. **Configuração**: Cria uma nova base de dados no teu servidor PostgreSQL.
2. **Criação das Tabelas**: Executa os comandos de `CREATE TABLE` presentes no ficheiro `Comandos SQL+Respostas SQL.txt`.
3. **Inserção de Dados**: Utiliza os comandos `INSERT` disponíveis no mesmo ficheiro para popular a base de dados.
4. **Consultas**: Executa as *queries* SQL fornecidas no ficheiro para visualizar os resultados solicitados no guião.