package wskim.aos.simpledutch.core.bl.useCase

import wskim.aos.simpledutch.core.bl.repository.UserInfoRepository
import javax.inject.Inject

class UserInfoUseCase @Inject constructor(
    private val userInfoRepository: UserInfoRepository,
) {

}