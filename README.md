<p align="center">
    <img src="https://www.svgrepo.com/show/184143/java.svg" width="130" />
    <img src="https://www.edureka.co/blog/wp-content/uploads/2019/08/recyclebin-data-1.png" width="220" />
</p>

# Vr Mini autorizador - v1.0.0


## Funcionalidades implementadas

[x] 

## Pré-requisitos

Para execução do serviço é necessário ter instalado no ambiente os softwares abaixo nas versões descritas ou superiores:

- Java SDK 21
- Maven v3.9.6
- Docker (Opcional)

## Principais dependências

- Spring Boot v3.2.5
- Spring Data JPA
- Mysql Driver
- Lombok

### Instalação de dependências

``` bash
   mvn install
```

## Environment Variables

O projeto se utiliza de variáveis de ambiente que podem ser definidas nos arquivos **application.yaml**

## Testes


``` bash
    mvn test
```

## Execução local

Para executar o projeto de forma local, faça a configuração do arquivo application.yml e execute os comandos abaixo.

``` bash
   spring-boot:run
```

A aplicação por padrão estara disponivel em **http://127.0.0.1:8080** a porta pode ser configurada no arquivo **application.yml** no atributo **server.port**

## Instalação e execução com Docker

Para facilitar instalação e execução da aplicação foi implementado containers Docker, onde é realizado o processo de build, testes e execução.
O profile configurado para utilizanção no Docker é o **application-docker.yml**

Estrutura dos arquivos Docker:

- **Dockerfile:** responsável por realizar o build da imagem da aplicação
- **docker-compose.yml:** responsável por realizar o build e execução do servicos

Os passos abaixo devem ser executados na raiz do projeto.

### Build

``` bash
    docker-compose -p vr-mini-autorizador -f docker/docker-compose.yml build
```

### Up

``` bash
    docker-compose -p vr-mini-autorizador -f docker/docker-compose.yml up -d
```

### Down

``` bash
    docker-compose -p vr-mini-autorizador -f docker/docker-compose.yml down
```

## Swagger

Para facilitar a documentação e interação com a API deste projeto, utilizamos o Swagger, uma ferramenta que
permite visualizar, testar e entender melhor os endpoints disponíveis. Abaixo estão as principais informações
relacionadas ao Swagger neste projeto:


### Acessando o Swagger
Após iniciar o serviço localmente ou através do Docker, você pode acessar a interface do Swagger no seguinte endereço: **http://localhost:8080/swagger-ui-custom.html**
### Explorando a API
Ao acessar o Swagger, você terá uma visão completa dos endpoints disponíveis, suas descrições, tipos de requisições
suportadas (GET, POST, etc.), parâmetros necessários e exemplos de resposta.


### Testando Endpoints
O Swagger permite que você teste os endpoints diretamente na interface, fornecendo entradas de dados e visualizando as
respostas. Isso facilita o processo de desenvolvimento e depuração.


## Desenvolvedor

- Bruno F Godoi - brunofgodoi@outlook.com.br