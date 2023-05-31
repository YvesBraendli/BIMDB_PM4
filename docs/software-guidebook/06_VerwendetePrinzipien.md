# Verwendete Prinzipien

Im Rahmen der Entwicklung von BIMDB wurden verschiedene Softwareentwicklungsprinzipien bewusst angewendet, um den aktuellen Branchenstandards in der Softwareentwicklung zu entsprechen. Die hier beschriebenen Prinzipien wurden entweder in einzelnen oder mehreren Stellen der Architektur umgesetzt. Sofern für das Verständnis nützlich werden die Prinzipien anhand von Quellcodebeispielen erläutert. 

## Grundlegende Prinzipien

Die Prinzipien, welche in diesem Abschnitt vorgestellt werden, können als grundlegende Prinzipien im Sinne eines Mindeststandards verstanden werden. 

### DRY (Don't Repeat Yourself)
Gemäss dem DRY-Prinzip sollen Informationen oder Funktionalitäten in der Software nur an einem Ort vorhanden sein sollten, um Redundanzen zu vermeiden und die Wartbarkeit zu verbessern.

### KISS (Keep It Simple, Stupid)
Das KISS-Prinzip fordert, dass die Softwarelösungen einfach und verständlich gehalten werden sollten. Dies erleichtert die Wartung und reduziert die Wahrscheinlichkeit von Fehlern.

### YAGNI (You Aren't Gonna Need It)
Es sollen nur jene Funktionen implementiert werden, die zum aktuellen Zeitpunkt benötigt werden. Mit anderen Worten sollen keine Funktionen entwickelt werden, welche möglicherweise in der Zukunft nützlich sein könnten.

### SOLID-Principles
- Single Responsibility Principle (SRP): Jede Klasse sollte nur eine Verantwortung haben.
- Open/Closed Principle (OCP): Softwaremodule sollten offen für Erweiterungen, aber geschlossen für Modifikationen sein.
- Liskov Substitution Principle (LSP): Objekte einer abgeleiteten Klasse sollten die Objekte der Basisklasse ersetzen können, ohne die Korrektheit des Programms zu beeinflussen.
- Interface Segregation Principle (ISP): Klassen sollten nicht gezwungen werden, Interfaces zu implementieren, die sie nicht verwenden.
- Dependency Inversion Principle (DIP): Abhängigkeiten sollten auf Abstraktionen und nicht auf konkreten Implementierungen basieren.

### Separation of Concerns (SoC)
Dieses Prinzip bedeutet, dass verschiedene Aspekte der Software in unabhängige, lose gekoppelte Module aufgeteilt werden sollten. So wird die Wartbarkeit verbessert und die Komplexität reduziert. 

## Spezifische Prinzipien

Abschliessend soll in diesem Abschnitt auf spezifischere Prinzipien eingegangen werden, welche im Rahmen der Entwicklung bei gewissen Logiken bewusst verfolgt wurden, d.h. im Sinne von Blaupausen für eine solide und moderne Softwarearchitektur.

### Frontend

#### Model-View-Controller Pattern

Eines der vermutlich bekanntesten und verbreitetsten Softwarearchitekturmuster ist das Model-View-Controller Pattern (MVC), welches den Code in drei logische Schichten unterteilt. Jede Schicht hat klar definierte Zuständigkeiten, wodurch der Datenfluss innerhalb der Software grundsätzlich kontrolliert wird.
- Model: Datenverarbeitung und -verwaltung (Ausführen der Geschäftslogik). Z.B. die Dienste, welche Informationen von externen Quellen abrufen (z.B. via APIs), oder Interfaces (in contract.ts, generiert aus Java Klassen). 
- View: Struktur und Aussehen der Benutzeroberfläche. Wird durch die verschiedenen Komponenten (*components.html) realisiert.
- Controller: Verarbeitung der Benutzereingaben. Die von den Views empfangenen Benutzereingaben, werden an die zuständigen Controller weitergeleitet (*components.ts)

Ein Codebeispiel ist an dieser Stelle vermutlich nicht angebracht, da das MVC Pattern fester Bestandteil der Architektur einer Angular Anwendung ist und somit quasi automatisch in BIMDB umgesetzt wurde.[^1]

#### Dependency Injection / Singleton

Das Dependency Injection Pattern verlangt, das die Abhängigkeiten eines Objekts (z.B. andere Objekte, welche für die Funktionalität benötigt werden) über dessen Eigenschaften oder Konstruktorparameter bereitstellt gestellt werden, ohne das diese Abhängigkeiten in der Implementierung des Objektes selbst zu erstellen wären. Durch die entstehende lose Koppelung kann insbesondere die Testbarkeit des Objektes vereinfacht werden.

Das Singleton-Pattern gewährleistet, dass eine spezifische Klasse nur eine einzige Instanz hat und stellt einen globalen Zugriffspunkt auf diese Instanz zur Verfügung. Damit kann eine unnötige oder gar unerwünschte Mehrfacherzeugung von bestimmten Klassen verhindert werden.

