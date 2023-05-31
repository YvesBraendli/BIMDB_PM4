# Ziele und Hauptfunktionen

Dieses Kapitel beinhaltet eine Zusammenfassung der Ziele und Hauptfunktionen der BIMDB **Filmdatenbank**.

## Ziele

Mit der BIMDB **Filmdatenbank** soll eine Web-Applikation entstehen, die als Nachschlagewerk für Filme und Serien kostenlos genutzt werden kann. 

## Hauptfunktionen

Gemäss der Beschreibung des Hauptablaufs aus der Projektskizze [Kontextszenario Hauptablauf](https://gabrielbenabou.gitlab.io/bimdb/projektskizze/04_KontextszenarioHauptablauf/) ist als minimal lebensfähiges Produkt (MVP) eine Schnittstelle zur Anwendungsprogrammierung (API) mit der Filmdatenbank The Movie Database (TMDB) realisiert worden. Die daraus abgerufenen Daten zu einem Film oder einer Serie werden auf einer Web-Oberfläche (GUI) dargestellt. Wird ein Objekt ausgewählt, werden in den Details Metadaten zum Film oder zur Serie angezeigt. Zudem können über die Registerkarten "Cast" und "Crew" Informationen zur Besetzung abgerufen werden. Die Registerkarten "Recommendations" und "Similar Movies" enthalten eine Übersicht über auf dem ausgewählten Objekt basierende Empfehlungen sowie ähnliche Filme und Serien. Als Anwender-Funktionalität kann der Besucher der Seite den Film zu seinen Favoriten hinzufügen und mit der "Teilen-Funktion" mit anderen Anwendern teilen. Durch ein Login mit OAuth 2.0 ist ein sicheres Einsteigen in BIMDB gewährleistet.

### Weiterentwicklungen

Die Eigenschaften um die Nutzenmaximierung (insbesondere die hinsichtlich Geschlecht und Ethnie neutralen Bewertungen), die Anbindung der Web-Applikation an Soziale Medien sowie Erweiterungen im Streaming Bereich wurden im Rahmen der Entwicklung des minimal funktionsfähigen Produktes (MVP) nicht umgesetzt. Die Entwicklung dieser Merkmale ist für einen Beta Release geplant.

### Zielsetzungen

Durch die Entwicklung des MVP, hat die Web-Applikation den Reifegrad für die Produktion erreicht. Das Produkt wird als Alpha Release erstmalig am Markt angeboten. Im Rahmen dieses Prozesses sind die technischen und organisatorischen Zielsetzungen für den Alpha Release abgeschlossen. Die wirtschaftlichen Ziele sind gemäss der Definition im Kapitel Wirtschaftlichkeit der Projektskizze [Wirtschaftlichkeit](https://gabrielbenabou.gitlab.io/bimdb/projektskizze/08_Wirtschaftlichkeit/) zu erreichen.
