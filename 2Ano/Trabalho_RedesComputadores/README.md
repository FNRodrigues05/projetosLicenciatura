# Chat em Tempo Real (Letra-a-Letra) via UDP

Este projeto foi desenvolvido no âmbito da unidade curricular de **Redes de Computadores** da Universidade de Évora. O objetivo principal consistiu na criação de um sistema de comunicação distribuído em tempo real (letra a letra, sem a necessidade de clicar "Enter" para enviar) utilizando sockets UDP.

## Sobre o Projeto

Diferente de uma aplicação de chat convencional baseada no envio de blocos de texto, este sistema funciona transmitindo os caracteres à medida que são digitados. Para além da componente assíncrona suportada em **UDP**, o projeto integra ainda uma vertente em **TCP** exclusivamente para a transferência fiável de ficheiros.

## Funcionalidades Implementadas

O projeto inclui o trabalho base e diversos *extras* detalhados no relatório:
* **Comunicação Letra-a-Letra**: Atualização do ecrã em tempo real via UDP.
* **Autenticação e Registo**: Sistema de *login* e criação de utilizadores guardados de forma persistente.
* **Canais e Mensagens Privadas**: Suporte para o canal global (`[all]`) e mensagens diretas (`[dm]`).
* **Gestão de Grupos**: Possibilidade de criar, listar e gerir grupos privados entre múltiplos utilizadores.
* **Transferência de Ficheiros**: Troca de ficheiros *peer-to-peer* (P2P) mediada pelo servidor e estabelecida via sockets TCP.
* **Gestão de Sessão**: Controlo de inatividade (timeout) e remoção automática de "clientes fantasma" para robustez do servidor.

## Estrutura do Repositório

* `svFinal.c`: Código-fonte do Servidor (gere as conexões UDP, encaminhamento de mensagens, grupos e autenticação).
* `clFinal.c`: Código-fonte do Cliente (interface de terminal crua `raw mode`, visualização da interface `live` e *threads* para escuta paralela).
* `Relatório.pdf`: Documentação das escolhas de implementação, funcionamento do protocolo e análise de resultados.
* `Enunciado.pdf`: Requisitos originais do projeto.

## Como Compilar e Executar

Este projeto foi testado em ambiente Linux/Unix (devido à utilização de *pthreads* e *termios* para o terminal em *raw mode*).

### 1. Compilação
Abre o terminal na pasta raiz do projeto e compila o servidor e o cliente com os seguintes comandos:

**Compilar o Servidor:**
```bash
gcc svFinal.c -o server
```

**Compilar o Cliente:**
*(Requer a flag `-pthread` para suportar as threads de escuta e envio simultâneas)*
```bash
gcc clFinal.c -o client -pthread
```

### 2. Execução

**Iniciar o Servidor:**
Num terminal, arranca o servidor (fica à escuta na porta 12345 por defeito):
```bash
./server
```
*(Nota: O servidor imprimirá periodicamente no terminal um `CHECKPOINT` exibindo estatísticas de fiabilidade, como mensagens recebidas vs perdidas).*

**Iniciar o Cliente(s):**
Abre outro terminal (ou múltiplos terminais) e liga os clientes:
```bash
./client
```
*(Ao entrar, utiliza os comandos interativos exibidos no ecrã para criar conta, fazer login, mudar de sala `/group` ou enviar mensagens `/[username]`).*
