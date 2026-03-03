package com.app.dental_first.di

import android.content.Context
import androidx.room.Room
import com.app.dental_first.data.local.RoomDB
import com.app.dental_first.data.local.dao.CatalogProductsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    private const val DATABASE_NAME = "BD_Room"

    @Provides
    @Singleton
    fun provideDb(
        @ApplicationContext context: Context
    ): RoomDB {
        return Room.databaseBuilder(
            context,
            RoomDB::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideCatalogProductsDao(
        db: RoomDB
    ): CatalogProductsDao {
        return db.catalogProductsDao()
    }
}