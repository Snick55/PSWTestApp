package com.github.snick55.pswtestapp.domain

import com.github.snick55.pswtestapp.data.CarsRepository
import javax.inject.Inject

interface CreateCarUseCase {

    suspend fun createCar(carAddDomain: CarAddDomain)

    class Base @Inject constructor(
        private val repository: CarsRepository,
        private val validator: Validator
    ): CreateCarUseCase{

        override suspend fun createCar(carAddDomain: CarAddDomain) {
            validator.validate(carAddDomain.brand,carAddDomain.manufacturer,carAddDomain.price)
            repository.createCar(carAddDomain)
        }
    }
}