package wskim.aos.simpledutch.progaurdSafeZone

data class  DutchPersonVO(
    val name: String,
    val isEnd: Boolean
)

fun buildDutchPersonVOPreview() = DutchPersonVO(
    name = "참석자1",
    isEnd = false
)