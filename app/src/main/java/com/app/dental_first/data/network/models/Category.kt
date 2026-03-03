package com.app.dental_first.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val items: List<Subcategory>
)