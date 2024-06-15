package com.olimhousestudio.buspedia.ui.presentation.navigation


sealed class Screens(val route: String) {
    data object Splash : Screens("splash")
    data object SignUp : Screens("signUp")
    data object Login : Screens("login")
    data object Home : Screens("home")
    data object Profile : Screens("profile")
    data object AdminDashBoard : Screens("dashboard")
    data object AdminListPost : Screens("listPost")
    data object AdminAddPost : Screens("addPost")
    data object AdminProfile : Screens("adminProfile")
    data object AdminEditPost : Screens("editPost?&index={index}"){
        fun createRoute(
            index: Int?,
        ): String {
            return "editPost?&index=${index}"
        }
    }
}