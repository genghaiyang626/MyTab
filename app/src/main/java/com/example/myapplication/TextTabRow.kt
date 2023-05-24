package com.example.myapplication
import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@SuppressLint("UnrememberedMutableState")
@Composable
fun TextTabRow(
    allScreens: List<TextDestination>,
    onTabSelected: (TextDestination) -> Unit,
    currentScreen: TextDestination,
    indicator:@Composable (tabPositions: List<TabPosition>) -> Unit ={},
    indicator2:@Composable () -> Unit ={},
) {

    val scrollState = rememberScrollState()

    Surface(
        color = Color.White,
        contentColor = Color.Yellow,
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

            var xOffset = 0
            //居中
//            xOffset = if(tabPositions[currentScreen.ordinal].left < 150.dp){
//                -scroll
//            }else if (tabPositions[currentScreen.ordinal].left.roundToPx() <= 200 +paddingWidth ){
//                150- tabPositions[currentScreen.ordinal].left.roundToPx()
//            }else{
//                -scroll
//            }

            // 根据可滚动的距离来计算滚动位置
            val scroll = scrollState.value.coerceIn(0, left-totalWidth+paddingWidth*2)
            // 根据滚动位置得到实际组件偏移量
             xOffset = -scroll

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


