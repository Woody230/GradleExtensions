![](https://img.shields.io/badge/targets-JVM-informational)
![](https://img.shields.io/github/v/release/Woody230/GradleExtensions)
[![](https://img.shields.io/maven-central/v/io.github.woody230.gradle/multiplatform)](https://search.maven.org/search?q=io.github.woody230.gradle)
![](https://img.shields.io/github/license/Woody230/GradleExtensions)

# Gradle Extensions

Kotlin extensions for Gradle. Includes internal plugins for publishing, Android, and Kotlin Multiplatform.

# Gradle

Published to [Maven Central](https://search.maven.org/search?q=io.github.woody230.gradle).

```kotlin
repositories {
    mavenCentral()
}
```

```kotlin
implementation("io.github.woody230.gradle:$Module:$Version")
```

# Modules

Modules exist within multiple builds that are included within the root project as a composite:

* [common](#common)
* [internal-common-plugins](#internal-common-plugins)
* [internal-plugins](#internal-plugins)

Note that for all plugins, afterEvaluate is NOT used.
Consequently, all extension classes for the plugins must be configured before applying the plugins.

## common

Non-internal extensions for Gradle.

### android

Android gradle plugin extensions:

* androidExtension getter to find the `CommonExtension` as the extension model for the application or library plugin.

### multiplatform

Kotlin Multiplatform gradle plugin extensions:

* KotlinSourceSet extensions for getting named source sets:
    * commonMain
    * commonTest
    * jvmMain
    * jvmTest
    * androidMain
    * androidUnitTest
    * androidInstrumentedTest

```kotlin
kotlin {
    sourceSets {
        androidMain {
            // ...
        }
    }
}
```

* `MultiplatformDependencies` class and extension for conveniently setting the dependencies of the previously listed
  source sets.

```kotlin
multiplatformDependencies {
    androidMain {
        api("...")
    }
}
```

Normally this would look like:

```kotlin
kotlin {
    sourceSets {
        val androidMain by getting {
            dependencies {
                api("...")
            }
        }
    }
}
```

### properties

General Gradle extensions related to properties.

* `localProperties` extension to get the local.properties file from the root directory
    * The logic is the same as the Android gradle plugin.

## internal-plugins

Plugins intended to be used by my personal projects only.

### android-desugar-plugin

```kotlin
plugins {
    id("io.github.woody230.gradle.android.desugar")
}
```

* Configures the `LibraryExtension` or `BaseAppModuleExtension` depending on if the library or application plugin is
  applied:
    * Compile options:
        * isCoreLibraryDesugaringEnabled as true
* Adds the `com.android.tools:desugar_jdk_libs` dependency with the **[version]** to the `coreLibraryDesugaring`
  configuration.

#### AndroidDesugarExtension

* **[version]**: The version of the `com.android.tools:desugar_jdk_libs` dependency to apply. Optional with a default
  value of `2.0.3`.

### android-library-plugin

```kotlin
plugins {
    id("io.github.woody230.gradle.android.library")
}
```

* Applies the Android library gradle plugin.
    * Namespace: **[namespaceId]**, **[subNamespaceId]**, and **[artifactId]** separated by a period (`.`)
    * Compile sdk: **[compileSdk]**
    * Default config:
        * Min sdk: **[minSdk]**
        * Test instrumentation runner: **[testInstrumentationRunner]**
    * Compile options:
        * Source compatibility: **[sourceCompatibility]**
        * Target compatibility: **[targetCompatibility]**
    * Test options:
        * Unit tests:
            * Android resources:
                * Is included android resources: true

#### AndroidLibraryExtension

Required

* **[subNamespaceId]**: The specific category, denoting the type of modules.

Optional

* **[namespaceId]**: The base id of the namespace. Optional with a default value of `com.bselzer`.
* **[artifactId]**: The name of the module. Optional with a default value of the name of the project.
* **[compileSdk]**: The API level to compile against. Optional with a default value of 33.
* **[minSdk]**: The minimum API level required. Optional with a default value of 21.
* **[sourceCompatibility]**: The language level of the java source code. Optional with a default value of 11.
* **[targetCompatibility]**: The version of the generated Java bytecode. Optional with a default value of 11.
* **[testInstrumentationRunner]**: The fully qualified class name of the test instrumentation runner. Optional with a
  default value of `androidx.test.runner.AndroidJUnitRunner`.

### multiplatform-compose-plugin

```kotlin
plugins {
    id("io.github.woody230.gradle.multiplatform.compose")
}
```

* Applies the Compose Multiplatform gradle plugin.
* Configures the Android gradle plugin:
    * Build features:
        * Compose: true
    * Compose options:
        * Kotlin compiler extension version: **[compilerVersion]**

#### MultiplatformComposeExtension

* **[compilerVersion]**: The version of the compose compiler. Optional with a default value of `1.4.2`.
    * [Multiplatform Compatibility](https://github.com/JetBrains/compose-multiplatform/blob/master/VERSIONING.md#kotlin-compatibility)
        * [Mapping](https://github.com/JetBrains/compose-multiplatform/blob/master/gradle-plugins/compose/src/main/kotlin/org/jetbrains/compose/ComposeCompilerCompatibility.kt)
    * [Jetpack Compatibility](https://developer.android.com/jetpack/androidx/releases/compose-kotlin#pre-release_kotlin_compatibility)

### multiplatform-plugin

```kotlin
plugins {
    id("io.github.woody230.gradle.multiplatform")
}
```

* Applies the Kotlin Multiplatform gradle plugin.
    * Configures the targets:
        * JVM
        * Android with library variants `release` and `debug`
    * Sets the jvm toolchain with the **[jdkVersion]**

#### MultiplatformExtension

* **[jdkVersion]**: The version of the JDK used for the Java toolchain. Optional with a default value of 11.

### multiplatform-publish-plugin

```kotlin
plugins {
    id("io.github.woody230.gradle.multiplatform.publish")
}
```

* See [maven-publish-plugin](#maven-publish-plugin) for base logic.
    * Uses a KotlinMultiplatform platform with a Dokka generated javadoc.

#### MultiplatformPublishExtension

Implements the **[MavenPublishExtension](#mavenpublishextension)** without any additional properties.

### multiplatform-resource-plugin

```kotlin
plugins {
    id("io.github.woody230.gradle.multiplatform.resource")
}
```

* Applies the Moko Resources gradle plugin.
* Adds explicit task dependencies in order to be compatible with Gradle 8.

### multiplatform-test-plugin

```kotlin
plugins {
    id("io.github.woody230.gradle.multiplatform.test")
    id("io.github.woody230.gradle.multiplatform.compose.test")
}
```

Adds common test dependencies to the common, Android, and JVM test source sets.

The compose specific plugin adds the Compose Multiplatform test dependencies.

## internal-publish-plugins

Plugins intended to be used by my personal projects only.

These plugins are included within this build because they are also used by the other modules in the other builds within
this project.

### jvm-publish-plugin

```kotlin
plugins {
    id("io.github.woody230.gradle.jvm.publish")
}
```

* See [maven-publish-plugin](#maven-publish-plugin) for base logic.
    * Uses a KotlinJvm platform with a Dokka generated javadoc.

#### JvmPublishExtension

Implements the **[MavenPublishExtension](#mavenpublishextension)** without any additional properties.

### maven-publish-plugin

```kotlin
plugins {
    id("io.github.woody230.gradle.maven.publish")
}
```

* Sets up gradle properties from local properties, if they exist.

| Gradle Property            | Local Property    |
|----------------------------|-------------------|
| mavenCentralUsername       | sonatype.username |
| mavenCentralPassword       | sonatype.password |
| signingInMemoryKeyId       | signing.keyId     |
| signingInMemoryKey         | signing.key       |
| signingInMemoryKeyPassword | signing.password  |

* Applies [vanniktech's base publish plugin](https://github.com/vanniktech/gradle-maven-publish-plugin).
* Coordinates:
    * Group id: **[groupId]** and **[subGroupId]** separated by a period (`.`)
    * Artifact id: **[artifactId]**
    * Version: **[version]**
* Pom:
    * Name: **[subGroupId]** and **[artifactId]** separated by a dash (`-`)
    * Description: **[description]**
    * Licenses: based on **[licensing]**
        * `NONE`: No license is added.
        * `APACHE_2_0`: The Apache 2.0 license is added.
    * Developers:
        * Configure according to the **[developers]** delegate.
        * Always adds a developer with id `Woody230`.
    * Url: **[repository]**
    * Scm:
        * Url: **[repository]**
* Sets up Maven Central publishing through the publishAllPublicationsToMavenCentralRepository task.
    * The host is set to https://s01.oss.sonatype.org.
    * Automatic releasing is disabled.
    * The `mavenCentralUsername` and `mavenCentralPassword` gradle properties are applied.
* Signing is set up if the in-memory signing gradle properties are configured:
    * `signingInMemoryKeyId`
    * `signingInMemoryKey`
    * `signingInMemoryKeyPassword`

#### MavenPublishExtension

Required

* **[subGroupId]**: The specific category, denoting the type of modules. For example, this repository uses `gradle`.
* **[version]**: The semantic version of the artifact. A snapshot is denoted by a version ending in `-SNAPSNOT`.
* **[repository]**: The URL for the source control repository. For example, this repository
  uses `https://github.com/Woody230/GradleExtensions`.
* **[description]**: A brief description of what the module provides.

Optional

* **[groupId]**: The base group id of the coordinates. Optional with a default value of `io.github.woody230`.
* **[artifactId]**: The name of the module. Optional with a default value of the name of the project.
* **[licensing]**: The type of licensing to apply. Must be either `NONE` or `APACHE_2_0`. Optional with a default value
  of `NONE`.
* **[developers]**: Configures the additional developers. Optional with a default function that does nothing.

### plugin-publish-plugin

```kotlin
plugins {
    id("io.github.woody230.gradle.plugin.publish")
}
```

* Sets up gradle properties from local properties, if they exist.

| Gradle Property       | Local Property        |
|-----------------------|-----------------------|
| gradle.publish.key    | gradle.publish.key    |
| gradle.publish.secret | gradle.publish.secret |

* Project's group: **[groupId]** and **[subGroupId]** separated by a period (`.`)
* Project's version: **[version]**

* See [maven-publish-plugin](#maven-publish-plugin) for base logic.
    * Uses a GradlePublishPlugin platform.

* Applies the Java gradle plugin and Gradle plugin publish gradle plugin.
    * Website: **[repository]**
    * Vcs url: **[repository]** with `.git` appended
    * Plugins: for each of the **[plugins]** with at least 1 required
        * Id: **[groupId]**, **[subGroupId]**, and **[plugins.name]** separated by a period (`.`).
        * Display name: **[plugins.displayName]**
        * Description: **[plugins.description]**
        * Implementation class: **[plugins.className]**
        * Tags: **[tags]** and **[plugins.tags]** combined

#### PluginPublishExtension

Implements the **[MavenPublishExtension](#mavenpublishextension)**.

Required

* **[plugins]**: The plugins to publish. At least one plugin is required.
    * **[name]**: The name of the plugin, to be combined with the **[groupId]** and **[subGroupId]** for the id of the
      plugin.
    * **[displayName]**: The friendly name of the plugin.
    * **[description]**: A brief description of what the plugin provides. Optional with a default value of the *
      *[PluginPublishPluginExtension.description]**.
    * **[tags]**: The individual tags for this particular plugin, to be combined with the common tags. Optional with a
      default value of an empty list.
    * **[className]**: The fully qualified class name of the plugin.

Optional

* **[tags]**: The common tags to apply to all plugins. Optional with a default value of an empty list.