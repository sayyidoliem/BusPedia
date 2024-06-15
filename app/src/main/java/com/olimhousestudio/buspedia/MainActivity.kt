package com.olimhousestudio.buspedia

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.olimhousestudio.buspedia.data.source.local.KotprefLocalStorage
import com.olimhousestudio.buspedia.ui.presentation.navigation.Screens
import com.olimhousestudio.buspedia.ui.presentation.screens.module.admin.dashboard.AddPostScreens
import com.olimhousestudio.buspedia.ui.presentation.screens.module.admin.dashboard.AdminEditPostScreens
import com.olimhousestudio.buspedia.ui.presentation.screens.module.admin.dashboard.AdminListPost
import com.olimhousestudio.buspedia.ui.presentation.screens.module.admin.dashboard.DashboardScreens
import com.olimhousestudio.buspedia.ui.presentation.screens.module.admin.profile.AdminProfileScreens
import com.olimhousestudio.buspedia.ui.presentation.screens.module.guest.login.LoginScreens
import com.olimhousestudio.buspedia.ui.presentation.screens.module.guest.signup.SignUpScreens
import com.olimhousestudio.buspedia.ui.presentation.screens.module.guest.splash.SplashScreens
import com.olimhousestudio.buspedia.ui.presentation.screens.module.user.home.HomeScreens
import com.olimhousestudio.buspedia.ui.presentation.screens.module.user.profile.ProfileScreens
import com.olimhousestudio.buspedia.ui.theme.BusPediaTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusPediaTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = when {
                        KotprefLocalStorage.accessToken.isEmpty() -> Screens.Login.route
                        KotprefLocalStorage.isAdmin == true && KotprefLocalStorage.accessToken.isNotEmpty() -> Screens.AdminDashBoard.route
                        else -> Screens.Home.route
                    }
                ) {
                    composable(Screens.Splash.route) {
                        SplashScreens(navigateToLogin = { navController.navigate(Screens.Login.route) })
                    }
                    composable(Screens.Login.route) {
                        LoginScreens(
                            navigateToHome = { navController.navigate(Screens.Home.route) },
                            navigateToAdminDashboard = { navController.navigate(Screens.AdminDashBoard.route) },
                            navigateToSignUp = { navController.navigate(Screens.SignUp.route) }
                        )
                    }
                    composable(Screens.SignUp.route) {
                        SignUpScreens(navigateToLogin = { navController.navigate(Screens.Login.route) })
                    }
                    composable(Screens.Home.route) {
                        HomeScreens(navigateToProfile = { navController.navigate(Screens.Profile.route) })
                    }
                    composable(Screens.Profile.route) {
                        ProfileScreens(
                            navigateBack = { navController.navigateUp() },
                            navigateToLogin = { navController.navigate(Screens.Login.route) })
                    }
                    composable(Screens.AdminDashBoard.route) {
                        DashboardScreens(
                            navigate = { navController.navigate(Screens.AdminAddPost.route) },
                            navigateToProfile = { navController.navigate(Screens.AdminProfile.route) },
                            navigateToEdit = { navController.navigate(Screens.AdminListPost.route) }
                        )
                    }
                    composable(Screens.AdminAddPost.route) {
                        AddPostScreens(
                            navigateBack = { navController.navigateUp() },
                            navigateToDashboard = { navController.navigate(Screens.AdminDashBoard.route) })
                    }
                    composable(
                        Screens.AdminEditPost.route,
                        arguments = listOf(
                            navArgument("index") {
                                type = NavType.IntType
                                defaultValue = 0
                            },
                        ),
                    ) {
                        val index = it.arguments!!.getInt("index", 0)
                        AdminEditPostScreens(
                            navigateBack = { navController.navigateUp() },
                            navigateToList = { navController.navigate(Screens.AdminListPost.route) },
                            index = index
                        )
                    }
                    composable(Screens.AdminProfile.route) {
                        AdminProfileScreens(
                            navigateBack = { navController.navigateUp() },
                            navigateToLogin = { navController.navigate(Screens.Login.route) })
                    }
                    composable(Screens.AdminListPost.route) {
                        AdminListPost(
                            navigateBack = { navController.navigateUp() },
                            navigateToEdit = { index ->
                                navController.navigate(
                                    Screens.AdminEditPost.createRoute(
                                        index = index
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}