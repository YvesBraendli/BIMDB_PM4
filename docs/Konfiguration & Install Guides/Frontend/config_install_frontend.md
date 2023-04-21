# Konfiguartion und Installion Guide Frontend

## Schnittstelle zu Backend

### Interface Klassen (contract.ts)

Die Datei contract.ts (frontend/bimdb/src/app/generated/contract.ts) ist generiert und beinhaltet die Interfaces für die Klassen auf dem Server, die über die REST API an das Frontend übermittelt werden. Es werden alle Klassen aus (backend/bimdb/src/main/java/com/debugdemons/bimdb/domain) aufgenommen.

Um die Datei zu generieren, muss `mvn process-classes` ausgeführt werden. Die Konfigurationen zum Generieren sind in der pom.xml Datei abgelegt.
