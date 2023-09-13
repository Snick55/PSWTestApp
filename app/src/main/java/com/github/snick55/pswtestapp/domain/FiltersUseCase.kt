package com.github.snick55.pswtestapp.domain

import com.github.snick55.pswtestapp.core.Container
import com.github.snick55.pswtestapp.data.CarsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface FiltersUseCase {


   fun getAllFilters(): Flow<Container <List<String>>>


    class Base @Inject constructor(
        private val repository: CarsRepository
    ) : FiltersUseCase {
        override  fun getAllFilters(): Flow<Container<List<String>>> {
            return repository.getAllFilters()
        }
    }
}