package com.example.myapplication
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Dp

import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextTabRow(
    textTabs:@Composable () -> Unit ={},
    indicator:@Composable (tabPositions: List<TabPosition>) -> Unit ={},
    indicator2:@Composable () -> Unit ={},
) {
    Surface(
        color = Color.White,
        modifier = Modifier
            .width(TabWidth)
            .height(TabHeight),
        contentColor = Color.Yellow
    ) {
        val indicatorBox = @Composable{ it:List<TabPosition> -> indicator(it)}

    Layout(
        contents = listOf(textTabs,indicator2),
        modifier = Modifier
    ){
            (textTabsMeasures,indicatorMeasures,indicatorMeasures2),constraints->
//        val textTabsplaceable = textTabsMeasures.first().measure(constraints)
        val indicatorplaceable = indicatorMeasures.first().measure(constraints)
        val indicatorplaceable2 = indicatorMeasures2.first().measure(constraints)

        val textTabsplaceables = textTabsMeasures.map {
            val placeable = it.measure(constraints)
            placeable
        }

        val totalHeight = 56.dp.roundToPx()
        val totalWidth =300.dp.roundToPx()

        layout(totalWidth, totalHeight){
//            indicatorplaceable.place(0,0)
//            textTabsplaceable.place(0,0)
//            indicatorplaceable2.place(0,0)
            var left = 0
            var tabPositions = mutableListOf<TabPosition>()
            textTabsplaceables.forEach{
                it.placeRelative(left,0)
                tabPositions.add(TabPosition(left.toDp(),it.width.toDp()))
                left+=it.width
            }

//            (indicator(tabPositions))

        }
    }

    }

}

@Immutable
class TabPosition internal constructor(val left: Dp, val width: Dp) {
    val right: Dp get() = left + width

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TabPosition) return false

        if (left != other.left) return false
        if (width != other.width) return false

        return true
    }

    override fun hashCode(): Int {
        var result = left.hashCode()
        result = 31 * result + width.hashCode()
        return result
    }

    override fun toString(): String {
        return "TabPosition(left=$left, right=$right, width=$width)"
    }
}


