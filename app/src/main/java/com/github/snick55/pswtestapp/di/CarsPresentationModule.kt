package com.github.snick55.pswtestapp.di

import com.github.snick55.pswtestapp.presentation.PreferenceSource
import com.github.snick55.pswtestapp.presentation.carsList.CarItemUi
import com.github.snick55.pswtestapp.presentation.carsList.DomainCarToItemUi
import com.github.snick55.pswtestapp.presentation.details.CarDetailUi
import com.github.snick55.pswtestapp.presentation.details.DetailCarCommunication
import com.github.snick55.pswtestapp.presentation.details.DomainDetailsToUiMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class CarsPresentationModule {

    @Binds
    abstract fun bindPreferenceSource(preference: PreferenceSource.Base): PreferenceSource


    @Binds
    abstract fun bindDetailsCommunication(communication: DetailCarCommunication.Base): DetailCarCommunication

    @Binds
    abstract fun bindToUiMapper(mapper: DomainDetailsToUiMapper.Base): DomainDetailsToUiMapper<CarDetailUi>

    @Binds
    abstract fun bindDomainCarToItemUi(mapper: DomainCarToItemUi.Base): DomainCarToItemUi<CarItemUi>

}