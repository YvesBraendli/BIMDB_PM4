# Addition of SonarCloud for Code Coverage

|           |          |
|-----------|----------|
| status    | accepted |
| date      | 24.04.2023 |
| deciders  | Gabriel Ben Abou |
| consulted | - |
| informed  | Yves Brändli, Dominik Aschmann, Cristina Dias Pinto, Pascal Diethelm, Nils Oriet |

## Context and Problem Statement

We have decided to add SonarCloud as an additional code coverage tool to our project, alongside the existing code coverage provided by GitLab. The goal is to enhance our code analysis capabilities and have a comprehensive overview of code quality and coverage. However, this decision requires running both frontend and backend jobs in the pipeline for every merge request, even if only one of them has been modified.

## Decision Outcome

SonarCloud will be integrated into our project as an additional code coverage tool, alongside GitLab's code coverage. This decision is made to enhance our code analysis capabilities and have a comprehensive overview of code quality and coverage. As a consequence, every merge request, regardless of whether it modifies frontend or backend code, will trigger both frontend and backend jobs in the pipeline for the SonarCloud agent to perform the analysis.

### Consequences

- Good, because SonarCloud provides in-depth code analysis and reporting capabilities, complementing the existing code coverage provided by GitLab.
- Good, because having a comprehensive overview of code quality and coverage helps identify potential issues and improve the overall codebase.
- Bad, because running both frontend and backend jobs for every merge request may increase the pipeline execution time and resource usage.
- Bad, because it requires additional coordination and effort to ensure the frontend and backend jobs are properly configured and executed in the pipeline.
