package com.github.snick55.pswtestapp.presentation.settings

import com.github.snick55.pswtestapp.domain.CarDomain
import com.google.android.material.textfield.TextInputEditText

data class CarSettingsUi(
    private val id: Int,
    private val brand: String,
    private val description: String,
    private val manufacturer: String,
    private val price: Int = 0
) {
    fun toDomain() = CarDomain(id, brand, description, manufacturer, price)
    fun show(
        brandEditText: TextInputEditText,
        descriptionEditText: TextInputEditText,
        manufactureEditText: TextInputEditText,
        priceEditText: TextInputEditText
    ) {
        brandEditText.hint = brand
        descriptionEditText.hint = description
        manufactureEditText.hint = manufacturer
        priceEditText.hint = price.toString()
    }
}
