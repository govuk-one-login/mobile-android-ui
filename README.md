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

## Updating gradle-wrapper

Gradle secure hash algorithm (SHA) pinning is in place through the `distributionSha256Sum` attribute in gradle-wrapper.properties. This means the gradle-wrapper must upgrade through the `./gradlew wrapper` command.
Example gradle-wrapper.properties
```
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionSha256Sum=2db75c40782f5e8ba1fc278a5574bab070adccb2d21ca5a6e5ed840888448046
distributionUrl=https\://services.gradle.org/distributions/gradle-8.10.2-bin.zip
networkTimeout=10000
validateDistributionUrl=true
 ```

Use the following command to update the gradle wrapper. Run the same command twice, [reason](https://sp4ghetticode.medium.com/the-elephant-in-the-room-how-to-update-gradle-in-your-android-project-correctly-09154fe3d47b).

```bash
./gradlew wrapper --gradle-version=8.10.2 --distribution-type=bin --gradle-distribution-sha256-sum=31c55713e40233a8303827ceb42ca48a47267a0ad4bab9177123121e71524c26
```

Flags:
- `gradle-version` self explanatory
- `distribution-type` set to `bin` short for binary refers to the gradle bin, often in this format `gradle-8.10.2-bin.zip`
- `gradle-distribution-sha256-sum` the SHA 256 checksum from this [page](https://gradle.org/release-checksums/), pick the binary checksum for the version used

The gradle wrapper update can include:
- gradle-wrapper.jar
- gradle-wrapper.properties
- gradlew
- gradlew.bat

You can use the following command to check the SHA 256 checksum of a file

```bash
shasum -a 256 gradle-8.10.2-bin.zip
```
