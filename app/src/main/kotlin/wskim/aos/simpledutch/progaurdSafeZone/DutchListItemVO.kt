package wskim.aos.simpledutch.progaurdSafeZone

data class DutchListItemVO(
    val title: String,
    val amount: String,
    val enterPersonList: ArrayList<DutchPersonVO>
)

fun buildDutchListItemVOPreview() = DutchListItemVO(
    title = "1차",
    amount = "1000",
    enterPersonList = arrayListOf(
        buildDutchPersonVOPreview(),
        buildDutchPersonVOPreview()
    )
)