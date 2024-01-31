Приложение считывает tickets.json файл и рассчитывает показатели:
- Минимальное время полета между городами для каждого перевозчика
- Разница между средней ценой и медианой для полета между городами

Системные требования:
- Java 17
- Maven

Инструкции по установке:
1. Клонировать репозиторий в локальную директорию через SSH: ```git clone git@github.com:mdemidkin1992/java-tickets.git```
2. Перейти в локальную директорию, осуществить сборку jar файла: ```mvn clean package```
3. Запустить приложения из CLI: ```java -jar target/java-tickets-1.0-SNAPSHOT-jar-with-dependencies.jar```