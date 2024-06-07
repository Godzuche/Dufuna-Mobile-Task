package com.caresaas.caresaasmobiletask.core.designsystem.component

//import androidx.compose.animation.core.Spring
//import androidx.compose.animation.core.animateDp
//import androidx.compose.animation.core.spring
//import androidx.compose.animation.core.updateTransition
//import androidx.compose.material3.TabPosition
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import com.caresaas.caresaasmobiletask.navigation.Screen
//
//@Composable
//private fun ExploreTabIndicator(
//    tabPositions: List<TabPosition>,
//    currentExploreTab: Screen,
//) {
//    val transition = updateTransition(
//        currentExploreTab,
//        label = "Tab indicator"
//    )
//
//
//    val indicatorLeft by transition.animateDp(
//        transitionSpec = {
//            if (initialState < targetState) {
//                // Indicator moves to the right.
//                // Low stiffness spring for the left edge so it moves slower than the right edge.
//                spring(stiffness = Spring.StiffnessVeryLow)
//            } else {
//                // Indicator moves to the left.
//                // Medium stiffness spring for the left edge so it moves faster than the right edge.
//                spring(stiffness = Spring.StiffnessMedium)
//            }
//        },
//        label = "Indicator left"
//    ) { exploreTab ->
//        tabPositions[exploreTab.ordinal].left
//    }
//    val indicatorRight by transition.animateDp(
//        transitionSpec = {
//            if (initialState > targetState) {
//                // Indicator moves to the left.
//                // Low stiffness spring for the right edge so it moves slower than the left edge.
//                spring(stiffness = Spring.StiffnessVeryLow)
//            } else {
//                // Indicator moves to the right
//                // Medium stiffness spring for the right edge so it moves faster than the left edge.
//                spring(stiffness = Spring.StiffnessMedium)
//            }
//        },
//        label = "Indicator right"
//    ) { exploreTab ->
//        tabPositions[exploreTab.ordinal].right
//    }
//    /*    val color by transition.animateColor(
//            label = "Border color"
//        ) { page ->
//            if (page == TabPage.Home) Purple700 else Green800
//        }*/
//    Box(
//        Modifier
//            .fillMaxSize()
//            .wrapContentSize(align = Alignment.BottomStart)
//            .offset(x = indicatorLeft)
//            .width(indicatorRight - indicatorLeft)
//            .padding(4.dp)
//            .fillMaxSize()
//            .border(
//                BorderStroke(2.dp, /*color*/ MaterialTheme.colorScheme.primaryContainer),
//                RoundedCornerShape(4.dp)
//            )
////            .background(color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f))
//    )
//}
