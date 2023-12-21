package wskim.aos.simpledutch.core.bl.dataSource.lmpl

import wskim.aos.simpledutch.core.SharedPreferencesManager
import wskim.aos.simpledutch.core.bl.dataSource.DutchInfoDataSource
import wskim.aos.simpledutch.core.util.DateTimeUtils
import wskim.aos.simpledutch.progaurdSafeZone.DutchEndListItemVO
import wskim.aos.simpledutch.progaurdSafeZone.DutchHistoryListItemVO
import wskim.aos.simpledutch.progaurdSafeZone.DutchListItemVO
import wskim.aos.simpledutch.progaurdSafeZone.DutchPersonHistoryVO
import javax.inject.Inject

class DutchInfoDataSourceImpl @Inject constructor(
    private val sharedPreferencesManager: SharedPreferencesManager
) : DutchInfoDataSource {
    override fun insertDutchInfo(dutchListItemVO: DutchListItemVO) {
        sharedPreferencesManager.saveDutchInfo(
            selectDutchInfoList().apply {
                add(dutchListItemVO)
            }
        )
    }

    override fun selectDutchInfoList(): ArrayList<DutchListItemVO> {
        return sharedPreferencesManager.getDutchInfoList()
    }

    override fun selectDutchEndInfoList(): ArrayList<DutchEndListItemVO> {
        val list = selectDutchInfoList()
        val saveMap = HashMap<String, ArrayList<DutchListItemVO>>()
        val resultList = ArrayList<DutchEndListItemVO>()

        // HashMap 으로 중복 제거 및 기본 틀 체우기
        list.onEach { itemVO ->
            itemVO.enterPersonList.onEach { itemDetailVO ->
                if (saveMap[itemDetailVO.name] == null) {
                    saveMap[itemDetailVO.name] = arrayListOf(
                        DutchListItemVO(
                            title = itemVO.title,
                            amount = itemVO.amount,
                            enterPersonList = itemVO.enterPersonList
                        )
                    )
                } else {
                    saveMap[itemDetailVO.name]!!.apply {
                        add(
                            DutchListItemVO(
                                title = itemVO.title,
                                amount = itemVO.amount,
                                enterPersonList = itemVO.enterPersonList
                            )
                        )
                    }
                }
            }
        }

        // 기본 틀을 가지고 정산 여부 체우기
        val saveMapToList = saveMap.toList()
        saveMapToList.onEach { mapData ->

            var isEnd = true
            mapData.second.onEach {

                it.enterPersonList.onEach { personVO ->
                    if (mapData.first == personVO.name && !personVO.isEnd) {
                        isEnd = false
                    }
                }
            }

            resultList.add(
                DutchEndListItemVO(
                    name = mapData.first,
                    list = mapData.second,
                    isEnd = isEnd
                )
            )
        }

        return resultList
    }

    override fun updateDutchEndSomeInfo(name: String) {
        val list = selectDutchInfoList()

        list.onEach {
            it.enterPersonList.onEachIndexed { index, personVO ->
                if (personVO.name == name) {
                    it.enterPersonList[index] = it.enterPersonList[index].copy(
                        isEnd = !it.enterPersonList[index].isEnd
                    )
                }
            }
        }

        sharedPreferencesManager.saveDutchInfo(list)
    }

    override fun updateDutchEndAllInfo() {
        val resultHistoryVO = ArrayList<DutchPersonHistoryVO>()
        val list = selectDutchInfoList()

        var amount = 0
        list.onEach {
            amount += it.amount.toInt()

            it.enterPersonList.onEach { personVO ->
                // 더치 페이 이력 만들기
                resultHistoryVO.add(
                    DutchPersonHistoryVO(
                        name = personVO.name,
                        amount = it.amount.toInt() / it.enterPersonList.size,
                        count = 1
                    )
                )
            }
        }

        // 중복 내역 합산
        val distinct = resultHistoryVO.groupBy { it.name }
            .mapValues { (_, entries) ->
                entries.reduce { acc, current ->
                    acc.copy(amount = acc.amount + current.amount, count = acc.count + current.count)
                }
            }.values.toList()

        // 초기화 후 다시 대입
        resultHistoryVO.clear()
        resultHistoryVO.addAll(distinct)

        // 더치 페이 정산 이력 추가
        sharedPreferencesManager.saveDutchHistoryInfo(
            sharedPreferencesManager.getDutchHistoryInfoList().apply {
                add(
                    DutchHistoryListItemVO(
                        totalAmount = amount,
                        personList = resultHistoryVO,
                        regDate = DateTimeUtils.todayToDash()
                    )
                )
            }
        )

        // 더치 페이 목록 초기화
        sharedPreferencesManager.saveDutchInfo(arrayListOf())
    }

    override fun selectDutchTotalAmount(): Int {
        return selectDutchInfoList().map { it.amount }.sumOf { it.toInt() }
    }

    override fun selectDutchHistoryInfoList(): ArrayList<DutchHistoryListItemVO> {
        return sharedPreferencesManager.getDutchHistoryInfoList()
    }
}