Diese zwei Prinzipien könnte man vermutlich auch als grundlegend qualifizieren. Da die Umsetzung in Angular aber sehr beispielhaft ist, möchten wir an dieser Stelle kurz auf ein Codebeispiel eingehen. Mit der von Angular zur Verfügung gestellten Annotation **@Injectable** kann eine Dependency Injection ganz einfach definiert werden. Mit der **"providedIn": "root"** Instruktion wird die relevante Klasse zudem als Singleton auf globaler Ebene definiert. D.h. andere Klassen können auf die globale Instanz des Singletons zugreifen. 

```
// File: notification.service.ts
// Das Singleton mit folgender Annotation definiert
@Injectable({
    providedIn: 'root'
})
[...]

// File: notification.components.ts
// Zugriff auf Singleton via Dependency Injection
public constructor(
    private router: Router, 
    private notificationService: NotificationService,  // Dependency Injection des Singleton
    private cd: ChangeDetectorRef
)

// Die Funktionalität des NotificationService kann anschliessend verwendet werden
this.notificationService.notificationSubject$.pipe(
    takeUntil(this.onDestroy$)).subscribe(notification => {
    this.notifications.push(notification);
    this.cd.detectChanges();
});
[...]

```

### Backend

#### Builder-Pattern
Die Klasse TmdbUrlBuilder wird verwendet, um URLs für die API calls an TMDB (The Movie Database) zu konstruieren. Der Builder ermöglicht es, schrittweise die verschiedenen Komponenten der URL festzulegen und am Ende die vollständige URL zu generieren.

Der Builder verwendet die Klasse UriComponentsBuilder aus dem Spring Framework, um die URL zu konstruieren. Dieser Builder wird im Konstruktor initialisiert.

Es gibt verschiedene Methoden wie withEndpoint, withPage, withGenres, withCast usw., die verwendet werden können, um die verschiedenen Komponenten der URL festzulegen. Jede Methode prüft, ob der übergebene Wert vorhanden ist, und fügt ihn gegebenenfalls zur URL hinzu.

Der Builder unterstützt auch das Hinzufügen mehrerer Werte für bestimmte Parameter, wie z.B. Genres oder Schauspieler, indem er die Werte mit einem bestimmten Trennzeichen (in diesem Fall OR_DELIMITER oder AND_DELIMITER) verbindet.

Schliesslich gibt es die Methode build(), die die URL aus den gesetzten Komponenten erstellt und als Zeichenkette zurückgibt.

In Zukunft, wenn TMDB ihre API ändert (zum Beispiel durch Ändern eines Requestparameternamens oder der verwendeten Trennzeichen), wären die Änderungen auf diese Klasse beschränkt.

```
public class TmdbUrlBuilder {

    private static final String OR_DELIMITER = "|";
    private static final String AND_DELIMITER = ",";
    private UriComponentsBuilder uriBuilder;

    public TmdbUrlBuilder(String baseUrl) {
        this.uriBuilder = UriComponentsBuilder.fromUriString(baseUrl);
    }

    public TmdbUrlBuilder withEndpoint(String endpoint) {
        if (StringUtils.hasText(endpoint)) {
            uriBuilder.path(endpoint);
        }
        return this;
    }

    public TmdbUrlBuilder withPage(Integer page) {
        if (page != null) {
            uriBuilder.queryParam("page", page);
        }
        return this;
    }

    public TmdbUrlBuilder withGenres(List<Integer> genres) {
        if (!CollectionUtils.isEmpty(genres)) {
            uriBuilder.queryParam("with_genres", getParamValue(OR_DELIMITER, genres));
        }
        return this;
    }

    public TmdbUrlBuilder withCast(List<Long> actors) {
        if (!CollectionUtils.isEmpty(actors)) {
            uriBuilder.queryParam("with_cast", getParamValue(OR_DELIMITER, actors));
        }
        return this;
    }
    ...
    public String build() {
        UriComponents uriComponents = uriBuilder.build();
        return uriComponents.toUriString();
    }

    private <T> String getParamValue(String delimiter, Collection<T> values) {
        return String.join(delimiter, values.stream().map(String::valueOf).toArray(String[]::new));
    }
}
```

#### Master-Slave-Pattern
Das Master-Slave-Pattern beschreibt eine Softwarearchitektur, in der eine Komponente (Master) Kontrolle und Befehlsgewalt über eine oder mehrere andere Komponenten (Slaves) hat. Insbesondere das Zusammenspiel von Controller- und Service-Klassen kann als Umsetzung des Master-Slave-Patterns gesehen werden: Sämtliche API-Anfragen werden vom Master (Controller) entgegengenommen, analysiert und schliesslich zur eigentlichen Verarbeitung an den zuständigen Service (Slave) weitergereicht.

