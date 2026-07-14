# Sistema de Monitorização Ambiental (IoT Distribuído)

Este projeto foi desenvolvido no âmbito da unidade curricular de Sistemas Distribuídos da Universidade de Évora. Consiste numa solução robusta para a monitorização de temperatura e humidade em tempo real. O sistema destaca-se pela sua arquitetura híbrida e agnóstica ao protocolo, permitindo a ingestão simultânea de dados via MQTT, gRPC e REST, centralizando o processamento e a persistência num servidor Spring Boot.

## Arquitetura do Sistema

O sistema segue uma arquitetura distribuída composta pelos seguintes módulos:

### Sensores (Clientes)
Três tipos de simuladores Java que geram dados sintéticos:
* **Sensor MQTT:** Simula dispositivos de baixo consumo (ex: bateria/jardim).
* **Sensor gRPC:** Simula gateways de alta performance (ex: laboratórios/industrial).
* **Sensor REST:** Simula dispositivos genéricos com capacidade HTTP.

### Infraestrutura (Docker)
* **PostgreSQL:** Base de dados relacional para persistência de dispositivos e métricas.
* **Message Broker (Mosquitto/ActiveMQ):** Para gestão de mensagens assíncronas do protocolo MQTT.

### Servidor Central (Backend)
* **Spring Boot:** Orquestra a ingestão de dados, aplica a validação de negócio centralizada e expõe a API para o sistema.

### Admin CLI
* **Cliente de Consola:** Interface para gestão de dispositivos e visualização de relatórios e métricas em tempo real.

## Tecnologias Utilizadas

* **Linguagem:** Java 17+
* **Framework:** Spring Boot 3.x (Web, Data JPA, Integration)
* **Protocolos:**
  * **gRPC:** Google Protocol Buffers (.proto)
  * **MQTT:** Eclipse Paho Client
  * **REST:** Java HttpClient & Spring Web MVC
* **Base de Dados:** PostgreSQL
* **DevOps & Ferramentas:** Docker, Docker Compose, Maven

## Pré-requisitos

Certifique-se de que tem instalado na sua máquina:
* Java JDK 17 ou superior
* Maven
* Docker e Docker Compose

## Como Instalar e Executar

Siga a ordem abaixo para garantir que todos os componentes comunicam corretamente.

### 1. Iniciar a Infraestrutura (Docker)
Na raiz do projeto (onde está o ficheiro docker-compose.yml), execute o comando abaixo para levantar a Base de Dados e o Broker MQTT:
```bash
docker-compose up -d
```

### 2. Iniciar o Servidor Central
Abra um terminal, navegue até à pasta do servidor e inicie a aplicação Spring Boot:
```bash
cd server
mvn spring-boot:run
```
*(O servidor ficará à escuta nas portas: 8080 para REST, 9090 para gRPC e 1883 para ligação ao Broker).*

### 3. Configurar Dispositivos (Admin CLI)
**Importante:** O servidor rejeita dados de sensores desconhecidos por questões de segurança. Utilize o CLI para registar os IDs primeiro.
Num novo terminal, inicie o cliente de administração:
```bash
cd admin-cli
mvn exec:java
```
No menu, escolha "1. Gestão de Dispositivos" > "2. Adicionar novo dispositivo" e registe os IDs que pretende usar (ex: sensor-jardim, sensor-lab, sensor-sala).

### 4. Correr os Sensores
Abra novos terminais para cada sensor que deseja simular. Quando o programa iniciar, ser-lhe-á pedido o ID que acabou de registar na base de dados.

**Sensor REST:**
```bash
cd client-rest
mvn exec:java
```

**Sensor MQTT:**
```bash
cd client-mqtt
mvn exec:java
```

**Sensor gRPC:**
```bash
cd client-grpc
mvn exec:java
```

## Funcionalidades

### Gestão Centralizada (Admin CLI)
* **CRUD Completo:** Registar, listar, atualizar e remover sensores.
* **Tabelas Formatadas:** Visualização clara do estado dos dispositivos, protocolo utilizado e localização física.

### Monitorização e Estatísticas
* **Médias Agregadas:** Consulta de temperatura e humidade média, com possibilidade de filtragem por Sala, Piso, Departamento ou Edifício.
* **Relatório Global:** Visualização em tempo real de todos os dispositivos ativos e das suas médias de leituras.
* **Logs do Sistema:** Contagem de dispositivos ativos por protocolo e número de leituras totais processadas.

### Simulação Realista
* **Dados Sintéticos:** Os sensores geram variações gerais e plausíveis de temperatura (15-30ºC) e humidade (30-80%).
* **Segurança (Whitelisting):** Apenas IDs previamente registados na Base de Dados podem enviar métricas válidas.
* **Resiliência:** Tratamento adequado de erros e reconexões caso o servidor fique offline.

## Decisões de Arquitetura

* **Agnosticismo de Protocolo:** A camada de negócio (MetricsProcessor) é totalmente independente do protocolo de entrada. O código que valida os dados e os grava na base de dados é único para todos, garantindo consistência e facilidade de manutenção.
* **Escolha Estratégica de Protocolos:**
  * **MQTT:** Utilizado na borda (sensores a bateria) devido ao seu baixo overhead e modelo publish/subscribe.
  * **gRPC:** Utilizado para gateways concentradores (alto tráfego) devido à enorme eficiência e compressão da serialização binária do Protobuf.
  * **REST:** Utilizado para gestão (Admin CLI) e eventual integração web devido à sua universalidade e facilidade de debugging.
* **Dados Agregados vs. Raw:** O servidor armazena os dados de forma bruta (raw) na base de dados. Contudo, a API expõe endpoints focados e otimizados para o cálculo de médias, transferindo a carga computacional para o servidor e reduzindo substancialmente o tráfego de rede para o cliente de administração.