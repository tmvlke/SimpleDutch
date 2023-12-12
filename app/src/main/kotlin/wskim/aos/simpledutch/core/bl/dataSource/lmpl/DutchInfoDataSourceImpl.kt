package wskim.aos.simpledutch.core.bl.dataSource.lmpl

import wskim.aos.simpledutch.core.SharedPreferencesManager
import wskim.aos.simpledutch.core.bl.dataSource.DutchInfoDataSource
import wskim.aos.simpledutch.progaurdSafeZone.HomeDutchListItemVO
import javax.inject.Inject

class DutchInfoDataSourceImpl @Inject constructor(
    private val sharedPreferencesManager: SharedPreferencesManager
) : DutchInfoDataSource {
    override fun insertDutchInfo(homeDutchListItemVO: HomeDutchListItemVO) {
        sharedPreferencesManager.saveDutchInfo(homeDutchListItemVO)
    }

    override fun selectDutchInfoList(): ArrayList<HomeDutchListItemVO> {
        return sharedPreferencesManager.getDutchInfoList()
    }
}