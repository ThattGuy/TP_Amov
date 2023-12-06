package pt.isec.amov.tp.eguide.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pt.isec.amov.tp.eguide.ui.screens.LoginScreen
import pt.isec.amov.tp.eguide.ui.screens.MainScreen
import pt.isec.amov.tp.eguide.ui.screens.RegisterScreen
import pt.isec.amov.tp.eguide.ui.screens.Screens
import pt.isec.amov.tp.eguide.ui.viewmodels.LocationViewModel


@Composable
fun SetupNavGraph(navController : NavHostController, viewModel: LocationViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screens.Login.route
    ) {
        composable(Screens.Login.route) {
            LoginScreen(viewModel = viewModel, navController = navController)
        }
        composable(Screens.REGISTER.route) {
            RegisterScreen(viewModel = viewModel)
        }
        composable(Screens.MAIN.route) {
            MainScreen(viewModel = viewModel)
        }
    }
}