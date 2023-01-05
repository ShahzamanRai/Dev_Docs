package com.example.devdocs

sealed class docUrls(val url: String) {
    object android : docUrls("https://developer.android.com/guide")
    object arch : docUrls("https://wiki.archlinux.org/")
    object python : docUrls("https://docs.python.org/3/")
    object java : docUrls("https://docs.oracle.com/en/java/")
    object kotlin : docUrls("https://kotlinlang.org/docs/home.html")
    object compose : docUrls("https://developer.android.com/jetpack/compose/documentation")
    object javaScript : docUrls("https://developer.mozilla.org/en-US/docs/Web/JavaScript")
    object typeScript : docUrls("https://www.typescriptlang.org/docs/")
    object swift : docUrls("https://www.swift.org/documentation/")
    object lua : docUrls("https://www.lua.org/docs.html")
    object angular : docUrls("https://angular.io/docs")
    object react : docUrls("https://reactjs.org/docs/getting-started.html")
    object material : docUrls("https://m3.material.io/")
    object tensorFlow : docUrls("https://www.tensorflow.org/lite/android")
}