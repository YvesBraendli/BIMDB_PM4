# Deployment and Branching Strategy for CI/CD

|           |          |
|-----------|----------|
| status    | accepted |
| date      | 13.03.2023 |
| deciders  | Gabriel Ben Abou |
| consulted | - |
| informed  | Yves Brändli, Dominik Aschmann, Cristina Dias Pinto, Pascal Diethelm, Nils Oriet, Tristan Kindle, David Schaefle |

## Context and Problem Statement

We need to decide on the deployment and branching strategy for our CI/CD process. The goal is to define how different branches represent different environments (staging and production) and ensure smooth and automated deployment from staging to production.

## Decision Drivers

- Clear separation and representation of staging and production environments.
- Streamlined and automated deployment process.
- Efficient and reliable release management.

## Considered Options

- Using the development branch as the representation of the staging environment and auto-deploying the main branch to the production environment after release and tag.

## Decision Outcome

Chosen option: Using the development branch as the representation of the staging environment and auto-deploying the main branch to the production environment after release and tag. This strategy ensures a clear separation between staging and production, and allows for automated deployment to production after successful release and tagging.

### Consequences

- Good, because using the development branch as the staging environment allows for easy testing and validation before promoting changes to production.
- Good, because auto-deploying the main branch to production after release and tag ensures a streamlined and automated deployment process.
- Bad, because any changes directly pushed to the main branch will not be automatically deployed to production and will require a release and tagging process.

## Pros and Cons of the Option

### Using the Development Branch as the Staging Environment and Auto-deploying the Main Branch to Production

- Good, because it provides a clear separation between staging and production environments.
- Good, because it allows for easy testing and validation in the staging environment using the development branch.
- Good, because it enables automated deployment to production after successful release and tagging of the main branch.
- Bad, because changes directly pushed to the main branch will not be automatically deployed to production and will require a release and tagging process.
