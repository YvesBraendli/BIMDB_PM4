# Frontend framework


|           |          |
|-----------|----------|
| status    | accepted |
| date      | 13.3.2023 |
| deciders  | Pascal Diethelm, Nils Oriet |
| consulted | - |
| informed  | Yves Brändli, Dominik Aschmann, Cristina Dias Pinto,  Gabriel Ben Abou, Tristan Kindle, David Schaefle |

## Context and Problem Statement
The context of this decision is the choice of a front-end JavaScript framework for developing our web application. We
have evaluated three well-known options: Angular, React, and Vue.js.

## Decision Drivers

* At least one expert in the framework has to be in the frontend team
* Well-known framework with basic functionalities
* Built on TypeScript

## Considered Options

* Angular
* React
* Vue.js

## Decision Outcome

Chosen option: "Angular", because we have two experienced angular developers in the frontend team.

## Pros and Cons of the Options

### Angular

* Pros:
  * Two experienced developers in the frontend team
  * Provides a consistent and robust framework for building web applications
  * Framework with built-in support for components, services, routing, and state management
  * Built on TypeScript, providing additional type safety and better code organization
  * Large and active community with lots of resources and support available online
* Cons:
  * Steeper learning curve than React or Vue.js

### React

* Pros:
  * Lightweight and fast
  * Simple and flexible component-based architecture
  * Wide adoption and large community, with many resources available online
  * Can be used for both web and mobile app development with React Native
* Cons:
  * Requires more external libraries and tools to provide a complete set of features and functionality
  * Lacks built-in support for state management, requiring the use of external libraries like Redux
  * Steeper learning curve for developers new to JSX

### Vue.js
* Pros:
  * Simple and intuitive framework with a less steep learning curve
  * Flexible and adaptable, with a small API surface area and easy integration with existing projects
    * Offers built-in support for state management and routing
* Cons:
  * Smaller community and fewer resources compared to Angular and React
  * Less scalable and robust than Angular or React
  * Limited built-in functionality, requiring the use of external libraries for many features
