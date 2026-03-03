package com.app.dental_first.ui.screens.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.app.dental_first.ui.navigaton.BottomBar
import com.app.dental_first.ui.navigaton.NavGraph
import com.app.dental_first.ui.screens.cart.CartViewModel
import com.app.dental_first.ui.screens.catalog.CatalogViewModel
import com.app.dental_first.ui.screens.home.HomeViewModel
import com.app.dental_first.ui.screens.products.ProductsViewModel

@Composable
fun RootScreen(
    homeViewModel: HomeViewModel,
    catalogViewModel: CatalogViewModel,
    productsViewModel: ProductsViewModel,
    cartViewModel: CartViewModel
) {
    val navController = rememberNavController()

    AppContent(
        navController = navController,
        homeViewModel = homeViewModel,
        catalogViewModel = catalogViewModel,
        productsViewModel = productsViewModel,
        cartViewModel = cartViewModel,
        onMenuClick = {}
    )
}
@Composable
fun AppContent(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    catalogViewModel: CatalogViewModel,
    productsViewModel: ProductsViewModel,
    cartViewModel: CartViewModel,
    onMenuClick: () -> Unit
){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomBar(navController = navController, onMenuClick = onMenuClick)
        }
    ){ innerPadding ->
        NavGraph(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            navController = navController,
            homeViewModel = homeViewModel,
            catalogViewModel = catalogViewModel,
            productsViewModel = productsViewModel,
            cartViewModel = cartViewModel
        )
    }
}