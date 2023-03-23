# 🦺 Branching Strategie

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

