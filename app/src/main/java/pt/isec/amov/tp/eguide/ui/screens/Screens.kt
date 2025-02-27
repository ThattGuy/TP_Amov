package pt.isec.amov.tp.eguide.ui.screens
enum class Screens(display: String) {
    Initialization("initialization"),
    LAYOUT_BARS("Layout"),
    Login("Login"),
    REGISTER("Register"),
    MAIN("Main"),
    LIST_LOCATIONS("ListLocation"),
    REGISTER_LOCATION("RegisterLocation"),
    REGISTER_CATEGORY("RegisterCategory"),
    LIST_POINTS_OF_INTEREST("ListPointsOfInterest"),
    LIST_CATEGORIES("ListCategories"),
    REGISTER_POINT_OF_INTEREST("RegisterPointOfInterest"),
    PROFILE("Profile"),
    CONTRIBUTIONS("MyContributions"),
    CREDITS("Credits"),
    CHOOSEWHATTOREGISTER("ChooseWhatToRegister"),
    EDIT_POINT_OF_INTEREST("EditPointOfInterest"),
    EDIT_LOCATION("EditLocation"),
    EDIT_CATEGORY("EditCategory"),
    ADD_REVIEW("AddReview"),
    LIST_REVIEWS("ListReviews");


    val route : String
        get() = this.toString()
}