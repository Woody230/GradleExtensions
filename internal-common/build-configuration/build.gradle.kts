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
        buildConfigField(FieldSpec.Type.STRING, "libs_aboutlibraries_core", libs.aboutlibraries.core.get().toString(), nullable = false, const = true)
        buildConfigField(FieldSpec.Type.STRING, "libs_android_desugar", libs.android.desugar.get().toString(), nullable = false, const = true)
        buildConfigField(FieldSpec.Type.STRING, "libs_android_desugar_module", "${libs.android.desugar.get().module.group}:${libs.android.desugar.get().module.name}", nullable = false, const = true)
        buildConfigField(FieldSpec.Type.STRING, "libs_androidx_test_compose_ui", libs.androidx.test.compose.ui.asProvider().get().toString(), nullable = false, const = true)
        buildConfigField(FieldSpec.Type.STRING, "libs_androidx_test_compose_ui_junit", libs.androidx.test.compose.ui.junit.get().toString(), nullable = false, const = true)
        buildConfigField(FieldSpec.Type.STRING, "libs_androidx_test_compose_ui_manifest", libs.androidx.test.compose.ui.manifest.get().toString(), nullable = false, const = true)
        buildConfigField(FieldSpec.Type.STRING, "libs_androidx_test_core", libs.androidx.test.core.get().toString(), nullable = false, const = true)
        buildConfigField(FieldSpec.Type.STRING, "libs_androidx_test_junit", libs.androidx.test.junit.get().toString(), nullable = false, const = true)
        buildConfigField(FieldSpec.Type.STRING, "libs_androidx_test_runner", libs.androidx.test.runner.get().toString(), nullable = false, const = true)
        buildConfigField(FieldSpec.Type.STRING, "libs_kotlin_reflect", libs.kotlin.reflect.get().toString(), nullable = false, const = true)
        buildConfigField(FieldSpec.Type.STRING, "libs_kotlin_test", libs.kotlin.test.asProvider().get().toString(), nullable = false, const = true)
        buildConfigField(FieldSpec.Type.STRING, "libs_kotlin_test_annotations", libs.kotlin.test.annotations.get().toString(), nullable = false, const = true)
        buildConfigField(FieldSpec.Type.STRING, "libs_kotlin_test_junit", libs.kotlin.test.junit.get().toString(), nullable = false, const = true)
        buildConfigField(FieldSpec.Type.STRING, "libs_robolectric", libs.robolectric.get().toString(), nullable = false, const = true)
        buildConfigField(FieldSpec.Type.STRING, "libs_woody230_gradle_internal_libs", libs.woody230.gradle.internal.libs.get().toString(), nullable = false, const = true)
        buildConfigField(FieldSpec.Type.STRING, "plugins_aboutlibraries", libs.plugins.aboutlibraries.get().pluginId, nullable = false, const = true)
        buildConfigField(FieldSpec.Type.STRING, "plugins_android_application", libs.plugins.android.application.get().pluginId, nullable = false, const = true)
        buildConfigField(FieldSpec.Type.STRING, "plugins_android_library", libs.plugins.android.library.get().pluginId, nullable = false, const = true)
        buildConfigField(FieldSpec.Type.STRING, "plugins_buildkonfig", libs.plugins.buildkonfig.get().pluginId, nullable = false, const = true)
        buildConfigField(FieldSpec.Type.STRING, "plugins_compose", libs.plugins.compose.asProvider().get().pluginId, nullable = false, const = true)
        buildConfigField(FieldSpec.Type.STRING, "plugins_compose_compiler", libs.plugins.compose.compiler.get().pluginId, nullable = false, const = true)
        buildConfigField(FieldSpec.Type.STRING, "plugins_dokka", libs.plugins.dokka.get().pluginId, nullable = false, const = true)
        buildConfigField(FieldSpec.Type.STRING, "plugins_gradle_publish", libs.plugins.gradle.publish.get().pluginId, nullable = false, const = true)
        buildConfigField(FieldSpec.Type.STRING, "plugins_java", libs.plugins.java.get().pluginId, nullable = false, const = true)
        buildConfigField(FieldSpec.Type.STRING, "plugins_moko_resources", libs.plugins.moko.resources.get().pluginId, nullable = false, const = true)
        buildConfigField(FieldSpec.Type.STRING, "plugins_multiplatform", libs.plugins.multiplatform.get().pluginId, nullable = false, const = true)
        buildConfigField(FieldSpec.Type.STRING, "plugins_vanniktech_publish", libs.plugins.vanniktech.publish.get().pluginId, nullable = false, const = true)
        buildConfigField(FieldSpec.Type.STRING, "plugins_version_catalog_publish", libs.plugins.version.catalog.publish.get().pluginId, nullable = false, const = true)
        buildConfigField(FieldSpec.Type.INT, "versions_android_compile_sdk", libs.versions.android.compileSdk.get(), nullable = false, const = true)
        buildConfigField(FieldSpec.Type.INT, "versions_android_min_sdk", libs.versions.android.minSdk.get(), nullable = false, const = true)
        buildConfigField(FieldSpec.Type.INT, "versions_android_target_sdk", libs.versions.android.targetSdk.get(), nullable = false, const = true)
        buildConfigField(FieldSpec.Type.STRING, "versions_java_jdk", libs.versions.java.jdk.get(), nullable = false, const = true)
        buildConfigField(FieldSpec.Type.STRING, "versions_java_source_compatibility", libs.versions.java.sourceCompatibility.get(), nullable = false, const = true)
        buildConfigField(FieldSpec.Type.STRING, "versions_java_target_compatibility", libs.versions.java.targetCompatability.get(), nullable = false, const = true)
    }
}
