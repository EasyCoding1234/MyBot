# Используем официальный образ OpenJDK
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем файлы в контейнер
COPY target/my-bot.jar /app/my-bot.jar

# Указываем команду для запуска
CMD ["java", "-jar", "my-bot.jar"]