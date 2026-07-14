# Simulação de Memória Virtual: Tabela de Páginas de Dois Níveis (Parte 2)

Este projeto foi desenvolvido no âmbito da unidade curricular de **Sistemas Operativos** da Universidade de Évora. O objetivo desta segunda fase consiste em simular um sistema de gestão de memória virtual avançado, recorrendo a uma arquitetura de **Tabela de Páginas de Dois Níveis** (Two-Level Page Table).

## Sobre o Projeto

O simulador foca-se na tradução de endereços lógicos (virtuais) em endereços físicos através de uma hierarquia de tabelas (Diretório de Páginas e Tabela de Páginas). O programa analisa o comportamento do sistema operativo em regime de *Demand Paging* (paginação por procura), avaliando o desempenho da memória principal em função de parâmetros dinâmicos.

### Métricas Analisadas
A simulação permite avaliar o impacto da variação do tamanho de página (*Page Size*) e do tamanho da memória física no desempenho geral do sistema, calculando métricas críticas como:
* **Page Faults (Falhas de Página):** Ocorrências em que a página solicitada não se encontra na memória física.
* **Hit Ratio (Taxa de Acerto):** Percentagem de acessos à memória bem-sucedidos sem necessidade de ler do disco.
* **Uso de Memória pelas Tabelas:** Espaço ocupado na memória física pelas estruturas de tradução ativa (Diretórios e Tabelas de Páginas secundárias criadas dinamicamente).
* **Operações de I/O (Disco):** Leitura de páginas do disco para a memória física e escrita (*write-back*) de páginas modificadas (*dirty pages*) aquando da sua substituição.

## Estrutura de Ficheiros

* `pt2.c`: Ficheiro principal que contém a lógica do simulador de memória virtual de dois níveis e tradução de endereços.
* `queue.c` e `queue.h`: Implementação da estrutura de dados Fila (Queue), utilizada para gerir a ordem de substituição de páginas na memória física.
* `inputs_part2.c`: Módulo encarregue de simular as sequências de acessos e referências a endereços de memória.
* `header_p2.h`: Ficheiro de cabeçalho contendo as estruturas dos blocos de controlo, tabelas e assinaturas de funções.

## Como Compilar e Executar

Este projeto foi desenvolvido em linguagem C e requer a ligação da biblioteca de matemática (`-lm`) para efetuar os cálculos dinâmicos das máscaras de bits e offsets dos endereços.

### 1. Compilação
Para compilar o projeto e gerar o executável `simulador`:
```bash
make compile
```

### 2. Executar as Simulações (Cenários 0 a 11)
O `Makefile` disponibiliza 12 cenários pré-configurados que testam diferentes combinações de dimensões de página e algoritmos de substituição. Os resultados são automaticamente gravados em ficheiros `.out`:

* `make run0` *(Gera `output2T00.out`)*
* `make run1` *(Gera `output2T01.out`)*
* `make run2` *(Gera `output2T02.out`)*
* `make run3` *(Gera `output2T03.out`)*
* `make run4` *(Gera `output2T04.out`)*
* `make run5` *(Gera `output2T05.out`)*
* `make run6` *(Gera `output2T06.out`)*
* `make run7` *(Gera `output2T07.out`)*
* `make run8` *(Gera `output2T08.out`)*
* `make run9` *(Gera `output2T09.out`)*
* `make run10` *(Gera `output2T10.out`)*
* `make run11` *(Gera `output2T11.out`)*

### 3. Limpeza
Para limpar o executável e todos os ficheiros de texto `.out` gerados pelas simulações:
```bash
make clean
```