Die Vorteile dieser Aufgabenverteilung sind offensichtlich die lose Koppelung und die daraus resultierende Austauschbar- und Wartbarkeit der einzelnen Komponenten.

Dieses Prinzip haben wir aber auch für die Erstellung von benutzerspezifischen Filmvorschlägen umgesetzt. Diese Logik berechnet die fünf häufigsten Genres in der Liste von Lieblingsfilmen des Benutzers und erstellt daraus ein Objekt der **Filter**, welche anschliessend an anderer Stelle im Code dazu verwendet werden kann, um die gesamte Filmdatenbank entsprechend zu filtern (z.B. für personalisierte Filmvorschläge).


```
public class FilterCalculator<T extends Filterable> {

...

    public Filter getFilter(Set<T> favorites, Set<Long> favoriteActors) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Filter filter = new Filter();
        Map<Integer, Integer> genreOccurrence = calculateGenreOccurrences(favorites);
        Float minVoteAverage = calculateFloatValue(favorites,
                Filterable::getVoteAverage,
                Float::min);

        Date latestReleaseDate = calculateDateValue(favorites,
                Filterable::getReleaseDate,
                BinaryOperator.maxBy(Date::compareTo));

        List<Integer> topGenres = getTopGenres(genreOccurrence);
        if (!CollectionUtils.isEmpty(topGenres)) {
            filter.setGenresToInclude(topGenres.stream().limit(3).toList());
        }
        if (!CollectionUtils.isEmpty(favoriteActors)) {
            filter.setActors(favoriteActors.stream().limit(5).toList());
        }

        filter.setLatestReleaseDate(formatter.format(latestReleaseDate));
        filter.setMinVoteAverage(minVoteAverage);

        return filter;
    }
...
}
```

#### Proxy Pattern
"Der Proxy, auch Stellvertreter genannt, ist ein Entwurfsmuster aus dem Bereich der Softwareentwicklung, das zur Kategorie der Strukturmuster [...] gehört. Das Muster überträgt die Steuerung eines Objektes auf ein vorgelagertes Stellvertreterobjekt."[^2] Im Kern handelt es um eine zusätzliche logische Schicht, die als Schnittstelle zu einer anderen Ressource verwendet wird. Dieses Muster ist besonders nützlich, wenn die Erstellung eines Objekts ressourcenintensiv ist oder wenn der Zugriff auf das Objekt auf bestimmte Weise kontrolliert werden muss.

Da die Funktionalität der BIMDB stark von der TMDB API abhängt, haben wir uns dafür entschieden, dieses Pattern für die Handhabung von HTTP-Anfragen an die erwähnte API zu verwenden. 

Im nachfolgenden Codeausschnitt sieht man die Implementierung der Proxy Klasse (**MovieDbHttpRequest**), welche für die Instanziierung einen **HttpRequest** den **API-Schlüssel** benötigt. Die eigentliche Logik, welche für die Kommunikation mit der TMDB benötigt wird, wird durch diese Klasse verdeckt. Dadurch wird eine einfache und konsistente Schnittstelle für das Versenden der API-Anfragen sichergestellt. Das effektive "Versenden" der API-Anfragen wird schliesslich durch die Spring Boot Klasse "ClientHttpRequestExecution" vorgenommen, auf welche an dieser Stelle nicht weiter eingegangen wird.[^3]

```
private static class MovieDbHttpRequest extends HttpRequestWrapper {

    private static final String LANGUAGE_QUERY_PARAM = "language";
    private static final String TOKEN_TYPE = "Bearer ";

    public MovieDbHttpRequest(HttpRequest request, String apiKey) {
        super(request);
        request.getHeaders().add(HttpHeaders.AUTHORIZATION, TOKEN_TYPE + apiKey);
    }

    @Override
    public URI getURI() {
        final String decodedURI = URLDecoder
            .decode(getRequest().getURI().toString(), StandardCharsets.UTF_8);
        return UriComponentsBuilder
            .fromUriString(decodedURI)
            .queryParam(LANGUAGE_QUERY_PARAM, LocaleContextHolder.getLocale())
            .build()
            .toUri();
    }
}
```
[^1]: Medium, Carlos Caballero [Online]. hhttps://betterprogramming.pub/https-medium-com-ccaballero-understanding-mvc-services-for-front-end-angular-a6196492ee74 (Stand 24.05.2023)

[^2]: Wikipedia, Stellvertreter (Entwurfsmuster) [Online]. https://de.wikipedia.org/wiki/Stellvertreter_(Entwurfsmuster)#:~:text=Der%20Proxy%2C%20auch%20Stellvertreter%20genannt,Objektes%20auf%20ein%20vorgelagertes%20Stellvertreterobjekt (Stand 24.05.2023)

[^3]: Spring Framework Dokumentation, ClientHttpRequestExecution [Online]. https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/client/ClientHttpRequestExecution.html (Stand 24.05.2023)



