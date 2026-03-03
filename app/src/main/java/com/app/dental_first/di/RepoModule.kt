package com.app.dental_first.di

import com.app.dental_first.data.local.dao.CatalogProductsDao
import com.app.dental_first.data.network.NetworkApi
import com.app.dental_first.repository.DataRepository
import com.app.dental_first.repository.DataRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    @Provides
    @Singleton
    fun provideDataRepository(
        api: NetworkApi,
        catalogDb: CatalogProductsDao
    ): DataRepository {
        return DataRepositoryImpl(api, catalogDb)
    }
}