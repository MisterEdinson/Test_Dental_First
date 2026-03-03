package com.app.dental_first.ui.navigaton

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.app.dental_first.ui.screens.actions.ActionsScreen
import com.app.dental_first.ui.screens.cart.CartScreen
import com.app.dental_first.ui.screens.cart.CartViewModel
import com.app.dental_first.ui.screens.catalog.CatalogScreen
import com.app.dental_first.ui.screens.catalog.CatalogViewModel
import com.app.dental_first.ui.screens.home.HomeScreen
import com.app.dental_first.ui.screens.home.HomeViewModel
import com.app.dental_first.ui.screens.products.ProductsScreen
import com.app.dental_first.ui.screens.products.ProductsViewModel
import com.app.dental_first.ui.screens.profile.ProfileScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier,
    homeViewModel: HomeViewModel,
    catalogViewModel: CatalogViewModel,
    productsViewModel: ProductsViewModel,
    cartViewModel: CartViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(Screen.HomeScreen.route) {
            HomeScreen(
                navController = navController,
                homeViewModel = homeViewModel
            )
        }
        composable(Screen.CatalogScreen.route) {
            CatalogScreen(
                navController = navController,
                viewModel = catalogViewModel
            )
        }
        composable(Screen.CartScreen.route) {
            CartScreen(
                navController = navController,
                viewModel = cartViewModel
            )
        }
        composable(Screen.ProfileScreen.route) {
            ProfileScreen(
                navController = navController
            )
        }
        composable(Screen.ActionsScreen.route) {
            ActionsScreen(
                navController = navController
            )
        }
        composable(
            route = Screen.ProductsScreen.route,
            arguments = listOf(
                navArgument("categoryId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val categoryId = backStackEntry
                .arguments
                ?.getInt("categoryId") ?: 0

            ProductsScreen(
                categoryId = categoryId,
                navController = navController,
                productsViewModel = productsViewModel
            )
        }
    }
}