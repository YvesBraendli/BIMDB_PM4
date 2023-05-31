# Infrastruktur Architektur

BIMDB wurde basierend auf der ZHAW-Hochschulinfrastruktur entwickelt. Diese besteht aus einer Mischung von virtuellen Maschinen und Container-Instanzen, die innerhalb eines Kubernetes Cluster-Ökosystems zusammenspielen. Zur logischen Trennung der virtuellen Infrastruktur wurden für die unterschiedlichen Umgebungen – "Staging" und "PROD" – separate Namensbereiche eingerichtet.

Für die Durchführung von Continuous Integration-Jobs setzen wir auf Runner, die auf ebenfalls virtuellen Maschinen laufen. Die Aufgaben des Continuous Deployments werden mithilfe eines GitLab Kubernetes Agents (GKAS) direkt auf dem Cluster ausgeführt.

Um eine hohe Verfügbarkeit und Resilienz der Anwendung zu gewährleisten, ist ein Load-Balancer des Rancher Clusters implementiert. Dieser ermöglicht es, das Frontend, den IaM und das Backend durch Ingress-Richtlinien freizugeben, wodurch diese Komponenten von extern zugänglich werden. Die durch den Load Balancer realisierte Lastverteilung auf Serviceniveau erlaubt die Erzeugung mehrerer Container-Instanzen (Replikation). Sollte eine Instanz ausfallen, kann der Betrieb nahtlos auf eine andere umgeleitet werden.

Die vorhandene Infrastruktur ermöglicht sowohl horizontale als auch vertikale Skalierung. Durch die automatisierte Überwachung der Systemmetriken können zusätzliche Ressourcen hinzugefügt oder entfernt werden. Dies garantiert, dass den Benutzeranforderungen stets ausreichende Kapazitäten gegenüberstehen. Diese Funktion wird ausserhalb des ursprünglichen Projektumfangs implementiert (Beta Release).

Da die Infrastruktur der Hochschule genutzt wird, erfolgt deren Wartung durch die Hochschule selbst. Der Support für die Anwendung kann von einem internen Team übernommen werden, das sich um die fortlaufende Überwachung, Fehlerbehebung und Leistungsoptimierung kümmert, um eine verbesserte Benutzererfahrung zu gewährleisten.
