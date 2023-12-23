package wskim.aos.domain.usecase

import wskim.aos.domain.repository.DutchInfoRepository
import wskim.aos.domain.proguardSafeZone.vo.DutchEndListItemVO
import wskim.aos.domain.proguardSafeZone.vo.DutchHistoryListItemVO
import wskim.aos.domain.proguardSafeZone.vo.DutchListItemVO
import javax.inject.Inject

class DutchInfoUseCase @Inject constructor(
    private val dutchInfoRepository: DutchInfoRepository,
) {
    // 더치 페이 글쓰기 전 유효성 체크
    fun processedDutchWriteValidation(
        title: String,
        amount: String,
        enterPersonList: List<String>
    ): Boolean {
        return title.isNotEmpty() && amount.isNotEmpty() && enterPersonList.isNotEmpty()
    }

    // 더치 페이 글쓰기
    fun saveDutchInfo(dutchListItemVO: DutchListItemVO) {
        dutchInfoRepository.insertDutchInfo(dutchListItemVO)
    }

    // 더치 페이 목록 조회
    fun findDutchInfoList() : ArrayList<DutchListItemVO> {
        return dutchInfoRepository.selectDutchInfoList()
    }

    // 더치 페이 정산 목록 조회
    fun findDutchEndInfoList() : ArrayList<DutchEndListItemVO> {
        return dutchInfoRepository.selectDutchEndInfoList()
    }

    // 더치 페이 개별 정산
    fun modifyDutchEndSomeInfo(name: String) {
        dutchInfoRepository.updateDutchEndSomeInfo(name = name)
    }

    // 더치 페이 전체 정산
    fun modifyDutchEndAllInfo() {
        dutchInfoRepository.updateDutchEndAllInfo()
    }

    // 더치 페이 정산 전 전체 가격
    fun findDutchTotalAmount(): Int {
        return dutchInfoRepository.selectDutchTotalAmount()
    }

    // 더치 페이 정산 된 전체 이력
    fun findDutchHistoryInfoList(): ArrayList<DutchHistoryListItemVO> {
        return dutchInfoRepository.selectDutchHistoryInfoList()
    }
}