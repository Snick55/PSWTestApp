package com.github.snick55.pswtestapp.domain

import com.github.snick55.pswtestapp.data.CarsRepository
import javax.inject.Inject

interface InflateTableUseCase {

    suspend fun inflate()

    class Base @Inject constructor(private val repository: CarsRepository): InflateTableUseCase{

        override suspend fun inflate() {
            repository.inflate()
        }
    }

}