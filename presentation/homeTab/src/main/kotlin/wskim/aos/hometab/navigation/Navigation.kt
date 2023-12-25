package wskim.aos.hometab.navigation

import androidx.navigation.NavGraphBuilder
import wskim.aos.baseuikit.navigation.PageList
import wskim.aos.baseuikit.navigation.PageMoveActions
import wskim.aos.baseuikit.navigation.buildTopToBottomPage
import wskim.aos.hometab.navigation.page.HomeEndPage
import wskim.aos.hometab.navigation.page.HomeWritePage

fun NavGraphBuilder.homeTabGraph(actions: PageMoveActions) {
    // 홈 글쓰기 페이지
    buildTopToBottomPage(PageList.HomeWrite.route) {
        HomeWritePage(actions, it)
    }

    // 홈 정산 페이지
    buildTopToBottomPage(PageList.HomeEnd.route) {
        HomeEndPage(actions, it)
    }
}
