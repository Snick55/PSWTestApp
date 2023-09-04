package com.github.snick55.pswtestapp.data.mappers

import com.github.snick55.pswtestapp.data.carslist.CarData
import javax.inject.Inject

interface DomainToDataMapper<T> {

    fun map(
        id: Int,
        brand: String,
        description: String,
        manufacturer: String,
        price: Int
    ): T

    class Base @Inject constructor(): DomainToDataMapper<CarData>{

        override fun map(
            id: Int,
            brand: String,
            description: String,
            manufacturer: String,
            price: Int
        ): CarData {
            return CarData(id, brand, description, manufacturer, price)
        }
    }

}