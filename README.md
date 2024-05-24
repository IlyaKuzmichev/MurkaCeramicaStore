# MurkaCeramicaStore
## Test task for a Java developer vacancy

### Описание
 - Приложение реализует CRUD операции над объектом, с консольным интерфейсом
 - Абстрактное приложение склада керамического магазина
 - __Код лежит в ветке *develop*, создан [pull-request](https://github.com/IlyaKuzmichev/MurkaCeramicaStore/pull/1) в *master*__

### Использованные технологии
 - *Java 17*, *Spring 6*
 - *JdbcTemplate* - для взаимодействия с БД
 - *JUnit5, Mockito* - для Unit-тестов
 - *Logback* и "сахар" из *Lombok*  - для логгирования
 - *PostgreSQL* - база данных
 - *Liquibase* - для миграций БД
 - *H2* - in-memory база данных для тестов
 - *Maven JavaDoc plugin* - для формирования документации

### Сборка приложения
 - ```mvn package```
 - ```docker compose up -d``` для проверки работоспособности приложения поднимает БД в контейнере и накатывает миграции

### Запуск приложения
 - ```java -jar target/MurkaCeramicaStore-1.0-jar-with-dependencies.jar```

### Документация
 - *target/javadoc/apidocs/index.html*

### Очистка
 - ```mvn clean```
 - ```docker compose down -v```
