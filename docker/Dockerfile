# Estágio de compilação
FROM maven AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests=true

# Estágio de produção
FROM ubuntu:latest
WORKDIR /app

# Instalação do JDK
RUN apt-get update && apt-get install -y openjdk-17-jdk wget

# Instalação do Dockerize
RUN wget https://github.com/jwilder/dockerize/releases/download/v0.7.0/dockerize-linux-amd64-v0.7.0.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-v0.7.0.tar.gz \
    && rm dockerize-linux-amd64-v0.7.0.tar.gz

# Copiando o arquivo JAR e o script wait-for-it
COPY --from=build /app/target/vr-mini-autorizador-1.0.0.jar /app/vr-mini-autorizador.jar
COPY ./docker/scripts/wait-for-it.sh /app/wait-for-it.sh

# Definindo permissões para o script
RUN chmod +x /app/wait-for-it.sh

# Expondo a porta 8080
EXPOSE 8080

# Comando de inicialização da aplicação
CMD ["./wait-for-it.sh", "mysql", "--", "java", "-jar", "vr-mini-autorizador.jar"]
