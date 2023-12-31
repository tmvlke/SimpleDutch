package wskim.aos.baseuikit.navigation

import androidx.navigation.NavController

class PageMoveActions(navController: NavController) :
    Common,
    Main,
    Home
{
    override val upPress: () -> Unit = {
        navController.navigateUp()
    }

    override val gotoSplash: () -> Unit = {
        navController.popBackStack()
        navController.navigate(PageList.Splash.route)
    }

    override val gotoMain: () -> Unit = {
        navController.popBackStack()
        navController.navigate(PageList.Main.route)
    }

    override val gotoHomeWrite: () -> Unit = {
        navController.navigate(PageList.HomeWrite.route)
    }

    override val gotoHomeEnd: () -> Unit = {
        navController.navigate(PageList.HomeEnd.route)
    }
}

interface BasePageMoveActions

interface Common : BasePageMoveActions {
    val upPress: () -> Unit
    val gotoSplash: () -> Unit
}

interface Main : BasePageMoveActions {
    val gotoMain: () -> Unit
}

interface Home : BasePageMoveActions {
    val gotoHomeWrite: () -> Unit
    val gotoHomeEnd: () -> Unit
}
