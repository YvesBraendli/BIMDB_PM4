# Operation & Support

Das Kapitel "Operation und Support" behandelt den fortlaufenden Betrieb der Software und den Support bei aufkommenden Problemen.

Zur Erfassung von Fehlern und Statusinformationen sind in der Anwendung zentrale Logging-Mechanismen integriert. Jede Software-Komponente liefert ihre Log-Dateien separat. Damit alle relevanten Ereignisse und Fehler konsistent dokumentiert werden, kommen sowohl im Backend als auch im Frontend geeignete Logging-Technologien zum Einsatz. Die erstellten Log-Dateien werden in der Produktionsumgebung an einen ELK-Stack (Elasticsearch, Logstash, Kibana) übermittelt, welcher das zentrale Sammeln, Verarbeiten und Analysieren erlaubt. Dieser Ansatz ist derzeit nur als Konzept vorhanden und muss noch realisiert werden.

Die Administration der Anwendung umfasst bestimmte Teilaufgaben, die (noch) nicht automatisiert wurden. Dazu zählen unter anderem die Überwachung der Systemressourcen und das Anpassen von Konfigurationseinstellungen. Konfigurationsänderungen werden über CI/CD mit Helm in die verschiedenen Umgebungen eingespielt, ohne dass das System neu gestartet werden muss. In bestimmten Fällen kann jedoch ein Neustart der durch Helm bereitgestellten Applikation notwendig sein, um zu gewährleisten, dass die Konfigurationsänderungen vollständig wirksam sind. Dies könnte der Fall sein, wenn Änderungen Auswirkungen auf die Kernkomponenten der Anwendung haben oder wenn bestimmte Dienste oder Prozesse neu gestartet werden müssen. In solchen Situationen werden entsprechende Massnahmen getroffen, um sicherzustellen, dass der Neustart der Applikation ordnungsgemäss erfolgt.




