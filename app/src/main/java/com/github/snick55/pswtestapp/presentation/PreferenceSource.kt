package com.github.snick55.pswtestapp.presentation

import android.content.SharedPreferences
import javax.inject.Inject

interface PreferenceSource {

    fun setFlag(flag: Boolean)

    fun isFirstRun(): Boolean


    class Base @Inject constructor(private val sharedPreferences: SharedPreferences) :
        PreferenceSource {

        override fun setFlag(flag: Boolean) {
            sharedPreferences.edit().putBoolean(KEY_RUN, flag).apply()
        }

        override fun isFirstRun(): Boolean {
            return sharedPreferences.getBoolean(KEY_RUN, true)
        }

        private companion object {
            const val KEY_RUN = "RUN"
        }

    }
}