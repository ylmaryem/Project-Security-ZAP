# Utiliser l'image de base Java
FROM openjdk:17-jdk-alpine

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier JAR dans le container
COPY target/Tasks-SpringBoot-0.0.1-SNAPSHOT.jar app.jar

# Exposer le port 8080
EXPOSE 8080

# Lancer l'application avec Java
CMD ["java", "-jar", "app.jar"]


