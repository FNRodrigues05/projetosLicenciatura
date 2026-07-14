# Simulação de Escalonamento de Processos

Este projeto foi desenvolvido no âmbito da unidade curricular de **Sistemas Operativos** da Universidade de Évora. O objetivo central do trabalho consiste em simular e analisar o comportamento de diferentes algoritmos de escalonamento de processos num sistema operativo.

## Sobre o Projeto

O programa simula a gestão da CPU recebendo uma lista de processos (com tempo de chegada, duração e prioridade) e escalonando-os de acordo com três estratégias diferentes. No final de cada simulação, o sistema calcula métricas de desempenho essenciais para avaliar a eficiência de cada algoritmo:
* **Tempo de Retorno (Turnaround Time):** Tempo total desde a submissão até à conclusão do processo.
* **Tempo de Espera (Waiting Time):** Tempo que o processo passou na fila de prontos.
* **Tempo de Resposta (Response Time):** Tempo decorrido desde a chegada até à primeira vez que o processo obtém a CPU.

## Algoritmos Implementados

A simulação suporta os seguintes algoritmos de escalonamento:

1. **FCFS (First-Come, First-Served):** Algoritmo não-preemptivo simples onde o CPU é alocado aos processos pela ordem exata de chegada.
2. **Round-Robin (RR):** Algoritmo preemptivo que utiliza uma fila circular e atribui um "quantum" (fatia de tempo) fixo a cada processo.

## Estrutura de Ficheiros

* `Final.c`: Código-fonte principal que contém a lógica dos escalonadores (FCFS, RR e Personalizado) e o ciclo de simulação.
* `queue.c` / `queue.h`: Implementação da estrutura de dados Fila (Queue), essencial para a gestão dos processos prontos.
* `inputs.c`: Módulo responsável pela criação e gestão dos processos de *input* para a simulação.
* `Makefile`: Script com regras de automação para compilar, executar testes e limpar os ficheiros criados.
* `Relatório trabalho Sistemas Operativos-2.pdf`: Documentação detalhada sobre a modelação matemática, fluxogramas e análise dos resultados.

## Como Compilar e Executar

Este projeto foi desenvolvido em linguagem C e utiliza o `Makefile` incluído para facilitar todas as operações no terminal.

### 1. Compilar o Projeto
Para criar o executável `simulador`, abre o terminal na pasta do projeto e corre:
```bash
make compile
```

### 2. Executar as Simulações
O projeto disponibiliza 6 cenários de teste pré-configurados (de `0` a `5`). Ao correr cada comando, o programa executa a simulação e redireciona os resultados diretamente para um ficheiro de saída `.out`:

* **Cenário 0:**
  ```bash
  make run0
  ```
  *(Cria o ficheiro `output00.out`)*

* **Cenário 1:**
  ```bash
  make run1
  ```
  *(Cria o ficheiro `output01.out`)*

* **Cenário 2:**
  ```bash
  make run2
  ```
  *(Cria o ficheiro `output02.out`)*

* **Cenário 3:**
  ```bash
  make run3
  ```
  *(Cria o ficheiro `output03.out`)*

* **Cenário 4:**
  ```bash
  make run4
  ```
  *(Cria o ficheiro `output04.out`)*

* **Cenário 5:**
  ```bash
  make run5
  ```
  *(Cria o ficheiro `output05.out`)*

### 3. Limpeza de Ficheiros
Para apagar o executável `simulador` e todos os ficheiros de resultados `.out` Criados pelas simulações, corre:
```bash
make clean
```