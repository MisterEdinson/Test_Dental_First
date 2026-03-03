package com.app.dental_first.ui.navigaton

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.app.dental_first.R

open class Screen (
    val route: String,
    @StringRes val title: Int? = null,
    @DrawableRes val icon: Int? = null,
){
    object SplashScreen : Screen(
        route = "splash_screen"
    )
    object HomeScreen : Screen(
        route = "home_screen",
        title = R.string.bottom_menu_home,
        icon = R.drawable.ic_home
    )
    object CatalogScreen : Screen(
        route = "catalog_screen",
        title = R.string.bottom_menu_catalog,
        icon = R.drawable.ic_catalog
    )
    object CartScreen : Screen(
        route = "cart_screen",
        title = R.string.bottom_menu_cart,
        icon = R.drawable.ic_cart
    )
    object ProfileScreen : Screen(
        route = "profile_screen",
        title = R.string.bottom_menu_profile,
        icon = R.drawable.ic_profile
    )
    object ActionsScreen : Screen(
        route = "actions_screen",
        title = R.string.bottom_menu_actions,
        icon = R.drawable.ic_actions
    )
    object ProductsScreen : Screen(
        route = "products_screen/{categoryId}"
    ) {
        fun createRoute(categoryId: Int) = "products_screen/$categoryId"
    }
}