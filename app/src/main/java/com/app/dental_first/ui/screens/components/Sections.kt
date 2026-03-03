package com.app.dental_first.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.app.dental_first.ui.theme.MainBlack
import com.app.dental_first.ui.theme.MainGray
import com.app.dental_first.ui.theme.MainSilver
import com.app.dental_first.ui.theme.SecondBlue
import com.app.dental_first.ui.theme.SupperLite

@Composable
fun ProductsHeader(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .size(34.dp)
                .background(Color(0xFFF2F2F2), CircleShape)
        ) {
            Icon(
                Icons.Default.ArrowBack,
                null,
                tint = MainBlack)
        }

        Spacer(Modifier.width(18.dp))

        Column {
            Text(
                "Пример страницы с товарами",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 18.sp,
                color = MainBlack
            )
            Text(
                "Зубная паста (Пример)",
                color = MainSilver,
                style = MaterialTheme.typography.bodySmall,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun ProductsTabs() {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        listOf("Товары", "Категории", "Бренды", "Новое", "Популярное")
            .forEachIndexed { index, title ->
                val selected = index == 0
                Box(
                    modifier = Modifier
                        .background(
                            if (selected) SecondBlue
                            else SupperLite,
                            RoundedCornerShape(16.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleSmall,
                        fontSize = 14.sp,
                        color = MainGray
                    )
                }
            }
    }
}

@Composable
fun ProductsFilterRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(com.app.dental_first.R.drawable.ic_grid_column),
                null,
                modifier = Modifier.size(38.dp),
                tint = Color.Unspecified
            )
            Icon(
                painter = painterResource(com.app.dental_first.R.drawable.ic_grid_row),
                null,
                modifier = Modifier.size(38.dp),
                tint = Color.Unspecified
            )
            Spacer(Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .background(Color(0xFFF3F3F3), RoundedCornerShape(12.dp))
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Text(
                    text = "Популярные ",
                    style = MaterialTheme.typography.titleSmall,
                    fontSize = 14.sp,
                    color = MainBlack
                )
            }
            Spacer(Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .background(Color(0xFFF3F3F3), RoundedCornerShape(12.dp))
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Text(
                    text = "Фильтры ⚙",
                    style = MaterialTheme.typography.titleSmall,
                    fontSize = 14.sp,
                    color = MainBlack
                )
            }
        }
    }
}