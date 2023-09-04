package com.github.snick55.pswtestapp.domain

import com.github.snick55.pswtestapp.presentation.details.DomainDetailsToUiMapper
import com.github.snick55.pswtestapp.presentation.settings.CarDetailDomainToSettingsUiMapper

data class CarDetailDomain(
    private val brand: String,
    private val description: String,
    private val manufacturer: String,
    private val price: Int
){

    fun <T> map(mapper: DomainDetailsToUiMapper<T>):T{
        return mapper.map(brand,description,manufacturer,price)
    }

    fun <T> map(mapper: CarDetailDomainToSettingsUiMapper<T>):T{
        return mapper.map(brand,description,manufacturer,price)
    }
}
