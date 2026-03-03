package com.app.dental_first.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int,
    val name: String,
    val image: String,
    val price: Int,
    val description: String
)