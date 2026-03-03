package com.app.dental_first.ui.screens.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.dental_first.data.local.entity.ProductEntity
import com.app.dental_first.usecase.ProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class CartViewModel @Inject constructor(
    val api: ProductsUseCase
) : ViewModel(){
    val cartProducts: StateFlow<List<ProductEntity>> =
        api.observeCartProducts()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )

    fun incrementProduct(product: ProductEntity) {
        viewModelScope.launch {
            val newCount = product.itemsCart + 1
            api.updateCartCount(product.id, newCount)
        }
    }

    fun decrementProduct(product: ProductEntity) {
        viewModelScope.launch {
            if (product.itemsCart > 0) {
                val newCount = product.itemsCart - 1
                api.updateCartCount(product.id, newCount)
            }
        }
    }
    fun deleteProduct(product: ProductEntity) {
        viewModelScope.launch {
            if (product.itemsCart > 0) {
                api.updateCartCount(product.id, 0)
            }
        }
    }
    fun favoriteProduct(product: ProductEntity) {
        viewModelScope.launch {
            if (product.itemsCart > 0) {
                val newFavorite = !product.isFavorite
                api.updateCartFavorite(product.id, newFavorite)
            }
        }
    }
    fun toggleSelectProduct(product: ProductEntity) {
        viewModelScope.launch {
            if (product.itemsCart > 0) {
                val newSelect = !product.isSelected
                api.updateCartSelected(product.id, newSelect)
            }
        }
    }
}