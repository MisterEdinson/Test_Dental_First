package com.app.dental_first.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.dental_first.usecase.ProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(
    val api: ProductsUseCase
) : ViewModel() {

    init { initGetProducts() }
    private fun initGetProducts(){ viewModelScope.launch { api.getProductsNetwork() } }
}