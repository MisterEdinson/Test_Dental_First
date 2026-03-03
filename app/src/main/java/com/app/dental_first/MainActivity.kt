package com.app.dental_first

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.app.dental_first.ui.screens.cart.CartViewModel
import com.app.dental_first.ui.screens.catalog.CatalogViewModel
import com.app.dental_first.ui.screens.home.HomeViewModel
import com.app.dental_first.ui.screens.products.ProductsViewModel
import com.app.dental_first.ui.screens.root.RootScreen
import com.app.dental_first.ui.theme.MainBlue
import com.app.dental_first.ui.theme.Test_Dental_FirstTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModels()
    private val catalogViewModel: CatalogViewModel by viewModels()
    private val productsViewModel: ProductsViewModel by viewModels()
    private val cartViewModel: CartViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContent {
            Test_Dental_FirstTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) {
                    RootScreen(
                        homeViewModel,
                        catalogViewModel,
                        productsViewModel,
                        cartViewModel
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetSystemBarsColor(
    color: androidx.compose.ui.graphics.Color,
    darkIcons: Boolean
) {
    val view = LocalView.current
    val window = (view.context as Activity).window

    SideEffect {

        // Цвет статус бара
        window.statusBarColor = color.toArgb()

        // Цвет навигационной панели (по желанию)
        window.navigationBarColor = color.toArgb()

        // Цвет иконок (тёмные или светлые)
        WindowCompat.getInsetsController(window, view)
            .isAppearanceLightStatusBars = darkIcons

        WindowCompat.getInsetsController(window, view)
            .isAppearanceLightNavigationBars = darkIcons
    }
}