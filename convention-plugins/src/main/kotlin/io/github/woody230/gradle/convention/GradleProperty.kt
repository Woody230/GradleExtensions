package io.github.woody230.gradle.convention

internal object GradleProperty {
    const val MAVEN_CENTRAL_USERNAME = "mavenCentralUsername"
    const val MAVEN_CENTRAL_PASSWORD = "mavenCentralPassword"
    const val SIGNING_KEY_ID = "signingInMemoryKeyId"
    const val SIGNING_KEY = "signingInMemoryKey"
    const val SIGNING_PASSWORD = "signingInMemoryKeyPassword"
    const val SIGNING_ENABLED = "RELEASE_SIGNING_ENABLED"

    const val JAVADOC_ENABLED = "RELEASE_JAVADOC_ENABLED"
    const val SOURCES_ENABLED = "RELEASE_SOURCES_ENABLED"
}