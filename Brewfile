# Taps (data sources)
tap "homebrew/homebrew-cask" # Access alternative versions for homebrew casks

# terminal programs
brew "awscli" unless system "aws", "--version"
brew "cocogitto" unless system "cog", "--version"
brew "gh" unless system "gh", "--version"
brew "git-lfs" unless system "git", "lfs", "version"
brew "gpg2" unless system "gpg2", "--version"
brew "gnupg" unless system "gpg", "--version"
brew "jq" unless system "jq", "--version"
brew "nvm" unless system "command", "-v", "nvm"
brew "pinentry" unless system "pinentry", "--version"
brew "pinentry-mac" unless system "pinentry-mac", "--version"
brew "shellcheck" unless system "shellcheck", "--version"
brew "sonarqube-lts" unless system "which", "sonar"
brew "vale" unless system "vale", "--version"

# casks (binary apps, such as from installers)
cask "android-studio" unless system "mdfind", "-name", "Android Studio.app"
cask "android-file-transfer" unless system "mdfind", "-name", "Android File Transfer.app"
cask "docker" unless system "mdfind", "-name", "Docker.app"
cask "git-credential-manager" unless system "git-credential-manager", "--version"
cask "homebrew/cask-versions/oracle-jdk17" unless system "/usr/libexec/java_home", "-v", "17", "--failfast"

