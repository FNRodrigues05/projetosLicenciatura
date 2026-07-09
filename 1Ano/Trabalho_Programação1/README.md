# Jogo Ouri em C

Este repositório contém a implementação do jogo tradicional africano **Ouri** (também conhecido como Oware), desenvolvido em linguagem C como projeto para a disciplina de Programação 1 da Universidade de Évora.

## Funcionalidades
* **Dois modos de jogo:** Jogador vs Jogador (PvP) e Jogador vs Computador (PvE).
* **IA adaptativa:** O computador analisa o tabuleiro para fazer jogadas válidas.
* **Sistema de Save:** Guarda o progresso da tua partida num ficheiro `.txt` e retoma mais tarde.

## Como Compilar e Executar

### Pré-requisitos
Para compilar este projeto, precisas de ter o compilador `gcc` e a ferramenta `make` instalados no teu sistema.

### Compilação
Abre o terminal na pasta do projeto e corre o seguinte comando para gerar o executável:
```bash
make
```

*(Se não tiveres o `make` instalado, podes compilar manualmente com: `gcc -o ouri ouri.c`)*

### Execução
Para iniciar um novo jogo do zero:

```bash
./ouri
```

Para carregar um tabuleiro de um jogo guardado anteriormente:

```bash
./ouri nome_do_ficheiro
```

## Limpeza
Para apagar o ficheiro executável compilado, basta correr:

```bash
make clean
```

## Autores
* Tiago Hermitério
* Pedro Soares
* Francisco Rodrigues