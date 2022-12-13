package lamarao.jose.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import jose.newsapp.R
import lamarao.jose.newsapp.repositories.datastore.DatastoreRepository
import lamarao.jose.newsapp.ui.news.NewsScreen
import lamarao.jose.newsapp.ui.settings.SettingsScreen
import lamarao.jose.newsapp.ui.theme.NewsAppTheme
import lamarao.jose.newsapp.ui.util.Screens
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var datastoreRepository: DatastoreRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme(datastoreRepository = datastoreRepository, content = {
                window.statusBarColor = colors.primary.toArgb()
                val navController = rememberNavController()
                Scaffold(
                    topBar = {
                        TopAppBar(
                            content = {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = stringResource(id = R.string.app_name),
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily.SansSerif,
                                    style = MaterialTheme.typography.h3,
                                    color = colors.primary
                                )
                            },
                            backgroundColor = colors.primaryVariant,
                            elevation = 0.dp
                        )
                    },
                    bottomBar = {
                        BottomNavigation {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination
                            listOf(
                                Screens.MainScreen,
                                Screens.SettingsScreen
                            ).forEach { screen ->
                                BottomNavigationItem(
                                    icon = {
                                        Icon(
                                            painterResource(id = screen.icon),
                                            contentDescription = null
                                        )
                                    },
                                    label = { Text(stringResource(id = screen.label)) },
                                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    },
                                    selectedContentColor = colors.primary,
                                    unselectedContentColor = colors.primary.copy(alpha = 0.5F),
                                    modifier = Modifier.background(colors.primaryVariant)
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screens.MainScreen.route,
                        Modifier.padding(innerPadding)
                    ) {
                        composable(route = Screens.MainScreen.route) {
                            NewsScreen(hiltViewModel())
                        }
                        composable(route = Screens.SettingsScreen.route) {
                            SettingsScreen(hiltViewModel())
                        }
                    }
                }
            })
        }
    }
}
