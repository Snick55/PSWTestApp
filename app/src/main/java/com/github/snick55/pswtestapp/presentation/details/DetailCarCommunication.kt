package com.github.snick55.pswtestapp.presentation.details

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import javax.inject.Inject

interface DetailCarCommunication {

    fun map(car: CarDetailUi)

    fun observeDetailCar(owner: LifecycleOwner,observer: Observer<CarDetailUi>)


    class Base @Inject constructor(): DetailCarCommunication{

        private val liveData = MutableLiveData<CarDetailUi>()

        override fun map(car: CarDetailUi) {
            liveData.value = car
        }

        override fun observeDetailCar(owner: LifecycleOwner, observer: Observer<CarDetailUi>) {
            liveData.observe(owner, observer)
        }
    }

}