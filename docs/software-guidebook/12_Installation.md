#Installation

Das Kapitel Installation beschreibt den Installationsprozess.

Für die Automatisierung dem Softwarebereitstellungsprozess wird GitLab-CI in Kombination mit Helm Charts verwendet. 

Helm Charts sind ein integraler Bestandteil der Deployment-Strategie und bieten uns zahlreiche Vorteile. Einerseits ist Helm ein leistungsstarkes Werkzeug, das es uns ermöglicht, Anwendungskonfigurationen zu modularisieren und die Bereitstellung zu vereinfachen. Mit Helm Charts kann die Anwendung Konfigurationsdateien mithilfe von vordefinierten Templates schnell und konsistent bereitstellen. Dies ermöglicht eine standardisierte und reproduzierbare Bereitstellung unserer Software. Ein weiterer Vorteil von Helm besteht in der Möglichkeit, Upgrades (Rolling-Upgrades) und Rollbacks der Anwendung einfach durchzuführen. Neue Versionen der Anwendung können schnell bereitgestellt und sind abwärtskompatibel, falls Probleme auftreten. Dies ermöglicht es, schnellere Release-Zyklen zu erreichen und die Qualität der Anwendung durch automatisierte Tests sicherzustellen.

Durch GitLab-CI kann der gesamte Prozess effizient automatisiert werden. Mit GitLab-CI können Builds, das Testing und Container-Images abgewickelt werden. Das gewährleistet die Bewirtschaftung der gesamten Betriebskette für kontinuierliche Bereitstellung und Integration. Die Integration von Helm Charts in den Deployment-Prozess erleichtert die Bereitstellung und Verwaltung der Anwendung in den verschiedenen Umgebungen.

Die Installationsanleitung orientiert sich an das automatisierte Deployment und ist im  [README-File](https://link-url-here.org) vorzufinden.

| Software-Container     | Im App-Bundle auf der Infrastruktur bereitgestellt |
| ---------------------- | -------------------------------------------------- |
| Angular-Frontend       | Nginx Webserver                                    |
| Spring-Backend         | Tomcat Anwendungsserver                            |
| PostgreSQL-Datenbank   | PostgreSQL DBMS                                    |
| Keycloak (IaM-Container)| Authentifizierungsserver                           |

