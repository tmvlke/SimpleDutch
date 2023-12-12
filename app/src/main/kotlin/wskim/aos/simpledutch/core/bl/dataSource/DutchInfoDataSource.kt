package wskim.aos.simpledutch.core.bl.dataSource

import wskim.aos.simpledutch.progaurdSafeZone.HomeDutchListItemVO

interface DutchInfoDataSource {
    fun insertDutchInfo(homeDutchListItemVO: HomeDutchListItemVO)
    fun selectDutchInfoList(): ArrayList<HomeDutchListItemVO>
}