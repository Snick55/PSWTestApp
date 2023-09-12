package com.github.snick55.pswtestapp.di

import com.github.snick55.pswtestapp.data.CarsRepository
import com.github.snick55.pswtestapp.data.CacheDataSource
import com.github.snick55.pswtestapp.data.CarData
import com.github.snick55.pswtestapp.data.mappers.DomainToDataMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CarsDataModule {

    @Binds
    abstract fun bindCarsRepository(repository: CarsRepository.Base): CarsRepository

    @Binds
    abstract fun bindCacheDataSource(cacheDataSource: CacheDataSource.Base): CacheDataSource

    @Binds
    abstract fun bindDomainToDataMapper(mapper: DomainToDataMapper.Base): DomainToDataMapper<CarData>



}