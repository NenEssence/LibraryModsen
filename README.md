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
    docker-compose -f docker-compose.noservice.yml -d

6. Запустить сервисы api-gateway, eureka-server, book-service, library-service, identity-service
