package com.bselzer.gradle.models

import org.gradle.api.provider.Property

interface ModuleId {
    /**
     * The name of the group which becomes the root of the id.
     */
    val group: Property<String>

    /**
     * The subset of the group which denotes the type of modules.
     */
    val category: Property<String>

    /**
     * The name of the individual module.
     */
    val module: Property<String>
}