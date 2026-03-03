package com.app.dental_first.repository

import android.util.Log
import com.app.dental_first.core.CatalogMapper
import com.app.dental_first.data.local.dao.CatalogProductsDao
import com.app.dental_first.data.local.entity.CatalogEntity
import com.app.dental_first.data.local.entity.ProductEntity
import com.app.dental_first.data.network.NetworkApi
import com.app.dental_first.data.network.models.ProductsResponse
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    val api: NetworkApi,
    val catalogDb: CatalogProductsDao
): DataRepository{
    override suspend fun getProductsNetwork(): Boolean =
        runCatching {
            val response = api.getProductsData()
            if (!response.isSuccessful) return false
            val body = response.body() ?: return false
            val (catalogs, subs, products) = CatalogMapper.mapToEntity(body)
            catalogDb.insertAll(catalogs, subs, products)
            Log.d("!@#$%", "Inserted catalogs: ${catalogs.size}")
            true
        }.getOrElse {
            false
        }

    override fun getCatalogLocal(): Flow<List<CatalogEntity>> { return catalogDb.getAllCategories() }

    override fun observeProductsByCategory(categoryId: Int): Flow<List<ProductEntity>> {
        return catalogDb.getProductsByCategory(categoryId)
    }
    override suspend fun updateCartCount(id: Int, count: Int) {
        catalogDb.updateCartCount(id, count)
    }
    override fun observeCartProducts(): Flow<List<ProductEntity>> {
        return catalogDb.observeCartProducts()
    }
    override suspend fun updateCartFavorite(id: Int, favorite: Boolean){
        catalogDb.updateCartFavorite(id, favorite)
    }
    override suspend fun updateCartSelected(id: Int, newSelected: Boolean){
        catalogDb.updateCartSelected(id, newSelected)
    }
}