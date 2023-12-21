package wskim.aos.simpledutch.core.bl.repository.lmpl

import wskim.aos.simpledutch.core.bl.dataSource.DutchInfoDataSource
import wskim.aos.simpledutch.core.bl.repository.DutchInfoRepository
import wskim.aos.simpledutch.progaurdSafeZone.DutchEndListItemVO
import wskim.aos.simpledutch.progaurdSafeZone.DutchHistoryListItemVO
import wskim.aos.simpledutch.progaurdSafeZone.DutchListItemVO
import javax.inject.Inject

class DutchInfoRepositoryImpl @Inject constructor(
    private val dutchInfoDataSource: DutchInfoDataSource
) : DutchInfoRepository  {
    override fun insertDutchInfo(dutchListItemVO: DutchListItemVO) {
        dutchInfoDataSource.insertDutchInfo(dutchListItemVO)
    }

    override fun selectDutchInfoList(): ArrayList<DutchListItemVO> {
        return dutchInfoDataSource.selectDutchInfoList()
    }

    override fun selectDutchEndInfoList(): ArrayList<DutchEndListItemVO> {
        val list = dutchInfoDataSource.selectDutchEndInfoList()
        list.sortBy { it.name }
        return list
    }

    override fun selectDutchHistoryInfoList(): ArrayList<DutchHistoryListItemVO> {
        val list = dutchInfoDataSource.selectDutchHistoryInfoList()

        // 전체 리스트 작성 일자 최신순 정렬
        list.sortByDescending { it.regDate }

        // 개별 리스트 내 참여자 명 기준 내림차순 정렬
        list.onEach {
            it.personList.sortBy { item -> item.name }
        }
        return list
    }

    override fun updateDutchEndSomeInfo(name: String) {
        return dutchInfoDataSource.updateDutchEndSomeInfo(name)
    }

    override fun updateDutchEndAllInfo() {
        return dutchInfoDataSource.updateDutchEndAllInfo()
    }

    override fun selectDutchTotalAmount(): Int {
        return dutchInfoDataSource.selectDutchTotalAmount()
    }
}