package com.github.snick55.pswtestapp.data.carslist

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CarsDao {

    @Query("SELECT * FROM cars")
    suspend fun getCachedCars(): List<CarDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCar(carDB: CarDB)

    @Query("SELECT * FROM cars WHERE id = :id")
    fun getCarById(id: Int): Flow<CarDB>
}