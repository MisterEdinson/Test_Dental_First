package com.app.dental_first.usecase

import com.app.dental_first.data.local.entity.CatalogEntity
import com.app.dental_first.data.local.entity.ProductEntity
import com.app.dental_first.repository.DataRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class ProductsUseCase @Inject constructor(
    @Named("io") private val dispatcher: CoroutineDispatcher,
    private val dataApi: DataRepository
) {
    suspend fun getProductsNetwork(): Boolean { return dataApi.getProductsNetwork() }
    fun observeCatalog(): Flow<List<CatalogEntity>> = dataApi.getCatalogLocal()
    fun observeProductsByCategory(categoryId: Int): Flow<List<ProductEntity>> =
        dataApi.observeProductsByCategory(categoryId)
    suspend fun updateCartCount(id: Int, count: Int) = withContext(dispatcher) {
            dataApi.updateCartCount(id, count)
        }
    fun observeCartProducts(): Flow<List<ProductEntity>> =
        dataApi.observeCartProducts()

    suspend fun updateCartFavorite(id: Int, newFavorite: Boolean) = withContext(dispatcher) {
        dataApi.updateCartFavorite(id, newFavorite)
    }
    suspend fun updateCartSelected(id: Int, newSelected: Boolean) = withContext(dispatcher) {
        dataApi.updateCartSelected(id, newSelected)
    }
}