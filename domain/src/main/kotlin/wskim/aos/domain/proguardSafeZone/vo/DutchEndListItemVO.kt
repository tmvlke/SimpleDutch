package wskim.aos.domain.proguardSafeZone.vo

data class DutchEndListItemVO(
    val name: String,
    val list: List<DutchListItemVO>,
    val isEnd: Boolean
)
fun buildDutchEndListItemVOPreview() = DutchEndListItemVO(
    name = "참여자1",
    list = arrayListOf(
        buildDutchListItemVOPreview(),
        buildDutchListItemVOPreview()
    ),
    isEnd = false
)