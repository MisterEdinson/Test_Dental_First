package com.app.dental_first.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.dental_first.data.local.dao.CatalogProductsDao
import com.app.dental_first.data.local.entity.CatalogEntity
import com.app.dental_first.data.local.entity.ProductEntity
import com.app.dental_first.data.local.entity.SubCategoryEntity

@Database(
    entities = [
        CatalogEntity::class,
        SubCategoryEntity::class,
        ProductEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class RoomDB : RoomDatabase() {
    abstract fun catalogProductsDao(): CatalogProductsDao
}