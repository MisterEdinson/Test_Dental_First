package com.app.dental_first.ui.screens.actions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
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
import com.app.dental_first.R
import com.app.dental_first.SetSystemBarsColor
import com.app.dental_first.ui.theme.MainBlue

@Composable
fun ActionsScreen (navController: NavController){
    SetSystemBarsColor(
        color = MainBlue,
        darkIcons = true
    )
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
                painter = painterResource(R.drawable.logo),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(80.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Actions",
                color = Color.White,
                fontSize = 28.sp,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}