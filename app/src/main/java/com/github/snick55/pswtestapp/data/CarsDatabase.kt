package com.github.snick55.pswtestapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CarDB::class], version = 1)
abstract class CarsDatabase : RoomDatabase() {

    abstract fun carsDao(): CarsDao

}