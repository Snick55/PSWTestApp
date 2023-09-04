package com.github.snick55.pswtestapp.data.carslist

import com.github.snick55.pswtestapp.domain.CarDetailDomain
import com.github.snick55.pswtestapp.domain.CarDomain

data class CarData(
    private val id: Int,
    private val brand: String,
    private val description: String,
    private val manufacturer: String,
    private val price: Int
) {
    fun carDataToDomain() = CarDomain(id, brand, description, manufacturer, price)

    fun carDataToDetailsDomain() = CarDetailDomain(brand, description, manufacturer, price)
}
