package com.bselzer.gradle.internal.composite.task.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.initialization.Settings
import org.gradle.api.tasks.TaskProvider

abstract class CompositeTaskPlugin: Plugin<Settings> {
    override fun apply(settings: Settings) = with(settings) {
        // NOTE assumption is that the root project does not actually have any projects itself and purely relies on included builds.
        gradle.projectsLoaded {
            if (gradle.parent == null) {
                gradle.rootProject.registerRootTasks().configureComposite()
            } else {
                gradle.rootProject.registerLeafTasks().configureComposite()
            }
        }
    }

    protected abstract fun Project.registerRootTasks(): Collection<TaskProvider<Task>>
    protected abstract fun Project.registerLeafTasks(): Collection<TaskProvider<Task>>

    private fun Collection<TaskProvider<Task>>.configureComposite() = forEach { taskProvider ->
        taskProvider.configure { task ->
            task.group = "composite"
        }
    }

    /**
     * Gets the included builds tasks at the given [path] and adds them as dependencies to [this] task.
     */
    protected fun Task.dependOnIncludedBuilds(path: String) {
        val tasks = project.gradle.includedBuilds.map { build -> build.task(path) }
        dependsOn(tasks)
    }

    /**
     * Recursively gets the project's tasks with the given [name] and adds them as dependencies to [this] task.
     */
    protected fun Task.dependOnRecursively(name: String) {
        val tasks = project.getTasksByName(name, true)
        dependsOn(tasks)
    }
}