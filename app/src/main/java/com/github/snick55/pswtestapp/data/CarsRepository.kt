package com.github.snick55.pswtestapp.data

import com.github.snick55.pswtestapp.core.Container
import com.github.snick55.pswtestapp.data.mappers.DomainToDataMapper
import com.github.snick55.pswtestapp.domain.CarAddDomain
import com.github.snick55.pswtestapp.domain.CarDetailDomain
import com.github.snick55.pswtestapp.domain.CarDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface CarsRepository {

    suspend fun getAllCars(): Container<List<CarDomain>>

    suspend fun inflate()

    fun getCarById(id: Int): Flow<CarDetailDomain>

    suspend fun updateCar(car: CarDomain)

    suspend fun createCar(carAddDomain: CarAddDomain)

     fun getAllFilters(): Flow<Container<List<String>>>


    class Base @Inject constructor(
        private val cacheDataSource: CacheDataSource,
        private val mapper: DomainToDataMapper<CarData>,
    ) : CarsRepository {

        override fun getCarById(id: Int): Flow<CarDetailDomain> {
            return cacheDataSource.getCarById(id).map {
                it.carDataToDetailsDomain()
            }
        }

        override suspend fun getAllCars(): Container<List<CarDomain>> {
            return try {
                Container.Success(cacheDataSource.getCachedCars().map {
                    it.carDataToDomain()

                })
            } catch (e: Exception) {
                Container.Error(e)
            }
        }

        override suspend fun updateCar(car: CarDomain) {
            cacheDataSource.updateCar(car.map(mapper))
        }

        override suspend fun inflate() {
            cacheDataSource.inflate()
        }

        override suspend fun createCar(carAddDomain: CarAddDomain) {
            cacheDataSource.createCar(
                CarData(
                    carAddDomain.id,
                    carAddDomain.brand,
                    carAddDomain.description,
                    carAddDomain.manufacturer,
                    carAddDomain.price.toInt()
                )
            )
        }

        override  fun getAllFilters(): Flow<Container<List<String>>> = flow {
            val set = hashSetOf<String>()
            getAllCars().unwrap().forEach {
                set.add(it.manufacturer.lowercase())
            }
            if (set.isEmpty())
                emit(Container.Error(EmptyCacheException("No one filters")))
            else
                emit(Container.Success(set.toList()))
        }
    }


}