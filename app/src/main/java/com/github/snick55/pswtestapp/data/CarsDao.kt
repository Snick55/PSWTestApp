package com.github.snick55.pswtestapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CarsDao {

    @Query("SELECT * FROM cars")
    suspend fun getCachedCars(): List<CarDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCar(carDB: CarDB)

    @Query("SELECT * FROM cars WHERE id = :id")
    fun getCarById(id: Int): Flow<CarDB>

    @Update(entity = CarDB::class, onConflict = OnConflictStrategy.REPLACE)
   suspend fun updateCar(carDB: CarDB)
}