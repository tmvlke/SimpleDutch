package wskim.aos.simpledutch.core.bl.dataSource

import wskim.aos.simpledutch.progaurdSafeZone.DutchEndListItemVO
import wskim.aos.simpledutch.progaurdSafeZone.DutchHistoryListItemVO
import wskim.aos.simpledutch.progaurdSafeZone.DutchListItemVO

interface DutchInfoDataSource {
    fun insertDutchInfo(dutchListItemVO: DutchListItemVO)
    fun selectDutchInfoList(): ArrayList<DutchListItemVO>
    fun selectDutchEndInfoList(): ArrayList<DutchEndListItemVO>
    fun updateDutchEndSomeInfo(name: String)
    fun updateDutchEndAllInfo()
    fun selectDutchTotalAmount(): Int
    fun selectDutchHistoryInfoList(): ArrayList<DutchHistoryListItemVO>
}