# Usage of Container Registry

|           |          |
|-----------|----------|
| status    | accepted |
| date      | 25.03.2023 |
| deciders  | Gabriel Ben Abou |
| consulted | - |
| informed  | Cristina Dias Pinto, Nils Oriet |

## Context and Problem Statement

We need to decide on the container registry to use for storing our Docker images. The options considered are DockerHub and GitLab Container Registry.

## Decision Drivers

- Seamless integration with the rest of the GitLab ecosystem.
- Security and access control of container images.
- Ease of use and administration.

## Considered Options

- DockerHub
- GitLab Container Registry

## Decision Outcome

Chosen option: GitLab Container Registry. We have decided to use GitLab Container Registry due to its seamless integration with the rest of the GitLab ecosystem, enhanced security and access control features, and ease of use and administration.

### Consequences

- Good, because using GitLab Container Registry allows us to leverage the existing GitLab infrastructure and maintain a centralized repository for our Docker images.
- Good, because it provides enhanced security features such as access control, vulnerability scanning, and image signing.
- Good, because it integrates well with GitLab CI/CD pipelines, making the deployment of Docker images straightforward and efficient.

## Pros and Cons of the Options

### DockerHub

- Good, because it is a widely used and popular container registry.
- Good, because it has a large user community and extensive image repository.
- Bad, because it may require additional configuration and integration efforts with the rest of the GitLab ecosystem.
- Bad, because it may have limitations in terms of security and access control compared to GitLab Container Registry.

### GitLab Container Registry

- Good, because it seamlessly integrates with the rest of the GitLab ecosystem, providing a unified experience.
- Good, because it offers enhanced security features such as access control, vulnerability scanning, and image signing.
- Good, because it simplifies the deployment of Docker images with GitLab CI/CD pipelines.
- Bad, because it may require some learning curve for team members who are new to GitLab Container Registry.
