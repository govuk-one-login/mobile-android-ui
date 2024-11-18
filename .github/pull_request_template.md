[Tutorial for writing good descriptions]: https://cbea.ms/git-commit/

[//]: # (Be mindful that the PR title also needs to follow conventional commit standards)

# _`[WIP]` DCMAW-0000: title of change_

- Imperative, present tense description of a change that matches the
  [Tutorial for writing good descriptions].

[//]: # (e.g. "- Create 'androidLibrary' Gradle module.")

## Evidence of the change

[//]: # (Screenshots / uploaded videos go here)

## Checklist

### Before creating the pull request

- [ ] Commit messages that conform to conventional commit messages.
- [ ] Ran the app locally ensuring it builds.
- [ ] Tests pass locally.
- [ ] Pull request has a clear title with a short description about the feature or update.
- [ ] Created a `draft` pull request if it's not ready for review.

### Before the CODEOWNERS review the pull request

- [ ] Complete all Acceptance Criteria within Jira ticket.
- [ ] Self-review code.
- [ ] Successfully run changes on a testing device.
- [ ] Complete automated Testing:
  * [ ] Unit Tests.
  * [ ] Integration Tests.
  * [ ] Instrumentation / Emulator Tests.
- [ ] Review [Accessibility considerations].
- [ ] Handle PR comments.

### Before merging the pull request

- [ ] [Sonar cloud report] passes inspections for your PR.
- [ ] Resolve all comments.

[Sonar cloud report]: https://sonarcloud.io/project/overview?id=di-mobile-android-ui
[Accessibility considerations]: https://developer.android.com/guide/topics/ui/accessibility/testing
