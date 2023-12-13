package com.shahzaman.devdocs

import android.app.Application
import com.shahzaman.devdocs.preference.Preferences

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Preferences.init(this)
    }
}