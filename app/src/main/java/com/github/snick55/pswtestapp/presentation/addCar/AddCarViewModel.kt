package com.github.snick55.pswtestapp.presentation.addCar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.snick55.pswtestapp.core.Logg
import com.github.snick55.pswtestapp.domain.CreateCarUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCarViewModel @Inject constructor(
    private val useCase: CreateCarUseCase
) : ViewModel() {



    fun createCar(car: CarAddUi) = viewModelScope.launch(Dispatchers.IO){
        try {
            useCase.createCar(car.toDomain())
        }catch (e: java.lang.Exception){
            Logg("Create exception is $e")
            // make communication which contains the state of error
        }

    }
}