# Qualitätsattribute
In diesem Abschnitt geht es um die klare Definition von Qualitätsmerkmalen, welche für die Entwicklung und Pflege von BIMDB stets berücksichtigt werden sollten. Die einzelnen Merkmale werden spezifisch, messbar, erreichbar, relevant und zeitlich begrenzt (SMART) formuliert und sollen insbesondere für Diskussionen innerhalb des BIMDB-Teams herangezogen werden, da die aufgelisteten Attribute und Anforderungen erforderlich für die erfolgreiche Umsetzung BIMDB sind.

## Performance (Latenz, Durchsatz)
Schnelle Ladezeiten und eine hohe Reaktionsgeschwindigkeit auf Benutzeranfragen sind Pflicht. Der Durchsatz muss ausreichend sein, um eine hohe Anzahl von gleichzeitigen Benutzeranfragen bewältigen und gleichzeitig eine konsistente Streamingerfahrung bieten zu können.

## Skalierbarkeit (Daten-, Umsatz-Volumen)
Das Kernkriterium für die Architektur von BIMDB liegt auf die einfache Skalierbarkeit (vertikal als auch horizontal [^1]) des Daten- und Umsatzvolumens, um flexibel auf Veränderungen reagieren zu können.

## Verfügbarkeit (erforderliche Uptime, erlaubte Downtime, Wartungsfenster)
BIMDB sollte eine hohe Verfügbarkeit aufweisen, mit einer annähernd konstanten Uptime. Die Downtime sollte so gering wie möglich gehalten werden, d.h. für Updates oder Wartungen genutzt werden.

## Sicherheit (Authentifizierung, Autorisierung, Vertraulichkeit)
Die App muss robuste Sicherheitsmechanismen bieten und insbesondere die geltenden Datenschutzregulierungen umsetzen.

## Erweiterbarkeit
Der Quellcode von BIMDB soll modular aufgebaut sein, um zukünftige Erweiterungen und Verbesserungen effizient zu ermöglichen.

## Flexibilität
Die App muss flexibel genug sein, um sich an verschiedene Benutzeranforderungen und Nutzungsszenarien anzupassen. D.h. auch mögliche zukünftige Anforderungen und Funktionalitäten sollten so früh wie möglich mit bedacht werden.

## Nachverfolgbarkeit (Auditing)
Um die Fehlersuche und -behebung zu vereinfachen, werden relevante System- und Nutzeraktivitäten in einer Logdatei vermerkt, um Fehler innerhalb der App nachvollziehen und analysieren zu können.

## Monitoring & Management
Um eine professionelle Instandhaltung der Software sowie Hardware zu gewährleisten, werden branchenübliche Tools zur Überwachung und Visualisierung verwendet. Damit sollen Probleme frühzeitig erkennbar gemacht werden.

## Zuverlässigkeit
BIMDB sollte stabil und fehlertolerant sein, um ein kontinuierliches und störungsfreies Benutzererlebnis zu gewährleisten.

## Disaster Recovery-Ziele
Wo notwendig (z.B. bei den Benutzerdaten und deren Bewertungen), sollten Disaster Recovery Mechanismen definiert und umgesetzt werden. Damit im Falle eines Systemausfalls oder dergleichen ein Datenverlust verhindert werden kann.

## Interoperabilität
BIMDB muss eine konsistente und nachhaltige Anbindung an Social-Media-Plattformen und die IMDB gewährleisten.

## Rechtliche und regulatorische Anforderungen
BIMDB muss alle geltenden rechtlichen und regulatorischen Anforderungen, wie Datenschutzgesetze und Urheberrechtsgesetze, erfüllen.

## Internationalisierung
Mehrsprachige Benutzeroberflächen sollen unterstützt werden, damit der Einsatz von BIMDB in verschiedenen Regionen und Ländern verwendet werden kann.

## Zugänglichkeit
Die App sollte gemäss den modernen Standards barrierefrei gestaltet sein, um Benutzer mit verschiedensten Einschränkungen (Sehschwäche, Hörprobleme, etc.) nicht auszugrenzen.

## Benutzerfreundlichkeit
Und schliesslich soll die Benutzerfreundlichkeit stets im Auge behalten werden. Durch ständige Umfragen und Tests, soll für BIMDB eine intuitive Handhabung gewährleisten werden.

[^1]: "Unter vertikaler Skalierung versteht man ein Steigern der Leistung durch das Hinzufügen von Ressourcen zu einem Knoten/Rechner des Systems [...] Horizontale Skalierung bedeutet die Steigerung der Leistung eines Systems durch das Hinzufügen zusätzlicher Rechner/Knoten." Wikipedia, Skalierbarkeit [Online]. URL: https://de.wikipedia.org/wiki/Skalierbarkeit#Horizontale_Skalierung_(scale_out) (Stand 06.05.2023)