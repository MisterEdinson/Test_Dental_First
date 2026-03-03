package com.app.dental_first.ui.screens.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.dental_first.SetSystemBarsColor
import com.app.dental_first.ui.screens.components.ProductCard
import com.app.dental_first.ui.screens.components.ProductsFilterRow
import com.app.dental_first.ui.screens.components.ProductsHeader
import com.app.dental_first.ui.screens.components.ProductsTabs
import com.app.dental_first.ui.theme.GradientBlue
import com.app.dental_first.ui.theme.SupperLite

@Composable
fun ProductsScreen(
    categoryId: Int,
    navController: NavController,
    productsViewModel: ProductsViewModel
) {
    SetSystemBarsColor(
        color = SupperLite,
        darkIcons = true
    )
    val products by productsViewModel.products.collectAsState()

    LaunchedEffect(categoryId) {
        productsViewModel.setCategory(categoryId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        ProductsHeader(navController)
        Spacer(Modifier.height(12.dp))
        ProductsTabs()
        Spacer(Modifier.height(12.dp))
        ProductsFilterRow()
        Spacer(Modifier.height(12.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.weight(1f)
        ) {

            items(products, key = { it.id }) { product ->

                ProductCard(
                    product,
                    {
                        productsViewModel.incrementProduct(it)
                    },
                    {
                        productsViewModel.decrementProduct(it)
                    },
                    { productsViewModel.favoriteProduct(it) }
                )
            }

            item(span = { GridItemSpan(maxLineSpan) }) {
                Spacer(Modifier.height(120.dp))
            }
        }
    }
}
