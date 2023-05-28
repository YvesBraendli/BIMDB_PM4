# Use of GitLab CI for Project

|           |          |
|-----------|----------|
| status    | accepted |
| date      | 13.03.2023 |
| deciders  | Gabriel Ben Abou, Dominik Aschmann |
| consulted | - |
| informed  | Yves Brändli, Cristina Dias Pinto, Pascal Diethelm, Nils Oriet, Tristan Kindle, David Schaefle |


## Context and Problem Statement

We need to decide on the continuous integration (CI) tool for our project. The options considered are GitLab CI and Jenkins with ArgoCD. The chosen solution is GitLab CI.

## Decision Drivers

- Integration with existing GitLab repository
- Ability to integrate with runners and Kubernetes agents

## Considered Options

- GitLab CI
- Jenkins with ArgoCD

## Decision Outcome

Chosen option: "GitLab CI", because it aligns with our existing GitLab repository and provides seamless integration with runners and Kubernetes agents.

### Consequences

- Good, because it leverages our existing GitLab infrastructure and minimizes additional tooling.
- Good, because it allows us to easily integrate and utilize runners and Kubernetes agents for efficient CI/CD pipelines.
- Bad, because it may require some team members to acquire new skills or familiarize themselves with GitLab CI.

## Pros and Cons of the Options

### GitLab CI

- Good, because it integrates well with our existing GitLab repository.
- Good, because it provides native support for runners and Kubernetes agents.
- Neutral, because it may require some learning curve for team members who are new to GitLab CI.
- Bad, because it may have limitations in terms of plugin ecosystem compared to Jenkins.

### Jenkins with ArgoCD

- Good, because it is a widely-used CI tool with extensive plugin ecosystem.
- Good, because it offers integration with ArgoCD for advanced deployment workflows.
- Neutral, because it would require additional setup and maintenance effort.
- Bad, because it introduces an additional tool and potential complexity to our CI/CD infrastructure.
