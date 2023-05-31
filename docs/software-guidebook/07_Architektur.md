# Architektur

Die Softwarearchitektur von BIMDB kann den nachfolgenden Diagrammen entnommen werden.

## System Kontext

![System Context Diagram](https://gabrielbenabou.gitlab.io/bimdb/assets/img/structurizr-SystemContext.png)

Das Systemkontextdiagramm stellt die Grenzen der Anwendung respektive Software dar und zeigt die Beziehungen und Interaktionen, die das System mit externen Akteuren oder anderen Systemen hat [^1].

Die Hauptkomponenten des BIMDB Systemkontextdiagramms sind:

- Das System, BIMDB

- Externe Entitäten, die Benutzer (User) und die Filmdatenbank TMDB 

- Beziehungen/Interaktionen: Vom Benutzer zum System über den Aufruf der Software sowie von der bidirektionale Beziehung zwischen Software und Datenbank über einen API Aufruf.

Das Systemkontextdiagramm ist nützlich, um die externe Perspektive eines Systems zu visualisieren und bietet eine hohe Abstraktionsebene. Es ist oft der erste Schritt bei der Systemmodellierung und hilft, das Verständnis über das System und seine Umgebung zu erhöhen.

## Container-Diagramm

![Container Diagram](https://gabrielbenabou.gitlab.io/bimdb/assets/img/structurizr-Container.png)

Das Container Diagram von BIMDB stellt die high-level Software-Architektur des Systems dar und zeigt, wie die Software-Anwendungen (als "Container" bezeichnet) organisiert sind und wie sie interagieren.

Im Kontext eines Container-Diagramms ist ein "Container" eine lauffähige Einheit der Softwareinfrastruktur. BIMDB enthält folgende Container-Einheiten [^3]:

- Web Applikation
- API Applikation
- Datenbank

Zusätzlich sind folgende externe Container eingebunden:

- Keycloak
- TMDB

### Keycloak: Authentifizierung und Autorisierung der Benutzer in unserer Applikation

Um die Sicherheit der Kommunikation zu gewährleisten, wird OAuth 2.0 mit dem Authorization Code Flow sowie JSON Web Tokens genutzt. Das Frontend («Angular») erhält vom Identity Access Management Container («Authorization Server») einen Zugriffstoken in Form eines JWT. Der Zugriffstoken wird bei jeder Anfrage vom Frontend («Client») an das Backend («Resource Server») in einem Authorization-Header übermittelt. Das Backend validiert den JWT, um die Identität des Benutzers zu überprüfen und sicherzustellen, dass er die erforderlichen Berechtigungen hat.

Durch die Verwendung einer REST-Schnittstelle und die Sicherung der Kommunikation mit OAuth 2.0 und JWTs wird eine sichere Kommunikation zwischen Frontend und Backend gewährleistet. Dies ermöglicht eine klare Trennung der Verantwortlichkeiten (Separation of Concerns). Der Cross-Cutting Concern «Security» ist ausgelagert.

### TMDB

Die TMDB API (The Movie Database API) ist eine Programmierschnittstelle, die von der Website "The Movie Database" bereitgestellt wird. Sie ermöglicht Entwicklern den Zugriff auf umfangreiche Datenbanken von Filmen, Fernsehserien und Künstlern. Die API stellt verschiedene Endpunkte und Funktionen zur Verfügung, um Informationen über Filme, TV-Shows, Personen, Genres, Bewertungen und vieles mehr abzurufen.

Mit der TMDB API kann BIMDB Film- und Serien-Daten programmgesteuert abrufen und in ihre eigenen Anwendungen oder Websites integrieren [^4]. 

## Komponenten-Diagramm

Das Komponenten-Diagram ist in das Komponenten-Diagram für Frontend und Backend unterteilt. Es zeigt die Organisation und Abhängigkeiten zwischen verschiedenen Komponenten in einem System [^2]. Das BIMDB Komponenten-Diagram für das Frontend beinhaltet folgende Komponenten:
Web Applikation Komponente mit Dashboard (Discover), Movie und Serien Listen sowie Detailinformationen zu Filmen und Serien.

![System Context Diagram](https://gabrielbenabou.gitlab.io/bimdb/assets/img/structurizr-WebApp.png)

Das BIMDB Komponenten-Diagram für das Backend beinhaltet die Komponenten: Controller für API, Inhalt auf den unterschiedlichen Seiten der Software Applikation, interne Datenbank (Data Enhancement Controller) sowie den Zugriff auf TMDB und Security.

![System Context Diagram](https://gabrielbenabou.gitlab.io/bimdb/assets/img/structurizr-BackendApp.png)

[^1]: Stanford University IT (2023) Analysis Diagrams [Online]. URL: https://uit.stanford.edu/pmo/analysis-diagrams (Stand 10.05.2023)

[^2]: International Business Machines Corporation [IBM] (2021) Komponentendiagramme [Online]. URL: https://www.ibm.com/docs/de/rational-soft-arch/9.6.1?topic=diagrams-component (Stand 13.05.2023)

[^3]: Docker (2023) Use containers to Build, Share and Run your applications [Online]. URL: https://www.docker.com/resources/what-container/ (Stand 12.05.23)

[^4]: TMDB (2023) FAQ [Online]. URL: https://developer.themoviedb.org/docs/faq (Stand 25.05.23)