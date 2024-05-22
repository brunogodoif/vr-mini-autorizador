FROM maven AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests=true

FROM ubuntu:latest
WORKDIR /app

RUN apt-get update && apt-get install -y wget default-jdk

RUN wget https://github.com/jwilder/dockerize/releases/download/v0.7.0/dockerize-linux-amd64-v0.7.0.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-v0.7.0.tar.gz \
    && rm dockerize-linux-amd64-v0.7.0.tar.gz

COPY --from=build /app/target/vr-mini-autorizador-1.0.0.jar /app/vr-mini-autorizador.jar
COPY ./docker/scripts/wait-for-it.sh /app/wait-for-it.sh

RUN chmod +x /app/wait-for-it.sh

EXPOSE 8080

CMD ["./wait-for-it.sh", "mysql", "--", "java", "-jar", "vr-mini-autorizador.jar"]