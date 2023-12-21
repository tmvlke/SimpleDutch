package wskim.aos.simpledutch.progaurdSafeZone

data class DutchHistoryListItemVO(
    val totalAmount: Int,
    val personList: ArrayList<DutchPersonHistoryVO>,
    val regDate: String
)
fun buildDutchHistoryListItemVOPreview() = DutchHistoryListItemVO(
    totalAmount = 100000,
    personList = arrayListOf(
        buildDutchPersonHistoryVOPreview(),
        buildDutchPersonHistoryVOPreview()
    ),
    regDate = "2023-12-10"
)