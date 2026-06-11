
# Detection Management Platform

Webapplikation til at administrere og logge detektionstests mappet til MITRE ATT&CK-teknikker. Udviklet som afsluttende projekt på datamatikeruddannelsen i samarbejde med CIP.

## Kom i gang

Du skal bruge Java 17+, Maven og MySQL installeret.

Kør først `schema.sql` i MySQL for at oprette databasen og tabellerne. Filen ligger i `src/main/resources/`.

Sæt derefter disse tre miljøvariabler:

```
DB_URL=jdbc:mysql://localhost:3306/detection_platform
DB_USER=dit_brugernavn
DB_PASSWORD=dit_password
```

Start applikationen med:

```
mvn spring-boot:run
```

Åbn derefter [http://localhost:8080](http://localhost:8080) i browseren.
