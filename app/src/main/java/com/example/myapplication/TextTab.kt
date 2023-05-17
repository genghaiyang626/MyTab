package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.LightSlateGray

@Composable
fun TextTab(
    text: String,
    onSelected: () -> Unit,
    selected: Boolean
) {
    val boxwidth: (Int) -> Dp = { length: Int ->
        if (length > 2) {
            80.dp
        } else {
            50.dp
        }
    }

    Box(
        modifier = Modifier
            .background(Color.Transparent)
            //            .border(1.dp, Color.LightGray)
            .height(TabHeight)
            .width(boxwidth(text.length))
//            .padding(horizontal = 8.dp)
            .alpha(0.8f)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(TabHeight)
                .width(boxwidth(text.length))
                .selectable(
                    selected = selected,
                    onClick = onSelected,
                    role = Role.Tab
                )

        ) {
            if (selected) {
                Text(
                    text, fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp, color = Color.Black,
                )
            } else {
                Text(text, fontSize = 14.sp, color = LightSlateGray)
            }
        }
    }


}

val TabHeight = 56.dp
val TabWidth = 300.dp