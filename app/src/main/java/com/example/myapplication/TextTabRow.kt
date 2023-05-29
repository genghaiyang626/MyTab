package com.example.myapplication
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.log

@SuppressLint("UnrememberedMutableState")
@Composable
fun TextTabRow(
    allScreens: List<TextDestination>,
    onTabSelected: (TextDestination) -> Unit,
    currentScreen: TextDestination,
    indicator:@Composable (tabPositions: List<TabPosition>) -> Unit ={},
    indicator2:@Composable () -> Unit ={},
) {
    var scrollValue by rememberSaveable{ mutableStateOf(0.0f) }

    if (currentScreen.ordinal != TextDestination.old_ordinal){
        scrollValue = when(currentScreen.ordinal){
            3 -> 100.0f
            4 -> 200.0f
            5 -> 300f
            6 -> 400f
            7 -> 400f
            else -> 0.0f
        }
//        Log.e("测试","是否执行")
    }
    val scrollState = rememberScrollableState{
            delta ->
         scrollValue+=delta
        delta
    }

    Surface(
        color = Color.White,
        contentColor = Color.White,
        modifier = Modifier
            .scrollable(scrollState,Orientation.Horizontal, reverseDirection = true)
    ) {
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
            val textTabsplaceables = subcompose("textTabs",textTabs).map {
                val placeable = it.measure(constraints)//.copy(maxWidth = Constraints.Infinity)
                placeable
            }

            var left = 0
            val tabPositions = mutableListOf<TabPosition>()
            textTabsplaceables.forEach{
                tabPositions.add(TabPosition(left.toDp(),it.width.toDp()))
                left+=it.width
            }

            val indicatorPlaceable = subcompose("indicator"){
                indicator(tabPositions)
            }.map { it.measure(constraints.copy(minWidth = 0)) }

            val indicator2Placeable = subcompose("indicator2",indicator2).map {
                val placeable = it.measure(constraints.copy(minWidth = 0))
                placeable
            }

            val totalHeight = 56.dp.roundToPx()
            val totalWidth = 300.dp.roundToPx() // left.coerceAtMost(300.dp.roundToPx())
            val paddingWidth = 10.dp.roundToPx()

            // 根据可滚动的距离来计算滚动位置
            val scroll = scrollValue.coerceIn(0F, (left-totalWidth+paddingWidth*2).toFloat()).toInt()

            // 根据滚动位置得到实际组件偏移量
            val xOffset  = -scroll

            layout(totalWidth, totalHeight){
                indicatorPlaceable.forEach {
                    it.placeRelative(paddingWidth+xOffset, 0)
                }

                var tleft = paddingWidth
                textTabsplaceables.forEach{
                    it.placeRelative(tleft+xOffset,0)
                    tleft+=it.width
                }

                indicator2Placeable.forEach {
                    it.placeRelative(totalWidth-it.width, 0)
                }
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


