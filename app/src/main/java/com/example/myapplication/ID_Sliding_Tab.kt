package com.example.myapplication


import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup

import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.Scaffold
import androidx.compose.material3.TabRow

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
                allScreens = TextTabRowScreens,
                onTabSelected = { newScreen ->
                    navController
                        .navigateSingleTopTo(newScreen.route)
                },
                currentScreen =currentScreen,
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
        ) {
            allScreens.forEach { screen ->
                TextTab(
                    text = screen.route,
                    onSelected = { onTabSelected(screen) },
                    selected = currentScreen == screen
                )
//                TabRow(selectedTabIndex = ) {
//
//                }
            }
        }
    }
}

@Composable
fun TextTabIndicator(
    tabPositions: List<TabPosition>,
    currentScreen: TextDestination
) {
//    val indicatorLeft = tabPositions[currentScreen.ordinal].left
//    val indicatorRight = tabPositions[currentScreen.ordinal].right

    val transition = updateTransition(currentScreen, label = "Tab indicator")
    val indicatorLeft by transition.animateDp(transitionSpec = { spring(stiffness = Spring.StiffnessVeryLow)}, label = "Indicator left") { page ->
        tabPositions[page.ordinal].left
    }
    val indicatorRight by transition.animateDp(transitionSpec = { spring(stiffness = Spring.StiffnessVeryLow)}, label = "Indicator right") { page ->
        tabPositions[page.ordinal].right
    }

    Box(
        modifier = Modifier
//            .fillMaxSize()
            .wrapContentWidth(Alignment.Start)
            .height(50.dp)
            .offset(x = indicatorLeft)
            .width(indicatorRight - indicatorLeft)
//            .background(Color.Transparent)
            .border(2.dp, Color.Red),
        contentAlignment = Alignment.BottomEnd,
    ) {
//        Text(text = "Test", color = Color.Black)
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

