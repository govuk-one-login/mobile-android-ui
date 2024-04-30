package uk.gov.ui.ext

import java.util.Locale

object StringExtensions {
    val kebabRegex = "-[a-zA-Z]".toRegex()
    val snakeRegex = "_[a-zA-Z]".toRegex()
    val proseRegex = " [a-zA-Z]".toRegex()

    fun String.kebabToLowerCamelCase(): String = kebabRegex.replace(this) {
        it.value.replace("-", "")
            .replaceFirstChar { char ->
                if (char.isLowerCase()) {
                    char.titlecase(Locale.getDefault())
                } else {
                    char.toString()
                }
            }
    }

    fun String.proseToLowerCamelCase(): String = proseRegex.replace(this) {
        it.value.replace(" ", "")
            .replaceFirstChar { char ->
                if (char.isLowerCase()) {
                    char.titlecase(Locale.getDefault())
                } else {
                    char.toString()
                }
            }
    }

    fun String.proseToUpperCamelCase(): String = this.proseToLowerCamelCase()
        .replaceFirstChar { char ->
            if (char.isLowerCase()) {
                char.titlecase(Locale.getDefault())
            } else {
                char.toString()
            }
        }

    fun String.snakeToLowerCamelCase(): String = snakeRegex.replace(this) {
        it.value.replace("_", "")
            .replaceFirstChar { char ->
                if (char.isLowerCase()) {
                    char.titlecase(Locale.getDefault())
                } else {
                    char.toString()
                }
            }
    }

    fun String.snakeToUpperCamelCase(): String = this.snakeToLowerCamelCase()
        .replaceFirstChar { char ->
            if (char.isLowerCase()) {
                char.titlecase(Locale.getDefault())
            } else {
                char.toString()
            }
        }
}
