package com.github.snick55.pswtestapp.presentation.settings

import javax.inject.Inject

interface CarDetailDomainToSettingsUiMapper<T> {


    fun map(
        brand: String,
        description: String,
        manufacturer: String,
        price: Int
    ): T

    class Base @Inject constructor() : CarDetailDomainToSettingsUiMapper<CarSettingsUi> {
        override fun map(
            brand: String,
            description: String,
            manufacturer: String,
            price: Int
        ): CarSettingsUi {
            return CarSettingsUi(0,brand, description, manufacturer, price)
        }
    }

}