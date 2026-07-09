# GURU2P2 - Jogo de Palavras

Este repositório contém o projeto **GURU2P2**, desenvolvido no âmbito da disciplina de **Programação II** da **Universidade de Évora**

## Sobre o Projeto
O "GURU2P2" é um jogo interativo inspirado no clássico "Palavra Guru". O objetivo do jogador é formar palavras válidas a partir de um conjunto base de letras fornecido em cada nível. O projeto foi desenvolvido em **Java** e utiliza o paradigma de Programação Orientada a Objetos.

## Funcionalidades Implementadas

O jogo cumpre todos os requisitos avançados propostos no projeto:

* **Interface Gráfica (GUI):** Interface completa desenvolvida com a biblioteca Java Swing, com ecrã inicial, painel de letras interativo e painel de palavras.
* **Sistema de Moedas e Dicas:** O jogador ganha 10 moedas por cada palavra correta adivinhada. Ao acumular 100 moedas, pode comprar uma dica que revela a posição de uma letra numa palavra oculta.
* **Validação com Dicionário:** Se o jogador inserir uma palavra que é válida no dicionário português (usando o ficheiro `portuguese-large.txt`), mas que não faz parte das palavras principais do nível, recebe 10 moedas bónus.
* **Gravar e Carregar (Save/Load):** O jogador pode guardar o seu progresso a qualquer momento num ficheiro local (`savegame.txt`) e retomar o jogo mais tarde exatamente de onde parou, mantendo o nível e as moedas.

## Estrutura de Ficheiros
* `GURU2P2.java`: Código-fonte principal com a lógica do jogo e a Interface Gráfica.
* `ficheiro_niveis.txt`: Ficheiro de texto que contém a base de dados dos níveis jogáveis (letras disponíveis e palavras a descobrir).
* `portuguese-large.txt`: Dicionário utilizado para a validação de palavras extra.
* `Makefile`: Script para automatizar a compilação e execução do jogo.
* `Relatório Guru2P2.pdf`: Relatório final detalhando o desenvolvimento do projeto e a interface do utilizador.

## Como Compilar e Executar

Este projeto inclui um `Makefile` para facilitar a compilação e execução no terminal. Certifica-te de que tens o [Java JDK](https://www.oracle.com/java/technologies/downloads/) instalado.

1. Abre o terminal na pasta do projeto.
2. Para **compilar e iniciar o jogo** num só passo, escreve:

```bash
make run
```

3. (Opcional) Para apenas compilar o código gerando o ficheiro `.class`:

```bash
make
```

4. Para limpar os ficheiros compilados (`.class`):

```bash
make clean
```