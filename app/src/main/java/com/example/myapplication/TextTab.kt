package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.LightSlateGray

@OptIn(ExperimentalTextApi::class)
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
//            .fillMaxWidth()
//            .wrapContentWidth(Alignment.Start)
            .height(TabHeight)
            .width(boxwidth(text.length))
//            .padding(horizontal = 8.dp)
//            .alpha(1f)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(TabHeight)
                .width(boxwidth(text.length))
//                .selectable(
//                    selected = selected,
//                    onClick = onSelected,
////                    role = Role.Tab
//                )

        ) {
            if (selected) {
                Text(
                    text, fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp, color = Color.Black, modifier = Modifier.clickable(enabled = true,onClick = onSelected,onClickLabel= "选中")
                )
            } else {
                val colorStops = arrayOf(
                    0.0f to Color.Black ,
                    1.0f to Color.Black
//                    0.0f to Color.Yellow,
//                    0.2f to Color.Red,
//                    1f to Color.Blue
                )

                Text(text, fontSize = 14.sp, color = LightSlateGray,
                    style= TextStyle(
                        brush = Brush.horizontalGradient(colorStops = colorStops),
                        alpha = 0.6f
                    ),
                    modifier = Modifier.clickable(enabled = true,onClick = onSelected,onClickLabel= "没选中")
                )
            }
        }
    }


}

val TabHeight = 56.dp
//val TabWidth = 300.dp