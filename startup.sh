#!/bin/bash

# Caminho para o arquivo JAR da sua aplicação
JAR_FILE="pizzaonline-0.0.1-SNAPSHOT.jar"

# Verifica se o arquivo JAR existe
if [ ! -f "$JAR_FILE" ]; then
  echo "Arquivo JAR não encontrado: $JAR_FILE"
  exit 1
fi

# Inicia a aplicação Java
java -jar "$JAR_FILE"
