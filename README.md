# Android GOV UK UI Library

Libraries to provide some useful and generic ui components, pages and themes to be used across GOV.UK Android applications.

### Theme

This library contains the `GdsTheme`, collecting the colour schemes, fonts/typography and some useful dimensions.

### Components 

This library contains 'building blocks', smaller, reusable components - the only new components to be created should have full specs provided by the UCD team.
All new components should be created in `componentsv2` module - `components` module will be deprecated and removed once all dependencies from other repositories have been removed. 

### Patterns

This library contains page patterns to help with the creation of similar styles of page. They use components from the `componentsv2` library to build larger patterns.
All new components should be created in `patterns` module - `pages` module will be deprecated and removed once all dependencies from other repositories have been removed.

## Installation

To use these libraries please refer to the GitHub Packages to find the latest versions for each.

### Gradle

Include `maven("https://maven.pkg.github.com/govuk-one-login/mobile-android-ui")` in:
```
repositories {
...
}
```
Then simply reference the library in: 
```
dependencies {
  implementation("uk.gov.android:theme:_")
  ...
}
```

## v4.0.0 Breaking Changes
- in `components` - Configured the GdsButton to work with `AnnotatedString` in the `components/m3/buttons` to allow the IconButton to persist within the button and increase size/ move to another line accordingly when increasing font/ display size on device.
The `GdsButton` and any pages using it (e.g. `ErrorPage`) need to be the same version in order to be used in the same codebase.
