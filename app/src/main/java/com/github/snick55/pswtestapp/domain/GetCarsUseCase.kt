package com.github.snick55.pswtestapp.domain

import com.github.snick55.pswtestapp.core.Container
import com.github.snick55.pswtestapp.core.Logg
import com.github.snick55.pswtestapp.data.CarsRepository
import javax.inject.Inject

interface GetCarsUseCase {

   suspend fun getAllCars(isSorted: Boolean): Container<List<CarDomain>>

    class Base @Inject constructor(private val carsRepository: CarsRepository):GetCarsUseCase{

        override suspend fun getAllCars(isSorted: Boolean): Container<List<CarDomain>> {
            return  if (isSorted) {
                carsRepository.getAllCars().map {
                    it.sortedBy {carDomain->
                        carDomain.price
                    }
                }
            }else carsRepository.getAllCars()
        }
    }

}