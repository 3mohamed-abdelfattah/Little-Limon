package kmp.project.littlelemon

interface Destinations {
    val route: String
}

object Onboarding : Destinations {
    override val route = "onboarding"
}

object Home : Destinations {
    override val route = "Home"
}

object Profile : Destinations {
    override val route = "Profile"
}