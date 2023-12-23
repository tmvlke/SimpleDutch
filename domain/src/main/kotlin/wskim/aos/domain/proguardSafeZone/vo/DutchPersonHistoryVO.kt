package wskim.aos.domain.proguardSafeZone.vo

data class DutchPersonHistoryVO(
    val name: String,
    val amount: Int,
    val count: Int
)

fun buildDutchPersonHistoryVOPreview() = DutchPersonHistoryVO(
    name = "참석자1",
    amount = 50000,
    count = 3
)