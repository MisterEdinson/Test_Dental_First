package com.app.dental_first.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "product",
    indices = [
        Index("catalogId"),
        Index("subCategoryId")
    ]
)
data class ProductEntity (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val catalogId: Int,
    val subCategoryId: Int,
    val name: String,
    val image: String? = "",
    val price: Int,
    val isSelected: Boolean = false,
    val isFavorite: Boolean = false,
    val description: String,
    val itemsCart: Int = 0,
)