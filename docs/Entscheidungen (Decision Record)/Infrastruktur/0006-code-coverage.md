# Code Coverage with GitLab

|           |          |
|-----------|----------|
| status    | accepted |
| date      | 12.04.2023 |
| deciders  | Gabriel Ben Abou |
| consulted | - |
| informed  | Yves Brändli, Dominik Aschmann, Cristina Dias Pinto, Pascal Diethelm, Nils Oriet |

## Context and Problem Statement

We need to decide on the tool or platform to use for code coverage in our project. The options considered are GitLab and SonarCloud. The chosen solution is GitLab for code coverage.

## Decision Drivers

- Integration with existing GitLab platform.
- Ease of setup and configuration.
- Cost considerations.

## Considered Options

- GitLab
- SonarCloud

## Decision Outcome

Chosen option: GitLab. We have decided to use GitLab for code coverage in our project due to its integration with our existing GitLab platform, ease of setup and configuration, and cost considerations.

### Consequences

- Good, because GitLab provides seamless integration with our existing GitLab repository and pipeline, making it easy to set up and configure code coverage.
- Good, because it reduces the need for additional external tools or services.
- Bad, because it may have limitations compared to SonarCloud in terms of advanced code analysis and reporting features.

## Pros and Cons of the Options

### GitLab

- Good, because it integrates well with our existing GitLab platform and pipeline.
- Good, because it provides built-in code coverage features and reports.
- Neutral, because it may have limitations in terms of advanced code analysis compared to SonarCloud.
- Bad, because it may not provide as detailed and comprehensive code coverage reports as SonarCloud.

### SonarCloud

- Good, because it offers advanced code analysis and reporting features.
- Good, because it provides detailed code coverage reports and insights.
- Bad, because it may require additional setup and configuration.
- Bad, because it may introduce additional costs for licensing or usage.
