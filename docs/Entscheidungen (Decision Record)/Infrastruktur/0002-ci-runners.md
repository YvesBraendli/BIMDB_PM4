# Installation of GitLab CI Runners on Virtual Machines

|           |          |
|-----------|----------|
| status    | accepted |
| date      | 23.03.2023 |
| deciders  | Gabriel Ben Abou, Dominik Aschmann |
| consulted | - |
| informed  | Yves Brändli, Cristina Dias Pinto, Pascal Diethelm, Nils Oriet, Tristan Kindle, David Schaefle |

## Context and Problem Statement

We need to decide on the setup of GitLab CI runners for our project. The options considered are installing the runners on the given virtual machines, utilizing the available runner pool, or integrating GitLab Kubernetes runners. However, due to the unavailability of service account permissions, the integration of GitLab Kubernetes runners is not currently feasible. Therefore, the chosen solution is to install GitLab CI runners on the given virtual machines.

## Decision Drivers

- Unavailability of service account permissions for integrating GitLab Kubernetes runners
- Unavailability of access to the Kubernetes cluster
- Cost reduction
- Need for full control over caching and other mechanisms

## Considered Options

- Installing GitLab CI runners on the given virtual machines
- Utilizing the available runner pool
- Integrating GitLab Kubernetes runners (Not currently feasible due to service account permissions)

## Decision Outcome

Chosen option: Installing GitLab CI runners on the given virtual machines, because it allows us to work without access to the Kubernetes cluster, reduces cost (especially the cost for pipeline minutes), and provides full control over caching and other mechanisms.

### Consequences

- Good, because it enables us to utilize our existing virtual machines effectively.
- Good, because it eliminates the need for access to the Kubernetes cluster, allowing us to proceed with CI/CD without any dependencies.
- Good, because it reduces costs by avoiding the high cost associated with the pipeline minutes of shared runners.
- Good, because it provides us with full control over caching and other mechanisms, allowing for optimized CI/CD performance.


## Pros and Cons of the Options

### Installing GitLab CI Runners on Virtual Machines

- Good, because it allows us to utilize our existing virtual machines efficiently.
- Good, because it eliminates the need for access to the Kubernetes cluster, enabling us to proceed with CI/CD independently.
- Good, because it provides full control over caching and other mechanisms.
- Bad, because it may require additional setup and maintenance effort for managing the runners on the virtual machines.


### Utilizing the Available Runner Pool

- Good, because it utilizes shared resources from the available runner pool, reducing the need for dedicated virtual machines.
- Neutral, because it may provide easy scalability and flexibility in resource allocation.
- Bad, because it incurs a high cost for pipeline minutes, which may not be cost-effective for our project.
- Bad, because it introduces potential limitations or dependencies on the availability and configuration of the runner pool.


### Integrating GitLab Kubernetes Runners (Not currently feasible due to service account permissions)

- Good, because it would allow seamless integration with the Kubernetes cluster.
- Bad, because the unavailability of service account permissions prevents us from implementing this option.

