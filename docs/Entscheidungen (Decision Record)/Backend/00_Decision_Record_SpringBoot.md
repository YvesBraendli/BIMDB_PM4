# Spring Boot for backend


|           |          |
|-----------|----------|
| status    | accepted |
| date      | 13.3.2023 |
| deciders  | Cristina Dias Pinto, Yves Brändli |
| consulted | Cristina Dias Pinto, Gabriel Ben Abou |
| informed  | Gabriel Ben Abou, Nils Oriet |

## Context and Problem Statement
The basic situation was to decide, which framework we want to use for the whole project. It had to be one specific framework, which everyone already
and could work with.

<!-- This is an optional element. Feel free to remove. -->
## Decision Drivers

* At least one expert in the framework has to be in the backend team
* Framework has to be easy to implement
* Framework must be fast in the development process
* Everyone should be able to work with the framework

## Considered Options

* Spring Boot
* Play Framework
* Vert.x
* Quarkus

## Decision Outcome

Chosen option: "Spring Boot", because Cristina Dias  Pinto, who is  assigned in the backend team, had this framework already in the ZHAW-module
'Advanced Software Engineering' and knew it already very well. It was the only suitable option, which meets the criterion , that there has to be a
framework-expert in the backend team.

<!-- This is an optional element. Feel free to remove. -->
## Pros and Cons of the Options

### Spring Boot

{description}

* Good, because is a straightforward framework and is easy to implement
* Good, because there is a framework-expert in the backend team
* Neutral, because every feature can be made using this framework
* Bad, because steep learning curve, when someone is new in the spring ecosystem

### Play Framework

{description}

* Good, because it has a built-in support for asynchronous programming
* Good, because it supports multiple languages
* Bad, because it's designed for high-performance web applications and this is an overhead for this project
* Bad, because steeper learning curve than Spring Boot
* Bad, because limited Java EE support

### Vert.x

{description}

* Good, because it supports multiple languages
* Good, because it's lightweight and modular architecture
* Bad, because steeper learning curve than Spring Boot
* Bad, because limited Java EE support

### Quarkus

{description}

* Good, because it has a fast startup time
* Good, because it has a low memory footprint
* Good, because it supports multiple languages
* Bad, because some features require a paid subscription to access them