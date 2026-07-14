# Sistema de Monitorização Ambiental

**Universidade de Évora - Sistemas Distribuídos**

## Tecnologias Utilizadas

* **Linguagem:** Java 17+
* **Framework:** Spring Boot 3.x (Web, Data JPA, Integration)
* **Protocolos:**
    * **gRPC:** Google Protocol Buffers (`.proto`)
    * **MQTT:** Mosquitto
    * **REST:** Java `HttpClient` & Spring Web MVC
* **Base de Dados:** PostgreSQL
* **DevOps:** Docker Desktop, Maven


## Pré-requisitos

* **Java JDK 17** ou superior.
* **Maven** instalado.
* **Docker desktop** a correr.

## Instalação e Execução

### 1. Iniciar a Infraestrutura (Docker)
Na raiz do projeto (onde está o `docker-compose.yml`), execute:

docker compose up -d

*Isto iniciará a Base de Dados PostgreSQL e o Broker MQTT.*

### 2. Iniciar o Servidor
Navegar até à pasta do servidor e correr:

cd server
mvn spring-boot:run

*O servidor ficará à escuta nas portas: 8080 (REST), 9090 (gRPC) e 1883 (ligação ao Broker).*

### 3. Configurar Dispositivos (Admin CLI)
**Importante:** O servidor rejeita dados de sensores desconhecidos por segurança. Utilize o CLI para registar os IDs primeiro.

cd client-admin
mvn exec:java

1.  No menu, escolha **"1. Gestão de Dispositivos"**.
2.  Escolha **"2. Adicionar novo dispositivo"**.
3.  Registe os IDs para usar nos sensores.

### 4. Correr os Sensores
Abrir terminais para cada sensor que deseja simular.

**Sensor REST:**

cd client-rest
mvn exec:java
# Introduza o ID registado quando pedido


**Sensor MQTT:**

cd client-mqtt
mvn exec:java
# Introduza o ID registado quando pedido


**Sensor gRPC:**

cd client-grpc
mvn exec:java
# Introduza o ID registado quando pedido