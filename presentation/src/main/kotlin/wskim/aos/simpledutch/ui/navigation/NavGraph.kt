package wskim.aos.simpledutch.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import wskim.aos.baseuikit.navigation.PageList
import wskim.aos.baseuikit.navigation.PageMoveActions
import wskim.aos.baseuikit.navigation.buildFadeInToOutPage
import wskim.aos.baseuikit.navigation.buildStartToEndPage
import wskim.aos.hometab.navigation.homeTabGraph
import wskim.aos.simpledutch.ui.navigation.page.MainPage
import wskim.aos.simpledutch.ui.navigation.page.SplashPage
import wskim.aos.storagetab.navigation.storageTabGraph

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val actions = remember(navController) { PageMoveActions(navController) }


    NavHost(
        navController,
        startDestination = PageList.startDestination,
    ) {
        ///////////////////////////////////////////////////////
        // 스플래시 및 팝업
        ///////////////////////////////////////////////////////
        buildFadeInToOutPage(PageList.Splash.route) {
            SplashPage(actions, it)
        }

        ///////////////////////////////////////////////////////
        // 메인
        ///////////////////////////////////////////////////////
        buildStartToEndPage(PageList.Main.route) {
            MainPage(actions, it)
        }

        ///////////////////////////////////////////////////////
        // 홈 탭
        ///////////////////////////////////////////////////////
        homeTabGraph(actions)

        ///////////////////////////////////////////////////////
        // 저장소 탭
        ///////////////////////////////////////////////////////
        storageTabGraph(actions)
    }
}