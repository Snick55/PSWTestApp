package com.github.snick55.pswtestapp.di

import com.github.snick55.pswtestapp.domain.GetCarByIdUseCase
import com.github.snick55.pswtestapp.domain.GetCarsUseCase
import com.github.snick55.pswtestapp.domain.InflateTableUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CarsDomainModule {

    @Binds
    abstract fun bindCarsUseCase(useCase: GetCarsUseCase.Base): GetCarsUseCase

    @Binds
    abstract fun bindInflateTableUseCase(useCase: InflateTableUseCase.Base): InflateTableUseCase

    @Binds
    abstract fun bindGetCarByIdUseCase(getCarByIdUseCase: GetCarByIdUseCase.Base):GetCarByIdUseCase

}