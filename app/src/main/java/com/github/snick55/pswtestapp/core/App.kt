package com.github.snick55.pswtestapp.core

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {
}


fun Logg(string: String) = Log.d("Tag",string)