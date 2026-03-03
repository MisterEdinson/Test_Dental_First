package com.app.dental_first.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subcategory")
data class SubCategoryEntity (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val catalogId: Int,
    val image: String? = "",
    val name: String,
    val description: String
)