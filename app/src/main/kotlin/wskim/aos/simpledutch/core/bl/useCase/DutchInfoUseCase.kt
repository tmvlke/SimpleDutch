package wskim.aos.simpledutch.core.bl.useCase

import wskim.aos.simpledutch.core.bl.repository.DutchInfoRepository
import wskim.aos.simpledutch.progaurdSafeZone.HomeDutchListItemVO
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
    fun saveDutchInfo(homeDutchListItemVO: HomeDutchListItemVO) {
        dutchInfoRepository.insertDutchInfo(homeDutchListItemVO)
    }

    // 더치 페이 목록 조회
    fun findDutchInfoList() : ArrayList<HomeDutchListItemVO> {
        return dutchInfoRepository.selectDutchInfoList()
    }
}