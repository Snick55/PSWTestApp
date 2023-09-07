package com.github.snick55.pswtestapp.presentation.addCar

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.snick55.pswtestapp.domain.CreateCarUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AddCarViewModel @Inject constructor(
    private val useCase: CreateCarUseCase,
    private val communication: AddCommunication
) : ViewModel() {


    fun createCar(car: CarAddUi) = viewModelScope.launch {
        communication.showProgress()
        withContext(Dispatchers.IO) {
            try {
                useCase.createCar(car.toDomain())
                withContext(Dispatchers.Main){
                    communication.canGoBack()
                }
            } catch (e: java.lang.Exception) {
                withContext(Dispatchers.Main) {
                    communication.map(e)
                }
            }
        }
    }


    fun observeState(owner: LifecycleOwner, observer: Observer<AddCommunication.State>) {
        communication.observe(owner, observer)
    }


}