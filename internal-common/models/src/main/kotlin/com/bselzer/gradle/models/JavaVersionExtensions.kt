package com.bselzer.gradle.models

import org.gradle.api.JavaVersion

fun JavaVersion.toNumericString(): String = when (this) {
    JavaVersion.VERSION_1_1 -> "1.1"
    JavaVersion.VERSION_1_2 -> "1.2"
    JavaVersion.VERSION_1_3 -> "1.3"
    JavaVersion.VERSION_1_4 -> "1.4"
    JavaVersion.VERSION_1_5 -> "1.5"
    JavaVersion.VERSION_1_6 -> "1.6"
    JavaVersion.VERSION_1_7 -> "1.7"
    JavaVersion.VERSION_1_8 -> "1.8"
    JavaVersion.VERSION_1_9 -> "9"
    JavaVersion.VERSION_1_10 -> "10"
    JavaVersion.VERSION_11 -> "11"
    JavaVersion.VERSION_12 -> "12"
    JavaVersion.VERSION_13 -> "13"
    JavaVersion.VERSION_14 -> "14"
    JavaVersion.VERSION_15 -> "15"
    JavaVersion.VERSION_16 -> "16"
    JavaVersion.VERSION_17 -> "17"
    JavaVersion.VERSION_18 -> "18"
    JavaVersion.VERSION_19 -> "19"
    JavaVersion.VERSION_20 -> "20"
    JavaVersion.VERSION_21 -> "21"
    JavaVersion.VERSION_22 -> "22"
    JavaVersion.VERSION_23 -> "23"
    JavaVersion.VERSION_24 -> "24"
    JavaVersion.VERSION_HIGHER -> throw UnsupportedOperationException("Unable to convert a JavaVersion.VERSION_HIGHER to a numeric string.")
}

fun JavaVersion.toInt(): Int = when (this) {
    JavaVersion.VERSION_1_1,
    JavaVersion.VERSION_1_2,
    JavaVersion.VERSION_1_3,
    JavaVersion.VERSION_1_4,
    JavaVersion.VERSION_1_5,
    JavaVersion.VERSION_1_6,
    JavaVersion.VERSION_1_7,
    JavaVersion.VERSION_1_8,
    JavaVersion.VERSION_HIGHER -> throw UnsupportedOperationException("Unable to convert JavaVersion $this to an integer.")

    JavaVersion.VERSION_1_9 -> 9
    JavaVersion.VERSION_1_10 -> 10
    JavaVersion.VERSION_11 -> 11
    JavaVersion.VERSION_12 -> 12
    JavaVersion.VERSION_13 -> 13
    JavaVersion.VERSION_14 -> 14
    JavaVersion.VERSION_15 -> 15
    JavaVersion.VERSION_16 -> 16
    JavaVersion.VERSION_17 -> 17
    JavaVersion.VERSION_18 -> 18
    JavaVersion.VERSION_19 -> 19
    JavaVersion.VERSION_20 -> 20
    JavaVersion.VERSION_21 -> 21
    JavaVersion.VERSION_22 -> 22
    JavaVersion.VERSION_23 -> 23
    JavaVersion.VERSION_24 -> 24
}