SHELL := cmd
.SHELLFLAGS := /C

JAR=postgresql-42.7.8.jar
BIN=bin
SOURCES=$(wildcard *.java)

compile:
	if not exist "$(BIN)" mkdir "$(BIN)"
	javac -cp "$(JAR);." -d "$(BIN)" $(SOURCES)
	copy /Y "config.properties" "$(BIN)\config.properties" >nul

run-geral:
	java -cp "$(BIN);$(JAR);." ServidorGeral

client-geral:
	java -cp "$(BIN);$(JAR);." ClienteGeral

client-admin:
	java -cp "$(BIN);$(JAR);." ClienteAdmin

clean:
	if exist "$(BIN)" rmdir /s /q "$(BIN)"
