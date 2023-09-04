package com.github.snick55.pswtestapp.presentation.carsList

import javax.inject.Inject

interface DomainCarToItemUi<T> {

    fun map(
        id: Int,
        brand: String,
        description: String,
        manufacturer: String,
        price: Int
    ): T
    class Base @Inject constructor(): DomainCarToItemUi<CarItemUi> {

        override fun map(
            id: Int,
            brand: String,
            description: String,
            manufacturer: String,
            price: Int
        ): CarItemUi {
            return CarItemUi(id, brand, description, manufacturer, price)
        }
    }
}