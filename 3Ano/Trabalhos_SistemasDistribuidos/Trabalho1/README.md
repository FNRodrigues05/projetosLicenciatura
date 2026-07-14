# Sistema Distribuído de Gestão de Biblioteca

Este projeto foi desenvolvido no âmbito da unidade curricular de **Sistemas Distribuídos** da Universidade de Évora (ano letivo 2025/2026). O objetivo consiste na implementação de um sistema distribuído robusto para a gestão de uma biblioteca, suportando múltiplos clientes, diferentes protocolos de comunicação e persistência de dados em base de dados relacional.

## 📝 Sobre o Projeto

O sistema permite a gestão completa de uma biblioteca (registo de utilizadores, consulta de recursos como livros, computadores e salas de estudo, bem como a realização de empréstimos). Para demonstrar os diferentes paradigmas de sistemas distribuídos, a arquitetura foi dividida em três componentes principais:

* **Servidor Central:** Gere a lógica de negócio e comunica com a base de dados **PostgreSQL** via JDBC. Fica à escuta de ligações RMI e de Sockets TCP em simultâneo.
* **Cliente Geral (RMI):** Aplicação destinada aos utilizadores comuns da biblioteca. Comunica com o servidor através de *Remote Method Invocation* (RMI) para operações de consulta, empréstimos e devoluções.
* **Cliente Administrador (TCP):** Aplicação de gestão destinada aos funcionários. Comunica com o servidor através de Sockets TCP e permite aprovar novos utilizadores, validar recursos e consultar o estado global do sistema.

## 📂 Estrutura e Configuração

* `ServidorGeral.java`, `ClienteGeral.java`, `ClienteAdmin.java`: Classes principais de inicialização de cada componente.
* `config.properties`: Ficheiro externo de propriedades contendo as credenciais e parâmetros de ligação à base de dados PostgreSQL.
* `postgresql-42.7.8.jar`: *Driver* JDBC necessário para a comunicação com a base de dados.
* `Makefile`: Script de automação de compilação e execução desenhado para ambiente **Windows**.
* `sd relatorio.pdf`: Relatório técnico detalhado com a arquitetura, as queries SQL utilizadas, resolução de problemas e instruções de *setup* da Base de Dados.

## ⚙️ Configuração da Base de Dados

Antes de iniciar o sistema, é necessário configurar o PostgreSQL:
1. Crie uma base de dados no pgAdmin (ou linha de comandos).
2. Restaure o *schema* e os dados iniciais conforme documentado no relatório.
3. Edite o ficheiro `config.properties` na raiz do projeto com as credenciais corretas do seu servidor PostgreSQL.

## 🚀 Como Compilar e Executar (Windows)

Este projeto utiliza um `Makefile` configurado para o terminal do Windows (`cmd`). Certifique-se de que tem o Java (`javac` e `java`) bem como o utilitário `make` configurados nas variáveis de ambiente.

### 1. Compilação
Na raiz do projeto, compile o código e prepare a pasta `bin` com o seguinte comando:
```bash
make compile
```

### 2. Execução
Abra **três terminais diferentes** para simular o ambiente distribuído. Execute os comandos na seguinte ordem:

**Terminal 1 (Iniciar o Servidor):**
```bash
make run-geral
```

**Terminal 2 (Iniciar o Cliente Geral - RMI):**
```bash
make client-geral
```

**Terminal 3 (Iniciar o Cliente Administrador - TCP):**
```bash
make client-admin
```

### 3. Limpeza
Para apagar os ficheiros compilados e a pasta `bin`:
```bash
make clean
```