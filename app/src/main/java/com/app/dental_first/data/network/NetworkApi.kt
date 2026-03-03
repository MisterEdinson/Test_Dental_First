package com.app.dental_first.data.network

import com.app.dental_first.data.network.models.ProductsResponse
import retrofit2.Response
import retrofit2.http.GET

interface NetworkApi {
    @GET("uc?export=download&id=1bIgYsUcdnXbPVD-euR5juTck8HHkrZvM")
    suspend fun getProductsData(): Response<ProductsResponse>
}