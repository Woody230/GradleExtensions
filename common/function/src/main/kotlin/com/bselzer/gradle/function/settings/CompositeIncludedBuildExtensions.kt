package com.bselzer.gradle.function.settings

import org.gradle.api.initialization.ConfigurableIncludedBuild

/**
 * Substitutes modules using projects mapped according to the given [substitutions].
 */
fun ConfigurableIncludedBuild.substituteModulesUsingProjects(
    vararg substitutions: Pair<String, String>
): Unit = dependencySubstitution { dependencySubstitutions ->
    substitutions.forEach { (moduleNotation, projectNotation) ->
        val module = dependencySubstitutions.module(moduleNotation)
        val project = dependencySubstitutions.project(projectNotation)
        dependencySubstitutions.substitute(module).using(project)
    }
}