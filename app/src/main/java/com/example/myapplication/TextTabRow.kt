package com.example.myapplication
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
//import androidx.compose.material3.ScrollableTabData
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.Dp

import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextTabRow(
    allScreens: List<TextDestination>,
    onTabSelected: (TextDestination) -> Unit,
    currentScreen: TextDestination,
    indicator:@Composable (tabPositions: List<TabPosition>) -> Unit ={},
    indicator2:@Composable () -> Unit ={},
) {
    Surface(
        color = Color.White,
        contentColor = Color.Yellow
    ) {
//        val indicatorBox = @Composable{ it:List<TabPosition> -> indicator(it)}
        val textTabs = @Composable{
            allScreens.forEach { screen ->
                TextTab(
                    text = screen.route,
                    onSelected = { onTabSelected(screen) },
                    selected = currentScreen == screen
                )
            }
        }

        SubcomposeLayout(){
            constraints ->
            var textTabsplaceables = subcompose("textTabs",textTabs).map {
                val placeable = it.measure(constraints)
                placeable
            }

            var left = 0
            var tabPositions = mutableListOf<TabPosition>()
            textTabsplaceables.forEach{
                tabPositions.add(TabPosition(left.toDp(),it.width.toDp()))
                left+=it.width
            }

            val indicatorPlaceable = subcompose("indicator"){
                indicator(tabPositions)
            }.map { it.measure(constraints.copy(minWidth = 0)) }

            val totalHeight = 56.dp.roundToPx()
            val totalWidth =300.dp.roundToPx()

            layout(totalWidth, totalHeight){
                var tleft = 0
                textTabsplaceables.forEach{
                    it.placeRelative(tleft,0)
                    tleft+=it.width
                }

                indicatorPlaceable.forEach {
                    it.placeRelative(0, 0)
                }
            }
        }

//    Layout(
//        contents = listOf(textTabs,indicator2),
//        modifier = Modifier
//    ){
//            (textTabsMeasures,indicatorMeasures2),constraints->
//
//        val indicatorplaceable2 = indicatorMeasures2.first().measure(constraints)
//
//        val textTabsplaceables = textTabsMeasures.map {
//            val placeable = it.measure(constraints)
//            placeable
//        }
//
//        val totalHeight = 56.dp.roundToPx()
//        val totalWidth =300.dp.roundToPx()
//
//        layout(totalWidth, totalHeight){
////            indicatorplaceable.place(0,0)
////            textTabsplaceable.place(0,0)
//            val iw = indicatorplaceable2.width
//            val ih = indicatorplaceable2.height
//            indicatorplaceable2.place(totalWidth-iw,totalHeight-ih)
//
//            var left = 0
//            var tabPositions = mutableListOf<TabPosition>()
//            textTabsplaceables.forEach{
//                it.placeRelative(left,0)
//                tabPositions.add(TabPosition(left.toDp(),it.width.toDp()))
//                left+=it.width
//            }
//
//
//        }
//    }

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


