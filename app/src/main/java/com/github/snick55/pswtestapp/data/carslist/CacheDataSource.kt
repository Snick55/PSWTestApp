package com.github.snick55.pswtestapp.data.carslist

import com.github.snick55.pswtestapp.data.EmptyCacheException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface CacheDataSource {

   suspend fun getCachedCars(): List<CarData>

   suspend fun inflate()

   fun getCarById(id: Int): Flow<CarData>

   suspend fun updateCar(carData: CarData)

    class Base @Inject constructor(private val dao: CarsDao) : CacheDataSource {

        private val initialCars = listOf<CarDB>(
            CarDB(1,"BMW","Some description","Germany",999999),
            CarDB(2,"Volkswagen","Some description","Germany",8888),
            CarDB(3,"Porsche","Some description","Germany",123123),
            CarDB(4,"Lexus","Some description","JAPAN",13331),
            CarDB(5,"Nissan","Some description","JAPAN",182772),
            CarDB(6,"Mazda","Some description","JAPAN",139183),
            CarDB(7,"Lada","Some description","Russia",121211),
            CarDB(8,"Moskvich","Some description","Russia",9000000),
            CarDB(9,"GAZ","Some description","Russia",333333),
            CarDB(10,"Renault","Some description","France",343335),
            CarDB(11,"Peugeot","Some description","France",938271),
        )

        override fun getCarById(id: Int): Flow<CarData> {
            return dao.getCarById(id).map{
                it.carDbToData()
            }
        }

        override suspend fun updateCar(carData: CarData) {
           dao.updateCar(carData.toCarDb())
        }

        override suspend fun getCachedCars():List<CarData> {
            delay(1000) //fake loading
            val result = dao.getCachedCars()

            if (result.isEmpty()) throw EmptyCacheException("it's empty here")
            return result.map {
                it.carDbToData()
            }
        }

        override suspend fun inflate() {
            initialCars.forEach {
                dao.insertCar(it)
            }
        }
    }

}


