# Continuous Deployment Options

|           |          |
|-----------|----------|
| status    | accepted |
| date      | 23.03.2023 |
| deciders  | Gabriel Ben Abou, Dominik Aschmann |
| consulted | Instructors (Patrick Baumgartner) |
| informed  | Yves Brändli, Cristina Dias Pinto, Pascal Diethelm, Nils Oriet, Tristan Kindle, David Schaefle |

## Context and Problem Statement

We need to decide on the continuous deployment (CD) approach for our project. The options considered are:
1. Using ArgoCD with GitLab-CI.
2. Using the kubeconf of the given Rancher Cluster as a CI-Variable.
3. Using GitLab Kubernetes Agent (GKA) in conjunction with the instructors' help to configure the integration.
   - If the GKA configuration is not feasible, the alternative option is to create our own MicroK8s Cluster and deploy the agent there.

## Decision Drivers

- Need for a reliable and scalable CD solution.
- Integration with GitLab-CI.
- Limitations and constraints related to permissions and token expiration.

## Considered Options

1. ArgoCD with GitLab-CI.
2. Using the kubeconf of the given Rancher Cluster as a CI-Variable.
3. GitLab Kubernetes Agent (GKA) with instructor assistance.
   - Alternative: Creating our own MicroK8s Cluster and deploying the agent.

## Decision Outcome

Chosen option: GitLab Kubernetes Agent (GKA) with instructor assistance, as it provides a reliable CD solution and addresses the limitations and constraints related to permissions and token expiration. The instructors' help was crucial in configuring the integration.

### Consequences

- Good, because GitLab Kubernetes Agent (GKA) allows us to have a reliable and scalable CD solution.
- Good, because the instructors' assistance helped us overcome the limitations and constraints related to permissions and token expiration.
- Bad, because the configuration of GitLab Kubernetes Agent (GKA) was complicated due to insufficient permissions to create a delegated service account.


## Pros and Cons of the Options

### ArgoCD with GitLab-CI

- Good, because it provides a declarative and GitOps-based approach to continuous deployment.
- Good, because it integrates well with GitLab-CI.
- Bad, because it may require additional setup and maintenance effort for managing the ArgoCD infrastructure.


### Using the kubeconf of the given Rancher Cluster as a CI-Variable

- Good, because it allows direct access to the Rancher Cluster for CD.
- Bad, because it is bound to a user and not a service account, and the token might expire unexpectedly.


### GitLab Kubernetes Agent (GKA) with instructor assistance

- Good, because it provides a reliable CD solution.
- Good, because the instructors' assistance helps overcome limitations and constraints.
- Bad, because the configuration process can be complicated, especially due to insufficient permissions to create a delegated service account.


### Alternative: Creating our own MicroK8s Cluster and deploying the agent

- Good, because it provides a controlled environment for CD.
- Good, because it allows us to have full control over the configuration and setup.
- Bad, because it may require additional infrastructure and maintenance effort.


## More Information

We used the two given namespaces (Staging and PROD) in the given Rancher Cluster Infrastructure to deploy to automatically.
