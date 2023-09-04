package com.github.snick55.pswtestapp.presentation.details

import javax.inject.Inject


interface DomainDetailsToUiMapper<T>{

    fun map( brand: String,
             description: String,
             manufacturer: String,
             price: Int): T

    class Base @Inject constructor() : DomainDetailsToUiMapper<CarDetailUi> {

        override fun map(
            brand: String,
            description: String,
            manufacturer: String,
            price: Int
        ): CarDetailUi {
            return CarDetailUi(brand, description, manufacturer, price)
        }
    }
}

