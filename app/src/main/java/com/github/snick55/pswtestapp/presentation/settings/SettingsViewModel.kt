package com.github.snick55.pswtestapp.presentation.settings

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.snick55.pswtestapp.core.Logg
import com.github.snick55.pswtestapp.domain.GetCarByIdUseCase
import com.github.snick55.pswtestapp.domain.UpdateCarUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor (
    private val updateCarUseCase: UpdateCarUseCase,
    private val getCarByIdUseCase: GetCarByIdUseCase,
    private val settingsCarCommunication: SettingsCarCommunication,
    private val mapper: CarDetailDomainToSettingsUiMapper<CarSettingsUi>
): ViewModel() {

    fun getCarById(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        getCarByIdUseCase.getCarById(id)
            .collect{
                withContext(Dispatchers.Main){
                    settingsCarCommunication.map(it.map(mapper))
                }
        }
    }


    fun updateCar(carSettingsUi: CarSettingsUi) = viewModelScope.launch(Dispatchers.IO) {
        try {
            updateCarUseCase.updateCar(carSettingsUi.toDomain())
        }catch (e: java.lang.Exception){
            Logg("something went wrong $e")
        }
    }

    fun observeCar(owner: LifecycleOwner,observer: Observer<CarSettingsUi>){
        settingsCarCommunication.observeSettingsCar(owner, observer)
    }
}