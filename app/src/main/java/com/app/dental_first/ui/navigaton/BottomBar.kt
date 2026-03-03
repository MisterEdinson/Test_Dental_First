package com.app.dental_first.ui.navigaton

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.app.dental_first.ui.theme.LightGray
import com.app.dental_first.ui.theme.MainBlue
import com.app.dental_first.ui.theme.MainGray
import com.app.dental_first.ui.theme.SupperLite

@SuppressLint("ContextCastToActivity")
@Composable
fun BottomBar(
    navController: NavHostController,
    onMenuClick: () -> Unit
) {
    val screens = listOf(
        Screen.HomeScreen,
        Screen.CatalogScreen,
        Screen.ActionsScreen,
        Screen.CartScreen,
        Screen.ProfileScreen,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(bottom = 0.dp),
        contentAlignment = Alignment.Center
    ) {
        NavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = LightGray,
                    shape = RoundedCornerShape(20.dp)
                )
                .clip(RoundedCornerShape(20.dp)),
            containerColor = SupperLite
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                screens.forEach { screen ->
                    AddItem(
                        screen,
                        currentDestination,
                        navController,
                        onMenuClick = { onMenuClick() }
                    )
                }
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: Screen,
    currentDestination: NavDestination?,
    navController: NavHostController,
    onMenuClick: () -> Unit
) {
    val isSelected = currentDestination?.route == screen.route

    NavigationBarItem(
        label = {
            screen.title?.let { title ->
                Text(
                    text = stringResource(id = title),
                    color = if (isSelected) MainBlue else MainGray
                )
            }
        },
        icon = {
            screen.icon?.let { icon ->
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = screen.title?.let { stringResource(it) },
                    tint = if (isSelected) MainBlue else MainGray
                )
            }
        },
        selected = isSelected,
        onClick = {
            if (screen.route == "menu_screen") {
                onMenuClick()
            } else {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            }
        },
        colors = NavigationBarItemDefaults.colors(
            indicatorColor = Color.Transparent
        )
    )
}