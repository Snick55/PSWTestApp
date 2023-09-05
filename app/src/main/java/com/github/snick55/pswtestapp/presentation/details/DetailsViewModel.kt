package com.github.snick55.pswtestapp.presentation.details

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.snick55.pswtestapp.domain.GetCarByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getCarByIdUseCase: GetCarByIdUseCase,
    private val mapper: DomainDetailsToUiMapper<CarDetailUi>,
    private val communication: DetailCarCommunication
) : ViewModel() {

    fun getCarById(id: Int) {
      viewModelScope.launch(Dispatchers.IO) {
          getCarByIdUseCase.getCarById(id).collect{
              withContext(Dispatchers.Main){
                  communication.map(it.map(mapper))
              }
          }
      }
    }


    fun observeDetailCar(owner: LifecycleOwner,observer: Observer<CarDetailUi>){
        communication.observeDetailCar(owner, observer)
    }

}