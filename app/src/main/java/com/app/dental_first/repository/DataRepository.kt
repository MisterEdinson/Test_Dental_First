package com.app.dental_first.repository

import com.app.dental_first.data.local.entity.CatalogEntity
import com.app.dental_first.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

interface DataRepository {
    suspend fun getProductsNetwork(): Boolean
    fun getCatalogLocal(): Flow<List<CatalogEntity>>
    fun observeProductsByCategory(categoryId: Int): Flow<List<ProductEntity>>
    suspend fun updateCartCount(id: Int, count: Int)

    fun observeCartProducts(): Flow<List<ProductEntity>>
    suspend fun updateCartFavorite(id: Int, favorite: Boolean)
    suspend fun updateCartSelected(id: Int, newSelected: Boolean)
}