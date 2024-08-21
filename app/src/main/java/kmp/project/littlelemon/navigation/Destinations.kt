package kmp.project.littlelemon.navigation

interface Destinations {
    val route: String
}

object Splash : Destinations {
    override val route = "splash"
}

object Onboarding : Destinations {
    override val route = "onboarding"
}

object Home : Destinations {
    override val route = "home"
}

object Profile : Destinations {
    override val route = "profile"
}

object Cart : Destinations {
    override val route = "cart"
}