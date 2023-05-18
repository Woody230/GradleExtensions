package com.bselzer.gradle.settings

import org.gradle.api.initialization.ConfigurableIncludedBuild

fun ConfigurableIncludedBuild.substitute(substitution: Pair<String, String>) {
    dependencySubstitution { substitutions ->
        with(substitutions) {
            val module = module(substitution.first)
            val project = project(substitution.second)
            substitute(module).using(project)
        }
    }
}

fun ConfigurableIncludedBuild.substitute(vararg substitutions: Pair<String, String>) {
    substitutions.forEach { substitution -> substitute(substitution) }
}