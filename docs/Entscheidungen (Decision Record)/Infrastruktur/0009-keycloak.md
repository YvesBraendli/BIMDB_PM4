# Choice of Authorization Server

|           |          |
|-----------|----------|
| status    | accepted |
| date      | 05.05.2023 |
| deciders  | Gabriel Ben Abou |
| consulted | - |
| informed  | Yves Brändli, Cristina Dias Pinto, Nils Oriet, David Schaefle |

## Context and Problem Statement

We need to select an authorization server solution for our application, considering the requirements of both the frontend and backend. The options considered are Keycloak and utilizing Spring Security in the backend as the authorization server.

## Decision Drivers

- Separation of concerns and modularity
- Compatibility with frontend and backend frameworks

## Considered Options

- Keycloak as the dedicated authorization server
- Utilizing Spring Security in the backend as the authorization server

## Decision Outcome

Chosen option: Keycloak as the dedicated authorization server, because it provides a robust and feature-rich solution that can be easily integrated with both the frontend and backend. We will use the OpenID Connect OAuth 2.0 flow, where the frontend acts as the Client and the backend as the Resource server.

### Consequences

- Good, because Keycloak offers comprehensive authentication and authorization features that meet our application's requirements.
- Good, because using a dedicated authorization server promotes separation of concerns and modularity in our application architecture.
- Bad, because Keycloak may introduce additional complexity in terms of setup and maintenance compared to using Spring Security in the backend.

## Pros and Cons of the Options

### Keycloak as the dedicated authorization server

- Good, because it provides a wide range of authentication and authorization features out-of-the-box.
- Good, because it offers seamless integration with various frontend and backend frameworks.
- Neutral, because it may require some additional setup and configuration effort.
- Bad, because it introduces an external dependency on Keycloak, which may require ongoing maintenance.

### Utilizing Spring Security in the backend as the authorization server

- Good, because it leverages the existing Spring Security capabilities in our backend.
- Neutral, because it eliminates the need for an external authorization server.
- Bad, because it may require more custom development to implement certain authentication and authorization features.
- Bad, because it may limit flexibility in terms of integrating with different frontend frameworks.

## More Information
We use Open-ID Connect (OAuth 2.0), where the frontend acts as client and the backend as resource server. 