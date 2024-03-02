# Blog-Backend Applikation
Dieses Repository enthält eine Blog-Backend-Applikation, die mithilfe von Docker Containern aufgebaut ist. Die Applikation verwendet eine Microservice-Architektur, wobei die einzelnen Services in Docker Containern laufen. Die Container Images sind auf GitHub Container Registry (GHCR) gehostet.

## Voraussetzungen
Bevor Sie die Applikation starten, stellen Sie sicher, dass Sie die folgenden Tools installiert haben:

- Docker
- Docker Compose
- Git (optional, für das Klonen des Repositories)

## Repository klonen
Um mit dem Projekt zu beginnen, klonen Sie das Repository auf Ihren lokalen Computer:

```
git clone [[URL Ihres GitHub-Repositories]](https://github.com/Saverbox/messaging.git)
cd ./messaging
```

## Docker Images und Container
Die benötigten Docker Images für die verschiedenen Services des Blog-Backends sind auf GHCR gehostet. Sie brauchen keine manuelle Aktion, da die Images automatisch von Docker Compose heruntergeladen werden, wenn Sie die Applikation starten.

## Applikation starten
Um die Applikation zu starten, führen Sie den folgenden Befehl im Root-Verzeichnis des geklonten Repositories aus:

```
docker-compose up -d
```
Dieser Befehl startet alle Services im Hintergrund. Um den Status der laufenden Container zu überprüfen, verwenden Sie:

```
docker ps
```

## Services
Die Applikation besteht aus den folgenden Services:

1. blog-backend: Der Hauptservice der Blog-Anwendung.
2. validation-service: Ein Service zur Validierung von Daten.
3. redpanda: Ein Kafka-kompatibler Messaging-Service.
4. database: Eine PostgreSQL-Datenbank für die Datenspeicherung.

## API-Endpunkte testen
Nachdem die Services gestartet sind, können Sie die API-Endpunkte testen. Beispiel:

```
http POST http://localhost:8080/blogs title="Mein Blogpost 2" content="hftm is best"

http POST http://localhost:8080/blogs title="Mein Blogpost 2" content="hftm sucks"  

http GET http://localhost:8080/blogs
```






