package wskim.aos.simpledutch.core.bl.repository.lmpl

import wskim.aos.simpledutch.core.bl.dataSource.DutchInfoDataSource
import wskim.aos.simpledutch.core.bl.repository.DutchInfoRepository
import wskim.aos.simpledutch.progaurdSafeZone.HomeDutchListItemVO
import javax.inject.Inject

class DutchInfoRepositoryImpl @Inject constructor(
    private val dutchInfoDataSource: DutchInfoDataSource
) : DutchInfoRepository  {
    override fun insertDutchInfo(homeDutchListItemVO: HomeDutchListItemVO) {
        dutchInfoDataSource.insertDutchInfo(homeDutchListItemVO)
    }

    override fun selectDutchInfoList(): ArrayList<HomeDutchListItemVO> {
        return dutchInfoDataSource.selectDutchInfoList()
    }
}