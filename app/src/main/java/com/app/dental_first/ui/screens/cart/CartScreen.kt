package com.app.dental_first.ui.screens.cart

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.app.dental_first.data.local.entity.ProductEntity
import com.app.dental_first.ui.navigaton.BottomBar
import com.app.dental_first.ui.navigaton.Screen
import com.app.dental_first.ui.screens.components.CartHeader
import com.app.dental_first.ui.screens.components.CartItemPreviewCard
import com.app.dental_first.ui.screens.components.EmptyCartScreen
import com.app.dental_first.ui.screens.components.SwipeToDeleteContainer
import com.app.dental_first.ui.theme.MainBlue
import com.app.dental_first.ui.theme.MainBlueLight
import com.app.dental_first.ui.theme.MainWhite
import com.app.dental_first.ui.theme.SupperLite
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.platform.LocalContext
import com.app.dental_first.SetSystemBarsColor
import com.app.dental_first.core.toPriceFormat
import com.app.dental_first.ui.screens.components.SelectAllRow

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun CartScreen(
    navController: NavController,
    viewModel: CartViewModel
) {
    val cartProducts by viewModel.cartProducts.collectAsState()
    var isChecked by remember { mutableStateOf(false) }

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
        if (cartProducts.isEmpty()) {
            SetSystemBarsColor(
                color = MainBlue,
                darkIcons = true
            )
            EmptyCartScreen(onGoToCatalog = { navController.navigate(Screen.CatalogScreen.route) })
        } else {
            SetSystemBarsColor(
                color = SupperLite,
                darkIcons = true
            )
            val totalPrice =
                remember(cartProducts) { cartProducts.sumOf { it.price * it.itemsCart } }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(SupperLite)
            ) {

                Column(
                    modifier = Modifier.fillMaxSize()
                ) {

                    CartHeader(cartProducts, totalPrice)

                    Spacer(Modifier.height(8.dp))
                    SelectAllRow(
                        isChecked = isChecked,
                        onCheckedChange = { isChecked = it },
                        onDeleteClick = {  },
                        onFavoriteClick = {  },
                        onShareClick = {  }
                    )

                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(
                            bottom = 120.dp
                        )
                    ) {
                        items(cartProducts, key = { it.id }) { product ->
                            Spacer(Modifier.height(8.dp))
                            SwipeToDeleteContainer(
                                item = product,
                                onDelete = { viewModel.deleteProduct(it) }
                            ) {
                                CartItemPreviewCard(
                                    cartProducts = product,
                                    increment = { viewModel.incrementProduct(it) },
                                    decrement = { viewModel.decrementProduct(it) },
                                    deleteId = { viewModel.deleteProduct(it) },
                                    favoriteId = { viewModel.favoriteProduct(it) },
                                    toggleSelected = { viewModel.toggleSelectProduct(it) },
                                )
                            }
                        }
                        item {
                            Spacer(Modifier.height(100.dp))
                        }
                    }
                }

                CartBottomPanel(
                    cartProducts,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(horizontal = 20.dp)
                        .padding(bottom = 90.dp)
                )
            }
        }
    }
}

@Composable
fun CartBottomPanel(
    cartProducts: List<ProductEntity>,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val selectedProducts = remember(cartProducts) {
        cartProducts.filter { it.isSelected }
    }
    val totalPrice =
        remember(selectedProducts) { selectedProducts.sumOf { it.price * it.itemsCart } }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MainBlueLight,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.weight(1.0f)
            ) {
                Text(
                    text = totalPrice.toPriceFormat(),
                    color = MainBlue,
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 16.sp
                )
                Row {
                    Text(
                        text = (totalPrice * 0.1).toInt().toPriceFormat(),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            textDecoration = TextDecoration.LineThrough
                        ),
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = " • ${selectedProducts.size} шт.",
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }

            Button(
                onClick = { Toast.makeText( context, "Офрмляем заказ", Toast.LENGTH_SHORT ).show() },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = MainBlue),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 2.dp),
                    text = "Войти и оформить",
                    color = MainWhite,
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}