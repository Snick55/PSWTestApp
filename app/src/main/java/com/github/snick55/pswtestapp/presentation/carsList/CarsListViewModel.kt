package com.github.snick55.pswtestapp.presentation.carsList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.snick55.pswtestapp.core.Container
import com.github.snick55.pswtestapp.domain.GetCarsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CarsListViewModel @Inject constructor(
    private val getCarsUseCase: GetCarsUseCase,
    private val mapper: DomainCarToItemUi<CarItemUi>
) : ViewModel() {

    private val _cars = MutableLiveData<Container<List<CarItemUi>>>()
    val cars: LiveData<Container<List<CarItemUi>>> = _cars

    fun getCars(isSorted: Boolean = false) = viewModelScope.launch(Dispatchers.IO) {
        val carsUi = getCarsUseCase.getAllCars(isSorted).map { cars ->
            cars.map {
               it.map(mapper)
            }
        }
        withContext(Dispatchers.Main) {
            _cars.value = carsUi
        }
    }
}