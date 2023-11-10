package wskim.aos.simpledutch.ui.navigation

interface SinglePage
interface MultiPage
/*
* 단일 페이지 -> object
* 복수 페이지 -> sealed class
*
* route 는 중복 제거 목적으로 클래스 명과 동일하게 쓰기
*
* 앞뒤로 "/" 넣지 않기
* */
sealed class PageList : MultiPage {
    companion object {
        const val description = "전체 페이지"
        const val startDestination = Splash.route
    }

    object Splash : SinglePage {
        const val description: String = "스플래시 페이지"
        const val route: String = "Splash"
    }

    object Main : SinglePage {
        const val description = "메인 페이지"
        const val route = "Main"
    }
}