# Getting Started

### Rename your gradle project after cloning template project

Go to the unit test class 'io.github.astrapi69.InitialTemplateTest' and set your description for your repository in the
disabled unit test method testRenameToConcreteProject. Then enable the unit test method testRenameToConcreteProject by
comment or delete the annotation @Disabled and run it after. This will rename your gradle project. Now you can delete
all unrelated files and test dependencies that you do not need.

If you are use intellij ide than you can add the new gradle run configurations to git and delete the run configurations
for the unit test and the unit test class itself.

### Setting Secrets

The workflows in `.github/workflows` read these repository secrets:

- `CENTRAL_PORTAL_TOKEN_USERNAME` and `CENTRAL_PORTAL_TOKEN_PASSWORD`, a Central Portal user token: `publish.yml` hands
  them to `./gradlew publish` and `make central-upload` as `CENTRAL_USERNAME` and `CENTRAL_PASSWORD`
- `GPG_PRIVATE_KEY` and `GPG_PASSPHRASE`, the ascii-armored signing key and its passphrase: `publish.yml` hands them to
  `./gradlew publish`, which signs a release with them
- `CODECOV_TOKEN`, the Codecov token that `gradle.yml` hands to the coverage upload; a public repository does not need it

A local build reads the Central Portal user token from the environment variables `CENTRAL_USERNAME` and
`CENTRAL_PASSWORD`, or else from your local file `~/.gradle/gradle.properties`, which is not part of the repository:

```
centralUsername=ReplaceThisWithYourTokenUsername
centralPassword=ReplaceThisWithYourTokenPassword
```

Without `GPG_PRIVATE_KEY` and `GPG_PASSPHRASE`, a release is signed by the `gpg` command with the key that
`signing.gnupg.keyName` names, or by Gradle's signing properties when `signing.keyId` is set
(`gradle/publishing.gradle`).

For setting secrets for your repository you can consider the following sections:

- [Creating encrypted secrets for a repository](https://docs.github.com/en/actions/security-guides/encrypted-secrets#creating-encrypted-secrets-for-a-repository)
- [Creating encrypted secrets for an organization](https://docs.github.com/en/actions/security-guides/encrypted-secrets#creating-encrypted-secrets-for-an-organization)

Note that for organizations you only need to set the secrets once.

### Reference Documentation

For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)

### Additional Links

These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)
