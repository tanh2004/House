package com.example.asm.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.asm.HomeActivity
import com.example.asm.R
import com.example.asm.models.UserInfo

data class TabBarItem(
    val title: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
)

class Home {

    @Composable
    fun Container(saveUserInfo: (UserInfo) -> Unit,
                  goToScreen: (String) -> Unit){
        MainTabs(
            goToScreen = {goToScreen(it)},saveUserInfo = {saveUserInfo(it)})
    }



    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun MainTabs(goToScreen: (String) -> Unit,
        saveUserInfo: (UserInfo) -> Unit) {

        // setting up the individual tabs
        val homeTab = TabBarItem(
            title = "Home",
            selectedIcon = R.drawable.home_1,
            unselectedIcon = R.drawable.home_2
        )
        val alertsTab = TabBarItem(
            title = "Alerts",
            selectedIcon = R.drawable.notication_1,
            unselectedIcon = R.drawable.notication_2
        )
        val settingsTab = TabBarItem(
            title = "Settings",
            selectedIcon = R.drawable.marker_1,
            unselectedIcon = R.drawable.marker_2
        )
        val moreTab = TabBarItem(
            title = "More",
            selectedIcon = R.drawable.person_1,
            unselectedIcon = R.drawable.person_2
        )
        // creating a list of all the tabs
        val tabBarItems = listOf(homeTab, alertsTab, settingsTab, moreTab)
        // creating our navController
        val navController = rememberNavController()

        //Khai báo các màn hình
        val noticationScreen = Notication()
        val homeScreen = TrangChu()
        Scaffold(bottomBar = { TabView(tabBarItems, navController) }) {
            NavHost(navController = navController, startDestination = homeTab.title) {
                composable(homeTab.title) {
                    homeScreen.Container(goToScreen, saveUserInfo )
                }
//                Button(onClick = {
//                saveUserInfo(UserInfo(null, null, null, null))
//                }) {
//                Text(text = "Logout")
//                }
                composable(alertsTab.title) {
                    noticationScreen.Container()
                }
                composable(settingsTab.title) {
                    Text(settingsTab.title)
                }
                composable(moreTab.title) {
                    Text(text = "Hello")
                }
            }
        }
    }

    @Composable
    fun TabView(tabBarItems: List<TabBarItem>, navController: NavController) {
        var selectedTabIndex by rememberSaveable {
            mutableIntStateOf(0)
        }
        NavigationBar (
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color.White
        ){
            tabBarItems.forEachIndexed { index, tabBarItem ->
                NavigationBarItem(
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                        navController.navigate(tabBarItem.title)
                    },
                    icon = {
                        TabBarIconView(
                            isSelected = selectedTabIndex == index,
                            selectedIcon = tabBarItem.selectedIcon,
                            unselectedIcon = tabBarItem.unselectedIcon,
                            title = tabBarItem.title,
                        )
                    },
                    label = { Text(tabBarItem.title) })

            }
        }
    }


    @Composable
    fun TabBarIconView(
        isSelected: Boolean,
        selectedIcon: Int,
        unselectedIcon: Int,
        title: String,
    ) {
        Image(
            painter = painterResource(id = if (isSelected) selectedIcon else unselectedIcon),
            modifier = Modifier
                .width(24.dp)
                .height(24.dp),
            contentDescription = title
        )
    }
}


//            Text(text = "Home")
//            Button(onClick = {
//                val value = 123
//                goToScreen("detail/$value")
//            }) {
//                Text(text = "Detail")
//            }
//            Button(onClick = {
//                saveUserInfo(UserInfo(null, null, null, null))
//            }) {
//                Text(text = "Logout")
//            }