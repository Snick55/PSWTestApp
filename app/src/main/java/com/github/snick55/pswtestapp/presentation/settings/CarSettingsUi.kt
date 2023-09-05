package com.github.snick55.pswtestapp.presentation.settings

import com.github.snick55.pswtestapp.domain.CarDomain
import com.google.android.material.textfield.TextInputEditText

data class CarSettingsUi(
     val id: Int,
     val brand: String,
     val description: String,
     val manufacturer: String,
     val price: Int = 0
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
