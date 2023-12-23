package wskim.aos.domain.repository

import wskim.aos.domain.proguardSafeZone.vo.DutchEndListItemVO
import wskim.aos.domain.proguardSafeZone.vo.DutchHistoryListItemVO
import wskim.aos.domain.proguardSafeZone.vo.DutchListItemVO

interface DutchInfoRepository {
    fun insertDutchInfo(dutchListItemVO: DutchListItemVO)
    fun selectDutchInfoList(): ArrayList<DutchListItemVO>
    fun selectDutchEndInfoList(): ArrayList<DutchEndListItemVO>
    fun updateDutchEndSomeInfo(name: String)
    fun updateDutchEndAllInfo()
    fun selectDutchTotalAmount(): Int
    fun selectDutchHistoryInfoList(): ArrayList<DutchHistoryListItemVO>
}