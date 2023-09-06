package com.github.snick55.pswtestapp.domain

import javax.inject.Inject

interface Validator {


    fun validate(brand: String, manufactures: String, price: String)

    class Base @Inject constructor(): Validator{

        override fun validate(brand: String, manufactures: String, price: String) {
            if (brand.isBlank()) throw EmptyFieldException(Field.BRAND)
            if (manufactures.isBlank()) throw EmptyFieldException(Field.MANUFACTURES)
            if (price.isBlank()) throw EmptyFieldException(Field.PRICE)
            if (price.toIntOrNull() == null) throw IllegalPriceException()
        }
    }
}