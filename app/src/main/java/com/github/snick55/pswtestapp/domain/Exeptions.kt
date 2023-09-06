package com.github.snick55.pswtestapp.domain



class EmptyFieldException(
    val field: Field
): java.lang.Exception()

class IllegalPriceException(): java.lang.Exception()

enum class Field {
    BRAND,
    MANUFACTURES,
    PRICE
}