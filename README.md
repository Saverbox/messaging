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

Für die Abfrage der Blogs stehen folgende Funktionen zur verfügung:

### Blog-API-Funktionen:
GET /blogs: Ruft alle Blog-Einträge ab.

GET /blogs/{id}: Zeigt einen spezifischen Blog-Eintrag basierend auf seiner ID.

POST /blogs: Fügt einen neuen Blog-Eintrag hinzu.

PUT /blogs/{id}: Aktualisiert einen Blog-Eintrag.

DELETE /blogs/{id}: Löscht einen Blog-Eintrag nach ID.

GET /blogs/{id}/comments: Zeigt alle Kommentare eines Blog-Eintrags basierend auf der Blog-ID.

POST /blogs/{id}/comments: Fügt einen Kommentar zu einem Blog-Eintrag hinzu.

DELETE /blogs/{blogId}/comments/{commentId}: Löscht einen Kommentar von einem bestimmten Blog-Eintrag.

### Kommentar-API-Funktionen:
GET /comments: Ruft alle Kommentare ab.

GET /comments/{id}: Einen Kommentar anhand seiner ID abrufen.

PUT /comments/{id}: Einen Kommentar aktualisieren.

DELETE /comments/{id}: Einen Kommentar löschen.

## Berechtigungskonzept

Verschiedene Benutzer könnten unterschiedliche Rollen und damit unterschiedliche Berechtigungen haben:

**Guest:** Ein nicht angemeldeter Benutzer. Hat nur Zugriff auf öffentliche Methoden.

**Blogger:** Ein angemeldeter Benutzer, der Blogs und Kommentare erstellen, bearbeiten und löschen kann, aber nur seine eigenen.

**Admin:** Ein Benutzer mit höheren Rechten, der auf alle Funktionen und alle Blog-Einträge zugreifen kann.

Die Funktionen werden folgendermassen zugeteilt:

| Guest / Öffentlich: | Blogger: | Admin: |
| ----------- | ----------- | ----------- |
|Alle GET Funktionen| POST / PUT / DELETE auf eigenen Blogs| Alle Funktionen | 








