# Simulação de Memória Virtual: Substituição de Páginas (Parte 1)

Este repositório contém a Parte 1 do segundo trabalho prático da unidade curricular de **Sistemas Operativos** da Universidade de Évora. O objetivo principal deste projeto é simular e avaliar o desempenho de diferentes algoritmos de substituição de páginas na gestão de memória virtual.

## Sobre o Projeto

Quando a memória principal de um sistema operativo fica cheia, é necessário decidir qual a página a remover para dar espaço a uma nova. Este programa simula esse processo utilizando sequências de acesso à memória e avalia o número de falhas de página (page faults) geradas por duas abordagens clássicas:

* **FIFO (First-In, First-Out):** A página que entrou há mais tempo na memória é a primeira a ser substituída.
* **LRU (Least Recently Used):** A página que não é acedida (lida/escrita) há mais tempo é a escolhida para ser substituída.

## Estrutura de Ficheiros

* `parte1.c`: Ficheiro principal que contém a lógica do simulador e a implementação dos algoritmos FIFO e LRU.
* `inputs_part1.c`: Módulo responsável pela geração das sequências de referências de memória utilizadas nos testes.
* `header_parte1.h`: Ficheiro de cabeçalho contendo as definições das estruturas de dados e assinaturas das funções.
* `Makefile`: Script com as regras de automação para compilar o projeto e correr os diferentes cenários.

## Como Compilar e Executar

O projeto foi desenvolvido em C e inclui um `Makefile` que facilita a compilação e o redirecionamento dos resultados para ficheiros de saída (com a extensão `.out`).

### 1. Compilação
Para compilar o código fonte e gerar o executável `p1`, executa o comando:
```bash
make compile
```

### 2. Executar as Simulações (Cenários 0 a 5)
O programa disponibiliza cenários de teste pré-definidos. Podes testar cada algoritmo separadamente. O `Makefile` executará o simulador e guardará os resultados automaticamente.

**Para testar o algoritmo LRU:**
* Cenário 0: `make lru-0` *(Gera `lru00.out`)*
* Cenário 1: `make lru-1` *(Gera `lru01.out`)*
* Cenário 2: `make lru-2` *(Gera `lru02.out`)*
* Cenário 3: `make lru-3` *(Gera `lru03.out`)*
* Cenário 4: `make lru-4` *(Gera `lru04.out`)*
* Cenário 5: `make lru-5` *(Gera `lru05.out`)*

**Para testar o algoritmo FIFO:**
* Cenário 0: `make fifo-0` *(Gera `fifo00.out`)*
* Cenário 1: `make fifo-1` *(Gera `fifo01.out`)*
* Cenário 2: `make fifo-2` *(Gera `fifo02.out`)*
* Cenário 3: `make fifo-3` *(Gera `fifo03.out`)*
* Cenário 4: `make fifo-4` *(Gera `fifo04.out`)*
* Cenário 5: `make fifo-5` *(Gera `fifo05.out`)*

### 3. Limpeza
Para remover o executável `p1` e apagar todos os ficheiros de texto `.out` gerados pelas simulações, corre o comando:
```bash
make clean
```