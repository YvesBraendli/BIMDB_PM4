# Externe Schnittstellen

## The Movie Database API (TMDB API)
Sämtliche Daten werden über die von TMDB zur Verfügung gestellten Schnittstellen bezogen. [^1] In diesem Kapitel werden die technischen Details dieser Schnittstellen erläutert sowie ausgewählte Beispiele präsentiert.

### Technische Aspekte der Schnittstelle
- Authentifizierung: API-Schlüssel in der Anfrage als Parameter oder durch Benutzung des sogenannten Access Tokens als "Bearer" token.
- Nachrichtenformat: JSON-Format.
- Alle weiteren Details können der offiziellen Dokumentation entnommen werden. [^1]


### Nicht-technische Aspekte der Schnittstelle
- Zuständigkeit (Ownership): Die TMDB API wird von TMDB bereitgestellt und verwaltet. Die Verantwortung für die API liegt bei TMDB.
- Änderungsmanagement: Änderungen betreffend die API obliegen TMDB. Allfällige Kommunikationen in dieser Hinsicht müssen von BIMDB berücksichtigt werden.
- Service-Level-Agreements (SLAs): Gemäss Dokumentation stellt TMDB aktuell kein SLA zur Verfügung.

### API-Integration
API-Schlüssel als Anfrageparameter:
```
curl --request GET \
     --url 'https://api.themoviedb.org/3/movie/11?api_key=API_KEY'
```
Access Token als Bearer Token:
```
curl --request GET \
     --url 'https://api.themoviedb.org/3/movie/11' \
     --header 'Authorization: Bearer ACCESS_TOKEN'
```

### Implementierung

Bei der Implementierung der API Logik wurde auf die klare Trennung der Verantwortlichkeiten geachtet. Damit kann flexibel auf mögliche Änderungen der Schnittstelle reagiert werden. Die verschiedenen Klassen und deren Aufgaben können wie folgt zusammengefasst werden:

- **MovieDBApiConfig**: Konfigurationsklasse, die für das Speichern und Bereitstellen der API-spezifischen Informationen (API-Schlüssel und Basis-URL) zuständig ist.
- **BaseService**: Abstrakte Basisklasse, die von allen Service-Klassen erweitert wird und die Abstraktion von RestTemplate des Spring Frameworks verwendet. [^3]
- **ConfigService**: Für das Abrufen von API-Konfigurationsdaten verantwortlich.
- **MovieService**: Verantwortlich für das Abrufen von Filmdaten.
- **TvService**: Verantwortlich für das Abrufen Informationen über Serien.

---

[^1]: TMDB, Documentation [Online]. URL: https://developer.themoviedb.org/docs/getting-started (Stand 07.02.2023)

[^2]: DigitalOcean, An Introduction to GraphQL [Online]. URL: https://www.digitalocean.com/community/tutorials/an-introduction-to-graphql (Stand 07.02.2023)

[^3]: Spring Framework, Rest Template Documentation [Online]. URL: https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html (Stand 07.05.2023)
***