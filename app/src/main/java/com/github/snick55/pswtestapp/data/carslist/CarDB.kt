package com.github.snick55.pswtestapp.data.carslist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
data class CarDB(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo("brand")
    val brand: String,
    @ColumnInfo("description")
    val description: String,
    @ColumnInfo("manufacturer")
    val manufacturer: String,
    @ColumnInfo("price")
    val price: Int
) {
    fun carDbToData() = CarData(id, brand, description, manufacturer, price)
}
