package com.github.snick55.pswtestapp.presentation.settings

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import javax.inject.Inject

interface SettingsCarCommunication {

    fun map(car: CarSettingsUi)

    fun observeSettingsCar(owner: LifecycleOwner, observer: Observer<CarSettingsUi>)

    class Base @Inject constructor(): SettingsCarCommunication {

        private val liveData = MutableLiveData<CarSettingsUi>()

        override fun map(car: CarSettingsUi) {
            liveData.value = car
        }

        override fun observeSettingsCar(owner: LifecycleOwner, observer: Observer<CarSettingsUi>) {
            liveData.observe(owner, observer)
        }
    }
}