package pt.isec.amov.tp.eguide.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import pt.isec.amov.tp.eguide.ui.screens.ChooseWhatToRegisterScreen
import pt.isec.amov.tp.eguide.ui.screens.location.RegisterLocationScreen
import pt.isec.amov.tp.eguide.ui.screens.Credits
import pt.isec.amov.tp.eguide.ui.screens.category.ListCategories
import pt.isec.amov.tp.eguide.ui.screens.location.ListLocations
import pt.isec.amov.tp.eguide.ui.screens.poi.ListPointsOfInterest
import pt.isec.amov.tp.eguide.ui.screens.MainScreen
import pt.isec.amov.tp.eguide.ui.screens.user.MyContributions
import pt.isec.amov.tp.eguide.ui.screens.category.RegisterCategory
import pt.isec.amov.tp.eguide.ui.screens.poi.RegisterPointOfInterest
import pt.isec.amov.tp.eguide.ui.screens.auth.RegisterScreen
import pt.isec.amov.tp.eguide.ui.screens.Screens
import pt.isec.amov.tp.eguide.ui.screens.user.EditUserInfoScreen
import pt.isec.amov.tp.eguide.ui.screens.auth.LoginScreen
import pt.isec.amov.tp.eguide.ui.screens.category.EditCategory
import pt.isec.amov.tp.eguide.ui.screens.location.EditLocation
import pt.isec.amov.tp.eguide.ui.screens.poi.EditPointOfInterest
import pt.isec.amov.tp.eguide.ui.screens.poi.review.AddReview
import pt.isec.amov.tp.eguide.ui.screens.poi.review.ListReviews
import pt.isec.amov.tp.eguide.ui.screens.uicomponents.InitializationView
import pt.isec.amov.tp.eguide.ui.screens.uicomponents.Layout_Bars
import pt.isec.amov.tp.eguide.ui.viewmodels.AuthViewModel
import pt.isec.amov.tp.eguide.ui.viewmodels.LocationViewModel


@Composable
fun SetupNavGraph(navController: NavHostController,
                  authViewModel: AuthViewModel,
                  locationViewModel: LocationViewModel) {

    NavHost(
        navController = navController,
        startDestination = Screens.Initialization.route
        //startDestination = Screens.REGISTER_POINT_OF_INTEREST.route

    ) {
        composable(Screens.Initialization.route){
            InitializationView(viewModel = authViewModel, navController = navController)
        }
        composable(Screens.Login.route) {
            LoginScreen(viewModel = authViewModel, navController = navController){
                navController.navigate(Screens.MAIN.route)
            }
        }
        composable(Screens.REGISTER.route) {
            RegisterScreen(viewModel = authViewModel){
                navController.navigate(Screens.MAIN.route)
            }
        }
        composable(Screens.MAIN.route) {
            Layout_Bars(viewModel = authViewModel, navController = navController) {
                MainScreen(viewModel = locationViewModel, navController = navController)
            }
        }
        composable(Screens.LIST_LOCATIONS.route){
            //ListLocations(viewModel = locationViewModel, navController = navController)
            Layout_Bars(viewModel = authViewModel, navController = navController) {
                ListLocations(viewModel = locationViewModel, navController = navController)
            }
        }
        composable(Screens.REGISTER_LOCATION.route){
            Layout_Bars(viewModel = authViewModel, navController = navController) {
                RegisterLocationScreen(viewModel = locationViewModel, navController = navController)
            }
        }
        composable("${Screens.REGISTER_LOCATION.route}/{coordinates}", arguments = listOf(
            navArgument("coordinates") { type = NavType.StringType }
        )){
            Layout_Bars(viewModel = authViewModel, navController = navController) {
                RegisterLocationScreen(navController = navController, viewModel = locationViewModel)
            }
        }
        composable("${Screens.CHOOSEWHATTOREGISTER.route}/{coordinates}", arguments = listOf(
            navArgument("coordinates") { type = NavType.StringType }
        )){
            Layout_Bars(viewModel = authViewModel, navController = navController) {
                ChooseWhatToRegisterScreen(navController = navController)
            }
        }
        composable(Screens.REGISTER_CATEGORY.route){
            Layout_Bars(viewModel = authViewModel, navController = navController) {
                RegisterCategory(locationViewModel, navController = navController)
            }
        }
        composable(Screens.REGISTER_POINT_OF_INTEREST.route){
            Layout_Bars(viewModel = authViewModel, navController = navController) {
                RegisterPointOfInterest(navController = navController, viewModel = locationViewModel)
            }
        }
        composable("${Screens.REGISTER_POINT_OF_INTEREST.route}/{coordinates}", arguments = listOf(
            navArgument("coordinates") { type = NavType.StringType }
        )){
            Layout_Bars(viewModel = authViewModel, navController = navController) {
                RegisterPointOfInterest(navController = navController, viewModel = locationViewModel)
            }
        }
        composable(Screens.LIST_POINTS_OF_INTEREST.route) {
            Layout_Bars(viewModel = authViewModel, navController = navController) {
                ListPointsOfInterest(viewModel = locationViewModel, navController = navController)
            }
        }
        composable(Screens.LIST_CATEGORIES.route) {
            Layout_Bars(viewModel = authViewModel, navController = navController) {
                ListCategories(viewModel = locationViewModel, navController = navController)
            }
        }

        composable(Screens.PROFILE.route) {
            Layout_Bars(viewModel = authViewModel, navController = navController) {
                EditUserInfoScreen(viewModel = authViewModel, navController = navController)
            }
        }

        composable(Screens.CONTRIBUTIONS.route) {
            Layout_Bars(viewModel = authViewModel, navController = navController) {
                MyContributions()
            }
        }

        composable(Screens.CREDITS.route) {
            Layout_Bars(viewModel = authViewModel, navController = navController) {
                Credits()
            }
        }
        composable(Screens.EDIT_POINT_OF_INTEREST.route) {
            Layout_Bars(viewModel = authViewModel, navController = navController) {
                EditPointOfInterest(viewModel = locationViewModel, navController = navController)
            }
        }
        composable(Screens.EDIT_LOCATION.route) {
            Layout_Bars(viewModel = authViewModel, navController = navController) {
                EditLocation(viewModel = locationViewModel, navController = navController)
            }
        }
        composable(Screens.EDIT_CATEGORY.route) {
            Layout_Bars(viewModel = authViewModel, navController = navController) {
                EditCategory(viewModel = locationViewModel, navController = navController)
            }
        }
        composable(Screens.LIST_REVIEWS.route) {
            Layout_Bars(viewModel = authViewModel, navController = navController) {
                ListReviews(viewModel = locationViewModel, navController = navController)
            }
        }
        composable(Screens.ADD_REVIEW.route) {
            Layout_Bars(viewModel = authViewModel, navController = navController) {
                AddReview(viewModel = locationViewModel, navController = navController)
            }
        }
    }
}