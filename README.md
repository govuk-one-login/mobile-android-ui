# Android GOV UK UI Library

Libraries to provide some useful and generic ui components, pages and themes to be used across GOV.UK Android applications.

Components and Pages work of the principle of creating an example of the contained `...Parameters` class to inject all necessary options for an example of the given Composable

### Theme

This library contains the `GdsTheme`, collecting the colour schemes, fonts/typography and some useful dimensions

### Components

This library contains 'building blocks', smaller, resuable components 

### Pages

This library contains page patterns to help with the creation of similar styles of page. They use components from the `components` library to build larger patterns


## Installation

To use these libraries please refer to the Github Packages to find the latest versions for each

### Gradle

Include `maven("https://maven.pkg.github.com/govuk-one-login/mobile-android-ui")` in the 
```
repositories {
...
}
```
Then simply reference the library in 
```
dependencies {
  implementation("uk.gov.android:theme:_")
  ...
}
```

## v4.0.0 Breaking Changes
- Configured the GdsButton to work with `AnnotatedString` in the `components/m3/buttons` to allow the IconButton to persist within the button and increase size/ move to another line accordingly when increasing font/ display size on device.
The `GdsButton` and any pages using it (e.g. `ErrorPage`) need to be the same version in order to be used in the same codebase.
