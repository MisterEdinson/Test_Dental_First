package com.app.dental_first.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.app.dental_first.data.local.entity.CatalogEntity
import com.app.dental_first.data.local.entity.ProductEntity
import com.app.dental_first.data.local.entity.SubCategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CatalogProductsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCatalog(list: List<CatalogEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubCategories(list: List<SubCategoryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(list: List<ProductEntity>)

    @Transaction
    suspend fun insertAll(
        catalogs: List<CatalogEntity>,
        subCategories: List<SubCategoryEntity>,
        products: List<ProductEntity>
    ) {
        insertCatalog(catalogs)
        insertSubCategories(subCategories)
        insertProducts(products)
    }

    @Query("SELECT * FROM catalog ORDER BY id ASC")
    fun getAllCategories(): Flow<MutableList<CatalogEntity>>

    @Query("SELECT * FROM product WHERE catalogId = :categoryId")
    fun getProductsByCategory(categoryId: Int): Flow<List<ProductEntity>>

    @Query("UPDATE product SET itemsCart = :count WHERE id = :id")
    suspend fun updateCartCount(id: Int, count: Int)

    @Query("SELECT * FROM product WHERE itemsCart > 0")
    fun observeCartProducts(): Flow<List<ProductEntity>>

    @Query("UPDATE product SET isFavorite = :favorite WHERE id = :id")
    suspend fun updateCartFavorite(id: Int, favorite: Boolean)

    @Query("UPDATE product SET isSelected = :selected WHERE id = :id")
    suspend fun updateCartSelected(id: Int, selected: Boolean)
}