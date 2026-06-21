import com.codingfeline.buildkonfig.compiler.FieldSpec

plugins {
    id(libs.plugins.woody230.gradle.convention.jvm.get().pluginId)
    id(libs.plugins.buildkonfig.get().pluginId)
}

mavenPublishing {
    pom {
        description.set("Build configuration for internally used plugins.")
    }
}

buildkonfig {
    packageName = "io.github.woody230.gradle.internal.build.configuration"
    exposeObjectWithName = "BuildConfiguration"

    defaultConfigs {
        buildConfigField(FieldSpec.Type.STRING, "libs_woody230_gradle_internal_libs", libs.woody230.gradle.internal.libs.get().toString(), nullable = false, const = true)
    }
}