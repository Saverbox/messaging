# blog-backend

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev

```

## Umsetzung

Zur Umsetzung der Aufgabe, habe ich eine "Blog" und eine "Comment" Klasse erstellt.

Durch den PanacheEntity Import habe ich die Objekte Persistiert und über die Anotation @OnetoMany und @ManytoOne miteinander verknüpft.

```
@ManyToOne
    public Blog blog;

```
## API-Design

Für die Abfrage der Blogs sollen folgende Funktionen zur verfügung stehen:

### Blog-API-Functions:
GET /blogs: Ruft alle Blogs ab.

GET /blogs/{id}: Ruft einen bestimmten Blog anhand der ID ab.

POST /blogs: Erstellt einen neuen Blog.

PUT /blogs/{id}: Aktualisiert einen bestimmten Blog anhand der ID.

DELETE /blogs/{id}: Löscht einen bestimmten Blog anhand der ID.

GET /blogs/{id}/comments: Ruft alle Kommentare für einen bestimmten Blog anhand der ID ab.

POST /blogs/{id}/comments: Erstellt einen neuen Kommentar für einen bestimmten Blog anhand der ID.

### Comment-API-Functions:
GET /comments: Ruft alle Kommentare ab.

GET /comments/{id}: Ruft einen bestimmten Kommentar anhand der ID ab.

PUT /comments/{id}: Aktualisiert einen bestimmten Kommentar anhand der ID.

DELETE /comments/{id}: Löscht einen bestimmten Kommentar anhand der ID.

Ich habe bereits begonnen einzelne Ansatze umzusetzen und die nötigen Abfragen in der Service Klasse zu erstellen.
