package com.app.dental_first.ui.screens.components

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.Decoy
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.app.dental_first.R
import com.app.dental_first.core.toPriceFormat
import com.app.dental_first.data.local.entity.CatalogEntity
import com.app.dental_first.data.local.entity.ProductEntity
import com.app.dental_first.ui.theme.LightBlack
import com.app.dental_first.ui.theme.LightGray
import com.app.dental_first.ui.theme.MainBlack
import com.app.dental_first.ui.theme.MainBlue
import com.app.dental_first.ui.theme.MainBlue2
import com.app.dental_first.ui.theme.MainBlueLight
import com.app.dental_first.ui.theme.MainGray
import com.app.dental_first.ui.theme.MainRed
import com.app.dental_first.ui.theme.MainSilver
import com.app.dental_first.ui.theme.MainWhite
import com.app.dental_first.ui.theme.SecondBlue

@Composable
fun CatalogCard(
    item: CatalogEntity,
    onClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = { onClick(item.id) }
    ) {
        Column(
            modifier = Modifier.background(MainWhite)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.image)
                    .crossfade(true)
                    .build(),

                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .padding(top = 32.dp),

                placeholder = painterResource(R.drawable.img_product),
                error = painterResource(R.drawable.img_product),
                fallback = painterResource(R.drawable.img_product)
            )

            Column(modifier = Modifier.padding(24.dp)) {
                Text(
                    text = item.name,
                    color = MainBlack,
                    style = MaterialTheme.typography.bodyLarge
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Более 1200 товаров",
                        color = MainSilver,
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 16.sp
                    )
                    Spacer(Modifier.width(8.dp))
                    Icon(
                        modifier = Modifier.size(12.dp),
                        painter = painterResource(R.drawable.ic_arr_right),
                        contentDescription = null,
                        tint = MainSilver
                    )
                }
            }
        }
    }
}

@Composable
fun CollapsedCatalogStack(
    items: List<CatalogEntity>,
    expanded: () -> Unit
) {

    val visibleItems = items.take(3)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(340.dp)
            .clickable( onClick = { expanded() })
    ) {

        visibleItems.forEachIndexed { index, item ->

            val scale = 1f - (index * 0.05f)
            val offsetY = index * 20
            val alpha = if (index == 0) 1f else 1f - index * 0.2f

            CatalogCardElevation(
                item = item,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        translationY = offsetY.dp.toPx()
                        this.alpha = alpha
                    }
                    .zIndex((10 - index).toFloat())
            )
        }
    }
}

