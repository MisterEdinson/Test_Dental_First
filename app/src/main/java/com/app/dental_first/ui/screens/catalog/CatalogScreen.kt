package com.app.dental_first.ui.screens.catalog

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.app.dental_first.SetSystemBarsColor
import com.app.dental_first.ui.navigaton.BottomBar
import com.app.dental_first.ui.navigaton.Screen
import com.app.dental_first.ui.screens.components.CatalogCard
import com.app.dental_first.ui.screens.components.CollapseButton
import com.app.dental_first.ui.screens.components.CollapsedCatalogStack
import com.app.dental_first.ui.screens.components.SearchBar
import com.app.dental_first.ui.theme.GradientBlue
import com.app.dental_first.ui.theme.MainBlue

@Composable
fun CatalogScreen(
    navController: NavController,
    viewModel: CatalogViewModel
) {
    SetSystemBarsColor(
        color = Color(0xFF7683FF),
        darkIcons = true
    )
    val items by viewModel.catalogItems.collectAsState()
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        containerColor = Color.Transparent,
        contentWindowInsets = WindowInsets.systemBars,
        bottomBar = {
            BottomBar(
                navController = navController as NavHostController,
                onMenuClick = {}
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(GradientBlue)
                .padding(innerPadding)
        ) {

            Column(Modifier.fillMaxSize()) {

                Spacer(Modifier.height(20.dp))

                Text(
                    "Каталог товаров",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Text(
                    "Более 60 000 позиций",
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White.copy(0.7f),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(Modifier.height(16.dp))

                SearchBar()

                Spacer(Modifier.height(16.dp))
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    AnimatedContent(
                        targetState = isExpanded,
                        label = "catalog_anim"
                    ) { expanded ->

                        if (expanded) {
                            LazyColumn(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                items(items) {
                                    CatalogCard(it) { id ->
                                        navController.navigate(
                                            Screen.ProductsScreen.createRoute(id)
                                        )
                                    }
                                }
                            }
                        } else {
                            CollapsedCatalogStack(
                                items = items,
                                expanded = { isExpanded = !isExpanded }
                            )
                        }
                    }
                }

                Spacer(Modifier.height(10.dp))

                CollapseButton(
                    expanded = isExpanded,
                    onClick = { isExpanded = !isExpanded }
                )

                Spacer(Modifier.height(20.dp))
            }
        }
    }
}