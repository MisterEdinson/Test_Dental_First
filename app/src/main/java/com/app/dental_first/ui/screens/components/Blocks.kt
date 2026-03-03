package com.app.dental_first.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.dental_first.R
import com.app.dental_first.core.toPriceFormat
import com.app.dental_first.data.local.entity.ProductEntity
import com.app.dental_first.ui.theme.MainBlack
import com.app.dental_first.ui.theme.MainBlue
import com.app.dental_first.ui.theme.MainGray

@Composable
fun EmptyCartScreen(
    onGoToCatalog: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBlue),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 32.dp)
        ) {

            Icon(
                painter = painterResource(R.drawable.img_box),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(80.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Корзина",
                color = Color.White,
                fontSize = 28.sp,
                style = MaterialTheme.typography.bodyLarge,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Сейчас в вашей корзине пусто, перейдите в каталог, добавьте товары в корзину и возвращайтесь чтобы оформить заказ",
                color = Color.White,
                fontSize = 16.sp,
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onGoToCatalog,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Каталог товаров",
                    color = MainBlue,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun CartHeader(
    products: List<ProductEntity>,
    totalPrice: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Корзина",
            style = MaterialTheme.typography.titleLarge,
            fontSize = 28.sp,
            color = MainBlack
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "${products.sumOf { it.itemsCart }} всего товаров • ${totalPrice.toPriceFormat()}",
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 14.sp,
            color = MainGray
        )
    }
}

@Composable
fun SelectAllRow(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onDeleteClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onCheckedChange(!isChecked) }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        AnimatedCheckBox(
            checked = isChecked,
            onCheckedChange = { onCheckedChange(!isChecked) },
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text = "Выбрать все",
            style = MaterialTheme.typography.titleSmall,
            color = MainBlack,
            fontSize = 14.sp,
            modifier = Modifier.weight(1f)
        )

        ActionCircleButton(
            painter = painterResource(R.drawable.ic_tank),
            onClick = onDeleteClick
        )

        Spacer(Modifier.width(8.dp))

        ActionCircleButton(
            painter = painterResource(R.drawable.ic_like),
            onClick = onFavoriteClick
        )

        Spacer(Modifier.width(8.dp))

        ActionCircleButton(
            painter = painterResource(R.drawable.ic_arrow),
            onClick = onShareClick
        )
    }
}

@Composable
private fun ActionCircleButton(
    painter: Painter,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(Color(0xFFF1F1F1))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painter,
            contentDescription = null,
            tint = Color.Gray
        )
    }
}