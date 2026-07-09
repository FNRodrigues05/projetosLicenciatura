# Controlador de Bases de Dados - Fãs de Sobremesas

Este repositório contém o projeto desenvolvido no âmbito da disciplina de Bases de Dados da Universidade de Évora. O trabalho focou-se na modelação, criação e exploração de uma base de dados relacional para gerir uma rede social dedicada a entusiastas de sobremesas.

## Sobre o Projeto

O sistema foi desenhado para organizar e relacionar informações sobre membros, doces, ingredientes e interações sociais. A implementação foi realizada utilizando o sistema **PostgreSQL**, permitindo a gestão de dados complexos através de relacionamentos entre tabelas.

## Estrutura da Base de Dados

O modelo de dados inclui as seguintes relações principais:

* **Membro**: Armazena dados pessoais e identificação de cada utilizador.
* **Doce**: Regista receitas, descrições e géneros de sobremesas.
* **Ingrediente**: Mantém um catálogo de ingredientes e os seus custos unitários.
* **Amigo**: Modela a rede social através de relações de amizade simétricas.
* **Criou/Fez**: Registam a autoria dos doces e o histórico de confeções (avaliando tempo, aspeto e sabor).
* **TemIngrediente**: Associa doces aos seus ingredientes e respetivas quantidades.

## Conteúdo do Repositório

* `Criar Tabelas SQL.txt`: Script com a definição das tabelas (DDL), incluindo chaves primárias, estrangeiras e restrições de integridade.
* `Respostas Expressões SQL.txt`: Ficheiro contendo os comandos de inserção de dados e a resolução das consultas SQL solicitadas no guião.
* `Relatório_57823_59100_59119.pdf`: Documento técnico completo com a análise das chaves (primárias, candidatas e estrangeiras), diagramas e a tradução das consultas para Álgebra Relacional.

## Como Utilizar

1. **Criação do Esquema**: Importa ou executa o conteúdo de `Criar Tabelas SQL.txt` num servidor PostgreSQL.
2. **Povoamento e Consultas**: Executa o ficheiro `Respostas Expressões SQL.txt` para inserir os dados obrigatórios e testar as respostas às perguntas do guião.
3. **Consulta de Documentação**: O relatório em PDF fornece toda a lógica teórica e a justificação das escolhas do modelo relacional.