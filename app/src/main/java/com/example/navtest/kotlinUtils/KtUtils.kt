package com.example.navtest.kotlinUtils

import java.util.Locale

    /**
     * Replacement for Kotlin's deprecated `capitalize()` function.
     */
    fun String.capitalized(): String {
        return this.replaceFirstChar {
            if (it.isLowerCase())
                it.titlecase(Locale.getDefault())
            else it.toString()
        }
    }