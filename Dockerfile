# Используем официальный образ OpenJDK
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем собранный JAR-файл (теперь с правильным именем!)
COPY target/telegram-bot-1.0-SNAPSHOT.jar /app/app.jar

# Указываем команду для запуска
CMD ["java", "-jar", "app.jar"]