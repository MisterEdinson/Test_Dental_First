package com.app.dental_first.ui.screens.products

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.dental_first.data.local.entity.ProductEntity
import com.app.dental_first.usecase.ProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val api: ProductsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val categoryIdFlow = MutableStateFlow<Int?>(null)

    val products: StateFlow<List<ProductEntity>> =
        categoryIdFlow
            .filterNotNull()
            .flatMapLatest { id ->
                api.observeProductsByCategory(id)
            }
            .distinctUntilChanged()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )

    fun setCategory(categoryId: Int) {
        categoryIdFlow.value = categoryId
    }

    fun incrementProduct(product: ProductEntity) {
        viewModelScope.launch {
            api.updateCartCount(product.id, product.itemsCart + 1)
        }
    }

    fun decrementProduct(product: ProductEntity) {
        viewModelScope.launch {
            if (product.itemsCart > 0) {
                api.updateCartCount(product.id, product.itemsCart - 1)
            }
        }
    }

    fun favoriteProduct(product: ProductEntity) {
        viewModelScope.launch {
            val newFavorite = !product.isFavorite
            api.updateCartFavorite(product.id, newFavorite)
        }
    }
}