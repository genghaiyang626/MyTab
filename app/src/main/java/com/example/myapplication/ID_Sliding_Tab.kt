package com.example.myapplication


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup

import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.Scaffold

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment


import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Id_sliding_tab(modifier: Modifier=Modifier){
     val navController = rememberNavController()

    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination

    val currentScreen = TextTabRowScreens.find { it.route == currentDestination?.route } ?: Chosen


    Scaffold(
        topBar = {
            TextTabRow(
                textTabs = {
                    TextTabs(
                        allScreens = TextTabRowScreens,
                        onTabSelected = { newScreen ->
                            navController
                                .navigateSingleTopTo(newScreen.route)
                        },
                        currentScreen = currentScreen
                    )
                },
                indicator = { tabPositions -> TextTabIndicator(tabPositions,currentScreen) },
                indicator2 = { TextTabIndicator2() }
            )
        }
    
    ) { innerPadding ->
         NavHost(
             navController = navController,
             startDestination = Chosen.route,
             modifier = modifier.padding(innerPadding)
         ) {
             composable(route = Chosen.route) {
                 Chosenes()
             }
             composable(route = Boy.route) {
                 Chosenes2()
             }
             composable(route = Girl.route) {
                 Chosenes3()
             }
             composable(route = Book.route) {
                 Chosenes4()
             }
             composable(route = HuiYuan.route) {
                 Chosenes4()
             }
             composable(route = Free.route) {
                 Chosenes4()
             }
             composable(route = Listen.route) {
                 Chosenes4()
             }
             composable(route = Comic.route) {
                 Chosenes4()
//                 TabRow(selectedTabIndex = ) {
//
//                 }
             }
         }
     }

}

@Composable
fun TextTabs(
    allScreens: List<TextDestination>,
    onTabSelected: (TextDestination) -> Unit,
    currentScreen: TextDestination
) {
    val scrollState = rememberScrollState()
    Box(
        Modifier
            .width(TabWidth2)
            .height(TabHeight2)
            .background(Color.Transparent),
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .selectableGroup()
                .background(color = Color.Transparent)
                .width(TabWidth2)
                .scrollable(scrollState, Orientation.Horizontal, reverseDirection = true)
                .layout { measurable, constraints ->
                    // 约束中默认最大宽度为父组件所允许的最大宽度，此处为屏幕宽度
                    // 将最大宽度设置为无限大
                    val childConstraints = constraints.copy(
                        maxWidth = Constraints.Infinity
                    )
                    // 使用新的约束进行组件测量
                    val placeable = measurable.measure(childConstraints)
                    // 计算当前组件宽度与父组件所允许的最大宽度中取一个最小值
                    // 如果组件超出屏幕，此时width为屏幕宽度。如果没有超出，则为组件本文宽度
                    val width = placeable.width.coerceAtMost(constraints.maxWidth)
                    // 计算当前组件高度与父组件所允许的最大高度中取一个最小值
                    val height = placeable.height.coerceAtMost(constraints.maxHeight)
                    // 计算可滚动的距离
                    val scrollDistance = placeable.width - width
                    // 主动设置组件的宽高
                    layout(width, height) {
                        // 根据可滚动的距离来计算滚动位置
                        val scroll = scrollState.value.coerceIn(0, scrollDistance)
                        // 根据滚动位置得到实际组件偏移量
                        val xOffset = -scroll
                        // 对组件内容完成布局
                        placeable.placeRelativeWithLayer(xOffset, 0)
                    }
                }
        ) {
            allScreens.forEach { screen ->
                TextTab(
                    text = screen.route,
                    onSelected = { onTabSelected(screen) },
                    selected = currentScreen == screen
                )

            }
        }
    }
}

@Composable
fun TextTabIndicator(
    tabPositions: List<TabPosition>,
    currentScreen: TextDestination
) {
    Box(
        modifier = Modifier
//            .fillMaxSize()
            .wrapContentWidth(Alignment.Start)
            .size(50.dp)
//            .background(Color.Transparent)
            .border(2.dp, Color.Red),
        contentAlignment = Alignment.BottomEnd,
    ) {
        Text(text = "Test", color = Color.Black)
    }
}

@Composable
fun TextTabIndicator2() {
    Box(
        modifier = Modifier
            .wrapContentWidth(Alignment.End)
            .size(50.dp)
            .background(Color.Transparent)
            .border(2.dp, Color.Cyan),
        contentAlignment = Alignment.BottomEnd,
    ) {
        Text(text = "ME", color = Color.Black)
    }
}

@Composable
fun Chosenes(){
    Text(text = "第一页")
}

@Composable
fun Chosenes2(){
    Text(text = "第二页")
}

@Composable
fun Chosenes3(){
    Text(text = "第三页")
}

@Composable
fun Chosenes4(){
    Text(text = "其它内容")
}
//确保返回堆栈顶部最多只有给定目的地的一个副本
fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true }


@Preview(showBackground = false)
@Composable
fun MyTabPreview(){
    Id_sliding_tab()
}


private val TabHeight2 = 56.dp
private val TabWidth2 = 300.dp

