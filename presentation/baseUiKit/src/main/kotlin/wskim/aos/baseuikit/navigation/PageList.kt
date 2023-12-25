package wskim.aos.baseuikit.navigation

/*
*
* route 는 중복 제거 목적으로 클래스 명과 동일하게 쓰기
*
* 앞뒤로 "/" 넣지 않기
* */
interface SnBasePage {
    val description: String
    val route: String
}
interface SnPage : SnBasePage
interface SnTabPage : SnBasePage

interface SnTabInPage {
    val description: String
    val className: String
}
sealed class PageList : SnPage {
    companion object {
        const val description = "전체 페이지"
        const val startDestination = "Splash"
    }

    object Splash : SnPage {
        override val description: String = "스플래시 페이지"
        override val route: String = "Splash"
    }

    object Main : SnTabPage {
        override val description = "메인 페이지"
        override val route: String = "Main"

        object HomeTab: SnTabInPage {
            override val description: String = "메인의 홈 탭"
            override val className = "HomeTab"
        }

        object StorageTab: SnTabInPage {
            override val description: String = "메인의 저장소 탭"
            override val className = "StorageTab"
        }
    }

    object HomeWrite : SnPage {
        override val description = "홈 글쓰기 페이지"
        override val route = "HomeWrite"
    }

    object HomeEnd : SnPage {
        override val description = "홈 정산 페이지"
        override val route = "HomeEnd"
    }
}