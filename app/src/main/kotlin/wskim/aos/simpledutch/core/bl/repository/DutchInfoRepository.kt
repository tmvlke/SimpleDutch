package wskim.aos.simpledutch.core.bl.repository

import wskim.aos.simpledutch.progaurdSafeZone.HomeDutchListItemVO

interface DutchInfoRepository {
    fun insertDutchInfo(homeDutchListItemVO: HomeDutchListItemVO)
    fun selectDutchInfoList(): ArrayList<HomeDutchListItemVO>
}