package com.shahzaman.devdocs.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object Preferences {
    lateinit var instance: SharedPreferences

    const val themeKey = "theme"

    fun init(context: Context) {
        instance = context.getSharedPreferences("dev_docs", Context.MODE_PRIVATE)
    }

    fun edit(action: SharedPreferences.Editor.() -> Unit) = instance.edit(true, action)
}