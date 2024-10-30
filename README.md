# Library Project

**Версия:** 1.0.0

## Инструкция по запуску

### Вариант 1 (полный)

1. Склонировать репозиторий.
2. В папке с проектом перейти в `deployment/`:
   
   ```bash
   cd deployment/
4. Запустить docker-compose
   
   ```bash
   docker-compose up -d

### Вариант 2 (только базы данных в docker)
1. Склонировать репозиторий.
2. В папке с проектом перейти в `deployment/`:
   
    ```bash
    cd deployment/
4. Запустить docker-compose.noservice
   
    ```bash
    docker-compose -f docker-compose.noservice.yml up -d
6. Запустить сервисы api-gateway, eureka-server, book-service, library-service, identity-service
   
## [Postman collection](https://web.postman.co/workspace/Public~8eef24eb-df35-470a-87dc-548ca55407ae/collection/32315386-f510b1d4-9e01-4ea5-af66-4acdd9816b16) <- 
## [Swagger](http://localhost:8765/swagger-ui.html) <-
