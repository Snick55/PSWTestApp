package com.github.snick55.pswtestapp.presentation.addCar

import com.github.snick55.pswtestapp.domain.CarAddDomain
import com.github.snick55.pswtestapp.domain.CarDomain

data class CarAddUi(
    private val id: Int = -1,
    private val brand: String,
    private val description: String,
    private val manufacturer: String,
    private val price: String
) {
    fun toDomain(): CarAddDomain = CarAddDomain(id,brand,description,manufacturer,price)
}
