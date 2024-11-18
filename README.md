# Back-End Quinta do Ypuã

Este é o projeto back-end para o gerenciamento da pousada Quinta do Ypuã, desenvolvido com **Java Spring Boot** e seguindo o padrão de arquitetura de três camadas. A aplicação utiliza o banco de dados **MySQL** e foi projetada para ser facilmente implantada em diferentes ambientes usando **Docker**.

Durante o desenvolvimento, os testes foram realizados utilizando o **Insomnia** para enviar requisições HTTP aos endpoints da aplicação. A API foi documentada com o **Swagger**, proporcionando uma interface intuitiva para explorar e testar os endpoints, simplificando o desenvolvimento de clientes front-end e integrações de serviços.

---

## 🛠 Pré-requisitos

Antes de começar, certifique-se de ter as seguintes ferramentas instaladas:
- [Docker](https://www.docker.com/)
- [Docker Desktop](https://www.docker.com/products/docker-desktop/)

---

## ⚙️ Configuração do Ambiente de Desenvolvimento

### 1. Clone do projeto, compilação e Criação da Imagem do Servidor

Faça o download do projeto para o seu ambiente local:

```bash
git clone <URL_DO_REPOSITORIO>
```

Entre na pasta do projeto clonado:
```bash
cd <NOME_DO_DIRETORIO_DO_PROJETO>
```

Na raiz do projeto, execute o comando abaixo para compilar o projeto e criar a imagem Docker do servidor:

```bash
docker build -t pousada-server .
```

Para criar o contêiner com o MySQL e iniciar todos os serviços definidos no arquivo docker-compose.yml, execute:
```bash
docker-compose up
```

Para explorar e testar os endpoints da aplicação, abra o navegador no seguinte endereço:
```bash
http://localhost:8080/swagger-ui.html
```

Siga para o projeto do front-end em:
- [FrontEnd-Quinta do Ypuã](https://github.com/giovannitamanini/quinta-do-ypua-front)