@Composable
fun CatalogCardElevation(
    item: CatalogEntity,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Column(
            modifier = Modifier.background(MainWhite)
        ) {

            AsyncImage(
                model = item.image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .padding(top = 24.dp),
                placeholder = painterResource(R.drawable.img_product),
                error = painterResource(R.drawable.img_product)
            )

            Column(Modifier.padding(20.dp)) {

                Text(
                    text = item.name,
                    color = MainBlack,
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = "Более 1200 товаров",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun ProductCard(
    product: ProductEntity,
    onClickAddProduct: (ProductEntity) -> Unit,
    onClickMinusProduct: (ProductEntity) -> Unit,
    onClickFavorite: (ProductEntity) -> Unit,
) {
    val context = LocalContext.current
    val isNew = true
    val extraCount = 2
    val oldPrice = (product.price * 1.1).toInt()
    val discountPercent = 10
    val rating = 4.8
    val reviewsCount = 205
    val inCompare = false

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Box {

                AsyncImage(
                    model = product.image,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentScale = ContentScale.Fit,
                    placeholder = painterResource(R.drawable.img_def_product),
                    error = painterResource(R.drawable.img_def_product)
                )

                Row(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {

                    if (isNew) {
                        SmallBadge(
                            text = "NEW",
                            background = MainBlue
                        )
                    }

                    SmallBadge(
                        text = "+$extraCount",
                        background = LightGray,
                        textColor = Color.Black
                    )
                }

                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                    tint = if (product.isFavorite) MainRed else Color.LightGray,
                    modifier = Modifier
                        .size(22.dp)
                        .align(Alignment.TopEnd)
                        .padding(top = 4.dp)
                        .clickable(onClick = { onClickFavorite(product) })
                )
            }

            Spacer(Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "ID: ${product.id}",
                    style = MaterialTheme.typography.titleSmall,
                    color = MainSilver,
                    fontSize = 12.sp
                )

                Spacer(Modifier.width(6.dp))

                Icon(
                    painter = painterResource(R.drawable.ic_copy_id),
                    contentDescription = null,
                    tint = MainSilver,
                    modifier = Modifier
                        .size(16.dp)
                        .clickable(
                            onClick = {
                                Toast.makeText(context, "Скопировано", Toast.LENGTH_SHORT).show()
                            }
                        )
                )

                Spacer(Modifier.weight(1f))

                Icon(
                    painter = painterResource(R.drawable.ic_share),
                    contentDescription = null,
                    tint = MainGray,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(Modifier.height(6.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = product.price.toPriceFormat(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = MainBlue
                )

                Spacer(Modifier.width(8.dp))

                Text(
                    text = oldPrice.toPriceFormat(),
                    style = MaterialTheme.typography.bodySmall.copy(
                        textDecoration = TextDecoration.LineThrough
                    ),
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                Spacer(Modifier.weight(1f))

                SmallBadge(
                    text = "-$discountPercent%",
                    background = MainBlue,
                )
            }

            Spacer(Modifier.height(6.dp))

            Text(
                text = product.description,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                color = MainBlack,
                style = MaterialTheme.typography.titleSmall,
                fontSize = 12.sp
            )

            Spacer(Modifier.height(6.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color(0xFFFF9800),
                    modifier = Modifier.size(16.dp)
                )

                Spacer(Modifier.width(4.dp))

                Text(
                    text = "$rating",
                    style = MaterialTheme.typography.titleSmall,
                    color = MainBlack,
                    fontSize = 12.sp
                )

                Spacer(Modifier.width(4.dp))

                Text(
                    text = "• $reviewsCount оценок",
                    style = MaterialTheme.typography.titleSmall,
                    color = MainSilver,
                    fontSize = 12.sp
                )
            }

            Spacer(Modifier.height(10.dp))

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (inCompare)
                        Color(0xFFECECEC)
                    else
                        SecondBlue
                ),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text(
                    if (inCompare) "В сравнении" else "Сравнить",
                    color = if (inCompare) Color.DarkGray else MainBlue2,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }

            Spacer(Modifier.height(6.dp))

            if (product.itemsCart == 0) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            MainBlueLight,
                            RoundedCornerShape(14.dp)
                        )
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "В корзину",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 16.sp,
                        color = MainBlue,
                        modifier = Modifier.clickable(
                            onClick = { onClickAddProduct(product) }
                        )
                    )
                }

            } else {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            MainBlueLight,
                            RoundedCornerShape(14.dp)
                        )
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "-",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 16.sp,
                        color = MainBlue,
                        modifier = Modifier.clickable(
                            onClick = { onClickMinusProduct(product) }
                        )
                    )

                    Text(
                        "${product.itemsCart} шт.",
                        fontWeight = FontWeight.Bold,
                        color = LightBlack,
                        style = MaterialTheme.typography.bodyMedium,
                    )

                    Text(
                        text = "+",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 16.sp,
                        color = MainBlue,
                        modifier = Modifier.clickable(
                            onClick = { onClickAddProduct(product) }
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun SmallBadge(
    text: String,
    background: Color,
    textColor: Color = Color.White
) {
    Box(
        modifier = Modifier
            .background(background, RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 10.sp,
            style = MaterialTheme.typography.titleSmall,
        )
    }
}

@Composable
fun CartItemPreviewCard(
    cartProducts: ProductEntity,
    increment: (ProductEntity) -> Unit,
    decrement: (ProductEntity) -> Unit,
    deleteId: (ProductEntity) -> Unit,
    favoriteId: (ProductEntity) -> Unit,
    toggleSelected: (ProductEntity) -> Unit,
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MainWhite),
        border = BorderStroke(1.dp, LightGray),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Row {
                Box {
                    Image(
                        painter = painterResource(R.drawable.img_def_product),
                        contentDescription = null,
                        modifier = Modifier
                            .size(116.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White)
                            .padding(12.dp)
                    )
                    AnimatedCheckBox(
                        checked = cartProducts.isSelected,
                        onCheckedChange = { toggleSelected(cartProducts) },
                        modifier = Modifier.align(Alignment.TopStart)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier
                    .weight(1f)
                    .padding(end = 12.dp)) {
                    Text(
                        text = "ID: ${cartProducts.id}",
                        style = MaterialTheme.typography.titleSmall,
                        color = MainSilver,
                        fontSize = 12.sp,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = cartProducts.description,
                        style = MaterialTheme.typography.titleSmall,
                        fontSize = 12.sp,
                        color = MainBlack,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = cartProducts.price.toPriceFormat(),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MainBlack,
                            fontSize = 16.sp,
                        )
                        Spacer(Modifier.width(8.dp))

                        Text(
                            text = (cartProducts.price*0.1).toInt().toPriceFormat(),
                            style = MaterialTheme.typography.bodySmall.copy(
                                textDecoration = TextDecoration.LineThrough
                            ),
                            fontSize = 12.sp,
                            color = Color.Gray
                        )

                        Spacer(Modifier.width(8.dp))

                        SmallBadge(
                            text = "-10%",
                            background = MainBlue,
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(R.drawable.ic_star),
                            contentDescription = null,
                            tint = Color(0xFFFF8A00),
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "5.0",
                            style = MaterialTheme.typography.titleSmall,
                            color = MainBlack,
                            fontSize = 12.sp,
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "• 136 оценок",
                            color = MainSilver,
                            style = MaterialTheme.typography.titleSmall,
                            fontSize = 12.sp,
                        )
                    }
                }
                Column( modifier = Modifier.size(15.dp) ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_hor_dot),
                        contentDescription = null,
                        tint = MainSilver,
                        modifier = Modifier
                            .size(15.dp)
                            .padding(end = 2.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row( verticalAlignment = Alignment.CenterVertically ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .background(
                            Color(0xFFE9EAEC),
                            RoundedCornerShape(14.dp)
                        ).clickable(onClick = { deleteId(cartProducts) }),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_tank),
                        contentDescription = null,
                        tint = Color.Gray
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .background(
                            Color(0xFFE9EAEC),
                            RoundedCornerShape(14.dp)
                        )
                        .clickable(onClick = { favoriteId(cartProducts) }),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_like),
                        contentDescription = null,
                        tint = if (cartProducts.isFavorite) Color.Red
                        else Color.Gray
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .background(
                            Color(0xFFDDE2FF),
                            RoundedCornerShape(14.dp)
                        )
                        .clickable(onClick = { decrement(cartProducts) }),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "-",
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF3B4CCA)
                    )
                }
                Text(
                    text = "${cartProducts.itemsCart} шт.",
                    modifier = Modifier.padding(horizontal = 10.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MainBlack,
                    fontSize = 14.sp
                )
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .background(
                            Color(0xFFDDE2FF),
                            RoundedCornerShape(14.dp)
                        )
                        .clickable(onClick = { increment(cartProducts) }),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "+",
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF3B4CCA)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = { },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFDDE2FF),
                        contentColor = Color(0xFF3B4CCA)
                    )
                ) {
                    Text(
                        text = "Купить",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MainBlue,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

