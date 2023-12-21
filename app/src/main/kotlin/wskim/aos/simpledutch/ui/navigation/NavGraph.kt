package wskim.aos.simpledutch.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import wskim.aos.simpledutch.ui.navigation.page.HomeEndPage
import wskim.aos.simpledutch.ui.navigation.page.HomeWritePage
import wskim.aos.simpledutch.ui.navigation.page.MainPage
import wskim.aos.simpledutch.ui.navigation.page.SplashPage

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
        // 회원 가입 - 유저 정보 입력 페이지 (휴대폰 인증)
        buildStartToEndPage(PageList.Main.route) {
            MainPage(actions, it)
        }

        // 홈 글쓰기 페이지
        buildTopToBottomPage(PageList.HomeWrite.route) {
            HomeWritePage(actions, it)
        }

        // 홈 정산 페이지
        buildTopToBottomPage(PageList.HomeEnd.route) {
            HomeEndPage(actions, it)
        }
    }
}