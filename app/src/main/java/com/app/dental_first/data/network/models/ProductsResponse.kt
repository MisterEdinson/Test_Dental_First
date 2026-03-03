package com.app.dental_first.data.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductsResponse(
    @SerialName("catalog")
    val catalog: List<Category>,

    @SerialName("products")
    val products: List<Product>
)