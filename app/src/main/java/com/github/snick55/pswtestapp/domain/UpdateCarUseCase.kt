package com.github.snick55.pswtestapp.domain

import com.github.snick55.pswtestapp.data.CarsRepository
import javax.inject.Inject

interface UpdateCarUseCase {

    suspend fun updateCar(car: CarDomain)


    class Base @Inject constructor (private val repository: CarsRepository): UpdateCarUseCase{

        override suspend fun updateCar(car: CarDomain) {
            repository.updateCar(car)
        }
    }
}