package com.shahzaman.devdocs.language

import com.shahzaman.devdocs.R

sealed class Languages(val url: String, val title: String, val image: Int) {
    object android : Languages(
        "https://developer.android.com/guide",
        "Android",
        R.drawable.android
    )

    object arch : Languages(
        "https://wiki.archlinux.org/",
        "Arch Linux",
        R.drawable.arch
    )

    object python : Languages(
        "https://docs.python.org/3/",
        "Python",
        R.drawable.python
    )

    object java : Languages(
        "https://docs.oracle.com/en/java/",
        "Java",
        R.drawable.java
    )

    object kotlin : Languages(
        "https://kotlinlang.org/docs/home.html",
        "Kotlin",
        R.drawable.kotlin
    )

    object compose : Languages(
        "https://developer.android.com/jetpack/compose/documentation",
        "Compose",
        R.drawable.compose
    )

    object javaScript : Languages(
        "https://developer.mozilla.org/en-US/docs/Web/JavaScript",
        "JavaScript",
        R.drawable.javascript
    )

    object typeScript :
        Languages(
            "https://www.typescriptlang.org/docs/",
            "TypeScript",
            R.drawable.typescript
        )

    object swift : Languages(
        "https://www.swift.org/documentation/",
        "Swift",
        R.drawable.swift
    )

    object lua : Languages(
        "https://www.lua.org/docs.html",
        "Lua",
        R.drawable.lua
    )

    object angular : Languages(
        "https://angular.io/docs",
        "Angular",
        R.drawable.angular
    )

    object react :
        Languages(
            "https://reactjs.org/docs/getting-started.html",
            "React",
            R.drawable.react
        )

    object material : Languages(
        "https://m3.material.io/",
        "Material 3",
        R.drawable.material
    )

    object tensorFlow :
        Languages(
            "https://www.tensorflow.org/lite/android",
            "Tensorflow",
            R.drawable.tensorflow
        )
}

val languageList = listOf(
    Languages.android,
    Languages.compose,
    Languages.material,
    Languages.tensorFlow,
    Languages.kotlin,
    Languages.java,
    Languages.python,
    Languages.javaScript,
    Languages.typeScript,
    Languages.react,
    Languages.angular,
    Languages.swift,
    Languages.lua,
    Languages.arch,
)