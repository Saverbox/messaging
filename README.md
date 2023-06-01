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
