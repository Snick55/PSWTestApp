package com.github.snick55.pswtestapp.domain

import com.github.snick55.pswtestapp.data.mappers.DomainToDataMapper
import com.github.snick55.pswtestapp.presentation.carsList.DomainCarToItemUi

data class CarDomain(
    private val id: Int,
    private val brand: String,
    private val description: String,
    private val manufacturer: String,
    private val price: Int
){

     fun <T> map(mapper: DomainToDataMapper<T>): T{
          return mapper.map(id,brand, description, manufacturer, price)
     }

    fun <T> map(mapper: DomainCarToItemUi<T>): T{
        return mapper.map(id,brand, description, manufacturer, price)
    }

}
