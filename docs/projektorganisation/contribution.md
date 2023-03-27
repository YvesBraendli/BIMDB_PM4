# 🦺 Contribution

## Branching Strategie

Das Projekt orientiert sich stark an `Gitflow` (siehe [Gitflow von Atlassian](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow)). Es wird sowohl ein `development`- als auch `main`-Branch geführt. Der Stand von `development` spiegelt anschliessend die _Staging-Umgebung_ wider. Das Deployment in _Production_ erfolgt, sobald der _Release-Kandidat_ freigegeben (merge in `main`) und ein Release erzeugt wurde.

Feature-Branches werden ebenfalls vom `Gitflow` übernommen, allerdings verzichten wir — aus Gründen des Overheads bei der Projektgrösse — auf `Release`-Branches.

### Gitflow  

![Gitflow](../assets/img/gitlflow.svg)

## ✨ Genereller Ablauf für eine Featureimplementation

 1. Soll ein neues Feature implementiert werden, so wird zunächst ein Issue erfasst und priorisiert.
 2. Sobald aktiv an dem Issue gearbeitet wird, muss das Workflow-Label von `workflow::backlog` auf `workflow::in-progress` wechseln.
 3. Es kann anschliessend auf der gleichen Issue-Seite im GitLab UI ein `Draft Merge Request` erzeugt werden. Dies hat zur Folge, dass ein neuer Branch mit `{issue-id}-{issue-title}` ab dem jetzigen Stand des `development`-Branchs erzeugt wird.
 4. Ist die Arbeit erledigt (Issue Scope, MR-Template, Pipeline erfolgreich ✅), so kann das Feature in `development` gemerged werden.
 5. Nachdem die Pipeline in `development` getriggert wurde, sollte der aktuelle Stand über [Environments](https://gitlab.com/gabrielbenabou/bimdb/-/environments) ersichtlich sein.

## Erweiterung der Dokumentation

Die Dokumentation wird in Markdown-Files unter _[/docs](/docs)_ hinterlegt. Die Markdown-Files werden anschliessend in einem Pipeline-Job (in `development` und `main`) automatisiert als statische Seite mittels MKDocs gerendert.

### Lokales Rendern (zu Testzwecken)

Damit ein Wiki-Build vorgängig getestet werden kann, wird eine DevContainer Konfiguration bereitgestellt. Dieser kann auch für das verfassen genutzt werden.

#### Voraussetzungen

Es wird WSL samt Docker vorausgesetzt. [Hier](https://code.visualstudio.com/docs/devcontainers/containers#_installation) findet man weitere Informationen zur Installation.

#### DevContainer starten

 Das generelle vorgehen ist nachfolgend beschrieben:  

1. Öffnen des (Windows/WSL) Terminals
2. Pull des Repositories
3. In das Repository wechseln per `cd`
4. In den Docs folder wechseln `cd docs/`
5. VSCode folgendermassen öffnen `code .`
6. Die Aufforderung `Open in DevContainer` bestätigen
7. Auf das `Source Control` Icon klicken und `Open Parent Repository` klicken

Es wird nun der DevContainer einmalig gebuilded und gestartet. Die nötigen Erweiterungen für Markdown-Files sind installiert.

#### Wiki starten

Per CTRL + Shift + P kann (die ersten paar Zeichen reichen) `Tasks: Run Task` eingegeben werden. Danach kann der Task `Run Wiki` ausgeführt werden.
