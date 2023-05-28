# Database for Deployment: Postgres

|           |          |
|-----------|----------|
| status    | accepted |
| date      | 20.04.2023 |
| deciders  | Gabriel Ben Abou, Cristina Dias Pinto |
| consulted | - |
| informed  | Yves Brändli, Dominik Aschmann, Pascal Diethelm, Nils Oriet, Tristan Kindle, David Schaefle |

## Context and Problem Statement

We need to decide on the database to use for the deployment of our Spring backend. The options considered are H2 (in-memory database) and Postgres. The chosen solution is Postgres for deployment.

## Decision Drivers

- Production-ready and scalable database solution.
- Compatibility with our deployment environment and infrastructure.

## Considered Options

- H2 (in-memory database)
- Postgres

## Decision Outcome

Chosen option: Postgres. We have decided to use Postgres as the database for the deployment of our Spring backend due to its production-ready nature and scalability, as well as its compatibility with our deployment environment and infrastructure.

### Consequences

- Good, because Postgres is a robust and reliable database system suitable for production environments.
- Good, because it offers advanced features and optimizations for handling large datasets and concurrent transactions.
- Bad, because it may require additional setup and configuration compared to H2.
- Bad, because it may introduce additional complexity for database management and maintenance.

## Pros and Cons of the Options

### H2 (in-memory database)

- Good, because it is lightweight and convenient for development and testing purposes.
- Good, because it does not require a separate database setup or configuration.
- Bad, because it may not provide the same level of performance and scalability as Postgres.
- Bad, because it may not offer advanced features and optimizations needed for production deployments.

### Postgres

- Good, because it is a production-ready and scalable database solution.
- Good, because it offers advanced features and optimizations for handling large datasets and concurrent transactions.
- Neutral, because it may require additional setup and configuration compared to H2.
- Bad, because it may introduce additional complexity for database management and maintenance.

