# Usage of Multi-Stage Build in Dockerfile

|           |          |
|-----------|----------|
| status    | accepted |
| date      | 25.03.2023 |
| deciders  | Gabriel Ben Abou |
| consulted | Pascal Diethelm, Nils Oriet |
| informed  | Dominik Aschmann |

## Context and Problem Statement

We need to decide whether to use multi-stage build in the Dockerfile for the frontend application. The purpose is to optimize the build process and create a smaller final image.

## Decision Drivers

- Build efficiency and speed.
- Reduction of image size.
- Separation of build-time dependencies and runtime environment.

## Considered Options

- Single-stage build
- Multi-stage build

## Decision Outcome

Chosen option: Multi-stage build. We have decided to use multi-stage build in the Dockerfile for the frontend application due to its benefits in build efficiency, smaller image size, and separation of build-time dependencies from the runtime environment.

### Consequences

- Good, because multi-stage build allows us to optimize the build process by separating the build-time dependencies and artifacts from the final runtime image.
- Good, because it helps reduce the size of the final image by excluding unnecessary build-time dependencies.
- Good, because it improves the overall build efficiency and speed.
- Bad, because it may introduce some complexity and additional configuration in the Dockerfile.

## Pros and Cons of the Options

### Single-stage build

- Good, because it is simple and straightforward to implement.
- Good, because it may be sufficient for smaller projects with minimal build requirements.
- Bad, because it may result in larger image sizes that include unnecessary build-time dependencies.
- Bad, because it may impact build efficiency and speed for larger projects.

### Multi-stage build

- Good, because it allows us to separate the build-time dependencies and artifacts from the final runtime image, resulting in smaller image sizes.
- Good, because it improves build efficiency and speed by only including necessary artifacts in the final image.
- Bad, because it may require additional configuration and complexity in the Dockerfile.
