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

Non-internal modules:
```kotlin
implementation("io.github.woody230.gradle:$Module:$Version")
```

Internal modules:
```kotlin
implementation("io.github.woody230.gradle.internal:$Module:$Version")
```

# Modules

Modules exist within multiple builds that are included within the root project as a composite:

* [common](#common)
* [internal-plugins](#internal-plugins)
* [internal-publish-plugins](#internal-publish-plugins)

## common

Non-internal extensions for Gradle.

### android

Android gradle plugin extensions:

* androidExtension getter to find the `CommonExtension` as the extension model for the application or library plugin.

### function

General Gradle extensions.

* `localProperties` extension to get the local.properties file from the root directory
* `compositeLocalProperties` extension to get the local.properties file from the root directory or the parent directory recursively
* `fileOperations` extension to access the FileOperations from a settings plugin

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

* `MultiplatformSourceSets` class and extension for conveniently configuring the previously listed source sets.

```kotlin
multiplatformSourceSets {
    // Required configuring
    androidMain {
        // ...
    }

    // Optional configuring
    maybeAndroidMain {
        // ...
    }
}
```

* `MultiplatformDependencies` class and extension for conveniently configuring the dependencies of the previously listed source sets.

```kotlin
multiplatformDependencies {
    // Required configuring
    androidMain {
        api("...")
    }

    // Optional configuring
    maybeAndroidMain {
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

## internal-project-plugins

Plugins targeting a project intended to be used by my personal projects only.

### aboutlibraries-plugin
```kotlin
plugins {
    id("io.github.woody230.gradle.internal.aboutlibraries")
}
```

* Applies the `AboutLibrariesPlugin`.
* Configures the `AboutLibrariesExtension`:
  * If the Kotlin Multiplatform plugin has been applied, then `registerAndroidTasks` is set to false.
* If the Kotlin Multiplatform plugin has been applied:
  * The core AboutLibraries dependency is added to the main source sets.
* If the Moko Resources plugin has been applied, then the `aboutLibrariesResource` task is created:
  * The `aboutLibraries.json` file produced by the `exportLibraryDefinitions` task is moved to the MR assets of the source set the Moko Resources plugin is using.

### android-desugar-plugin

```kotlin
plugins {
    id("io.github.woody230.gradle.internal.android-desugar")
}
```

* Configures the `LibraryExtension` or `BaseAppModuleExtension` depending on if the library or application plugin is applied:
    * Compile options:
        * isCoreLibraryDesugaringEnabled as true
* Adds the `com.android.tools:desugar_jdk_libs` dependency with the **[version]** to the `coreLibraryDesugaring` configuration.

#### AndroidDesugarExtension

* **[version]**: The version of the `com.android.tools:desugar_jdk_libs` dependency to apply. Optional with a default value of `2.0.3`.

### android-plugin

The base plugin for configuring common options for the [library](#android-library-plugin) and [application](#android-application-plugin) plugins.

Configures the `CommonExtension`:

* Namespace: **[namespace.group]**, **[namespace.category]**, and **[namespace.module]** separated by a period (`.`)
* Compile sdk: **[compileSdk]**
* Default config:
    * Min sdk: **[minSdk]**
    * Test instrumentation runner: **[testInstrumentationRunner]**
* Build features:
  * Build config: **[buildConfig]**
* Compile options:
    * Source compatibility: **[sourceCompatibility]**
    * Target compatibility: **[targetCompatibility]**

#### AndroidExtension

Required

* **[namespace]**: The id of the namespace.
    * **[group]**: The base id of the namespace. Optional with a default value of `com.bselzer`.
    * **[category]**: The specific category, denoting the type of modules. For example, this repository uses `gradle`.
    * **[module]**: The name of the module. Optional with a default value of the name of the project.

Optional

* **[compileSdk]**: The API level to compile against. Optional with a default value of 33.
* **[minSdk]**: The minimum API level required. Optional with a default value of 21.
* **[sourceCompatibility]**: The language level of the java source code. Optional with a default value of 11.
* **[targetCompatibility]**: The version of the generated Java bytecode. Optional with a default value of 11.
* **[testInstrumentationRunner]**: The fully qualified class name of the test instrumentation runner. Optional with a default value of `androidx.test.runner.AndroidJUnitRunner`.
* **[buildConfig]**: Whether the build config is enabled. Optional with a default value of false.

#### android-application-plugin

```kotlin
plugins {
    id("io.github.woody230.gradle.internal.android-application")
}
```

* Sets up gradle properties from local properties, if they exist.

| Gradle Property | Local Property |
|-----------------|----------------|
| storeFile       | storeFile      |
| storePassword   | storePassword  |
| keyPassword     | keyPassword    |
| keyAlias        | keyAlias       |

* See [android-plugin](#android-plugin) for base logic.
* Applies the Android application gradle plugin.

Configures the `BaseAppModuleExtension`:

* Default config:
    * Target sdk: **[targetSdk]**
    * Application id: **[applicationId]**
    * Version name: **[versionName]**
    * Version code: **[versionCode]**
* Build types:
    * Release:
        * isMinifyEnabled: true
        * isShrinkResources: true
        * proguardFiles:
            * Add the **[defaultProguardFile]**.
                * `OPTIMIZED` uses `proguard-android-optimize.txt`
                * `UNOPTIMIZED` uses `proguard-android.txt`
            * Add the files in the rootDir/proguard directory that end with the `.pro` extension.
        * ndk:
            * debugSymbolLevel: `FULL`
        * signingConfig:
            * storeFile: file with path of `storeFile` gradle property
            * storePassword: `storePassword` gradle property
            * keyPassword: `keyPassword` gradle property
            * keyAlias: `keyAlias` gradle property

##### AndroidApplicationExtension

Implements the [AndroidExtension](#androidextension).

Required

* **[versionName]**: The semantic version.
* **[versionCode]**: The incremental version.

Optional

* **[applicationId]**: The id of the application. Optional with a default value of the **[namespace.group]**, **[namespace.category]**, **[namespace.module]** separated by a period (`.`)
* **[targetSdk]**: The target API level. Optional with a default value of 33.
* **[defaultProguardFile]**: The type of default proguard file. Must be either `UNOPTIMIZED` or `OPTIMIZED`. Optional with a default value of `OPTIMIZED`.
* **[buildConfig]**: Whether the build config is enabled. Optional with a default value of true.

#### android-library-plugin

```kotlin
plugins {
    id("io.github.woody230.gradle.internal.android-library")
}
```

* See [android-plugin](#android-plugin) for base logic.
* Applies the Android library gradle plugin.

##### AndroidLibraryExtension

Implements the [AndroidExtension](#androidextension) without any additional properties.

### buildkonfig-plugin
```kotlin
plugins {
    id("io.github.woody230.gradle.internal.buildkonfig")
}
```

* Applies the BuildKonfig plugin.

Extensions for adding fields.
```kotlin
buildkonfig {
    defaultConfigs {
        boolean("DEBUG", true)
        int("VERSION_CODE", 1)
        string("VERSION_NAME", "1.0.0")
    }
}
```

### multiplatform-compose-plugin

```kotlin
plugins {
    id("io.github.woody230.gradle.internal.multiplatform-compose")
}
```

* Applies the Compose Multiplatform gradle plugin.
* Applies the Compose Compiler gradle plugin.
* Configures the Android gradle plugin:
    * Build features:
        * Compose: true

### multiplatform-compose-test-plugin

```kotlin
plugins {
    id("io.github.woody230.gradle.internal.multiplatform-compose-test")
}
```

Adds compose test dependencies to the common, Android, and JVM test source sets.

### multiplatform-plugin

```kotlin
plugins {
    id("io.github.woody230.gradle.internal.multiplatform")
}
```

* Applies the Kotlin Multiplatform gradle plugin.
    * Sets the jvm toolchain with the **[jdkVersion]**

#### MultiplatformExtension

* **[jdkVersion]**: The version of the JDK used for the Java toolchain. Optional with a default value of 11.

#### multiplatform-android-target

```kotlin
plugins {
    id("io.github.woody230.gradle.internal.multiplatform.android.target")
}
```

* Applies the Android target.
  * Adds the library variants `release` and `debug`.

#### multiplatform-jvm-target

```kotlin
plugins {
    id("io.github.woody230.gradle.internal.multiplatform.jvm.target")
}
```

* Applies the JVM target.

### multiplatform-publish-plugin

```kotlin
plugins {
    id("io.github.woody230.gradle.internal.multiplatform-publish")
}
```

* See [maven-publish-plugin](#maven-publish-plugin) for base logic.
    * Uses a KotlinMultiplatform platform with a Dokka generated javadoc.

#### MultiplatformPublishExtension

Implements the [MavenPublishExtension](#mavenpublishextension) without any additional properties.

### multiplatform-test-plugin

```kotlin
plugins {
    id("io.github.woody230.gradle.internal.multiplatform-test")
}
```

Adds common test dependencies to the common, Android, and JVM test source sets.

## internal-publish-plugins

Plugins intended to be used by my personal projects only.

These plugins are included within this build because they are also used by the other modules in the other builds within this project.

### jvm-publish-plugin

```kotlin
plugins {
    id("io.github.woody230.gradle.internal.jvm-publish")
}
```

* See [maven-publish-plugin](#maven-publish-plugin) for base logic.
    * Uses a KotlinJvm platform with a Dokka generated javadoc.

#### JvmPublishExtension

Implements the [MavenPublishExtension](#mavenpublishextension) without any additional properties.

### maven-publish-plugin

Base plugin for publishing to Maven Central.

* Sets up gradle properties from local properties, if they exist.

| Gradle Property            | Local Property    |
|----------------------------|-------------------|
| mavenCentralUsername       | sonatype.username |
| mavenCentralPassword       | sonatype.password |
| signingInMemoryKeyId       | signing.keyId     |
| signingInMemoryKey         | signing.key       |
| signingInMemoryKeyPassword | signing.password  |
| RELEASE_SIGNING_ENABLED    | signing.enabled   |

* Applies [vanniktech's base publish plugin](https://github.com/vanniktech/gradle-maven-publish-plugin).
* Coordinates:
    * Group id: **[coordinates.group]** and **[coordinates.category]** separated by a period (`.`)
    * Artifact id: **[coordinates.module]**
    * Version: **[version]**
* Pom:
    * Name: **[coordinates.category]** and **[coordinates.module]** separated by a space with dashes (`-`) replaced with
      spaces and words capitalized
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
* Signing is set up if the `RELEASE_SIGNING_ENABLED` gradle property is `true`.

#### MavenPublishExtension

Required

* **[coordinates]**: The id of the coordinates.
    * **[group]**: The base id of the coordinates. Optional with a default value of `io.github.woody230`.
    * **[category]**: The specific category, denoting the type of modules. For example, this repository uses `gradle`.
    * **[module]**: The name of the module. Optional with a default value of the name of the project.
* **[version]**: The semantic version of the artifact. A snapshot is denoted by a version ending in `-SNAPSNOT`.
* **[repository]**: The URL for the source control repository. For example, this repository uses `https://github.com/Woody230/GradleExtensions`.
* **[description]**: A brief description of what the module provides.

Optional

* **[licensing]**: The type of licensing to apply. Must be either `NONE` or `APACHE_2_0`. Optional with a default value of `NONE`.
* **[developers]**: Configures the additional developers. Optional with a default function that does nothing.

### plugin-publish-plugin

```kotlin
plugins {
    id("io.github.woody230.gradle.internal.plugin-publish")
}
```

* Sets up gradle properties from local properties, if they exist.

| Gradle Property       | Local Property        |
|-----------------------|-----------------------|
| gradle.publish.key    | gradle.publish.key    |
| gradle.publish.secret | gradle.publish.secret |

* Project's group: **[coordinates.group]** and **[coordinates.category]** separated by a period (`.`)
* Project's version: **[version]**

* See [maven-publish-plugin](#maven-publish-plugin) for base logic.
    * Uses a GradlePublishPlugin platform.

* Applies the Java gradle plugin and Gradle plugin publish gradle plugin.
    * Website: **[repository]**
    * Vcs url: **[repository]** with `.git` appended
    * Plugins: for each of the **[plugins]** with at least 1 required
        * Id: **[coordinates.group]**, **[coordinates.category]**, and **[plugins.name]** separated by a period (`.`).
        * Display name: **[plugins.displayName]**
        * Description: **[plugins.description]**
        * Implementation class: **[plugins.className]**
        * Tags: **[tags]** and **[plugins.tags]** combined

#### PluginPublishExtension

Implements the **[MavenPublishExtension](#mavenpublishextension)**.

Required

* **[plugins]**: The plugins to publish. At least one plugin is required.
    * **[name]**: The name of the plugin, to be combined with the **[coordinates.group]** and **[coordinates.category]** for the id of the plugin.
        * If there is only a single plugin, then it is optional with a default value of the project name stripped of the `-plugin` suffix and with dashes (`-`) replaced by periods (`.`).
    * **[displayName]**: The friendly name of the plugin.
        * If there is only a single plugin, then it is optional with a default value of the project name with dashes (`-`) replaced by spaces and words capitalized.
    * **[description]**: A brief description of what the plugin provides.
        * If there is only a single plugin, then it is optional with a default value of the **[PluginPublishPluginExtension.description]**.
    * **[tags]**: The individual tags for this particular plugin, to be combined with the common tags. Optional with a default value of an empty list.
    * **[className]**: The fully qualified class name of the plugin.

Optional

* **[tags]**: The common tags to apply to all plugins. Optional with a default value of an empty list.

## internal-settings-plugins

Plugins targeting settings intended to be used by my personal projects only.

### bundled-plugin
```kotlin
plugins {
    id("io.github.woody230.gradle.internal.bundled")
}
```

* Add the following repositories to plugin management and all projects:
  * gradlePluginPortal
  * google
  * mavenCentral
  * mavenLocal
* Enables type safe project accessors.
* Applies the foojay toolchain plugin.
* Applies the following internal plugins:
  * [composite-build-plugin](#composite-build-plugin)
  * [composite-property-plugin](#composite-property-plugin)
  * [composite-publish-plugin](#composite-publish-plugin)
  * [composite-test-plugin](#composite-test-plugin)
  * [version-catalog-plugin](#version-catalog-plugin)

### composite-build-plugin
```kotlin
plugins {
    id("io.github.woody230.gradle.internal.composite-build")
}
```

* Adds the `assembleIncludedBuilds` task to a root build which assembles the projects in all included builds.
* Adds the `assembleRecursively` task to a child build which assembles the projects within the build.
* Adds the `buildIncludedBuilds` task to a root build which assembles and tests the projects in all included builds.
* Adds the `buildRecursively` task to a child build which assembles and tests the projects within the build.
* Adds the `cleanIncludedBuilds` task to a root build which cleans the projects in all included builds.
* Adds the `cleanRecursively` task to a child build which cleans the projects within the build.

### composite-property-plugin

```kotlin
plugins {
    id("io.github.woody230.gradle.internal.composite-property")
}
```

* If a `composite.properties` file exists at the root of the composite, then those properties are added to each project.
* If a `local.properties` file exists at the root of the composite, then the file is copied to the root of each included build.

### composite-publish-plugin

```kotlin
plugins {
    id("io.github.woody230.gradle.internal.composite-publish")
}
```

* Adds the `publishIncludedBuildsToMavenCentral` task to a root build which publishes the projects in all included builds to the Maven Central repository.
* Adds the `publishIncludedBuildsToMavenLocal` task to a root build which publishes the projects in all included builds to the Maven local repository.
* Adds the `publishRecursivelyToMavenCentral` task to a child build which publishes the projects within the build to the Maven Central repository.
* Adds the `publishRecursivelyToMavenLocal` task to a child build which publishes the projects within the build to the Maven local repository.

### composite-test-plugin

```kotlin
plugins {
    id("io.github.woody230.gradle.internal.composite-test")
}
```

* Adds the `testIncludedBuilds` task to a root build which runs all tests for the projects in all included builds.
* Adds the `testRecursively` task to a child build which runs all tests for the projects within the build.

### version-catalog-plugin

```kotlin
plugins {
    id("io.github.woody230.gradle.internal.version-catalog")
}
```

* Creates a version catalog named `libs` from a `libs.versions.toml` file that should exist under the `gradle` folder of the root of the composite. 