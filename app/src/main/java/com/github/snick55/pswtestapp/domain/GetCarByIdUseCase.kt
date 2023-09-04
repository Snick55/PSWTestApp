package com.github.snick55.pswtestapp.domain

import com.github.snick55.pswtestapp.data.CarsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetCarByIdUseCase {

    fun getCarById(id: Int): Flow<CarDetailDomain>

    class Base @Inject constructor(private val repository: CarsRepository): GetCarByIdUseCase{

        override fun getCarById(id: Int): Flow<CarDetailDomain> {
           return repository.getCarById(id)
        }
    }

}