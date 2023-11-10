package wskim.aos.simpledutch.core.bl.repository.lmpl

import wskim.aos.simpledutch.core.bl.dataSource.UserInfoDataSource
import wskim.aos.simpledutch.core.bl.repository.UserInfoRepository
import javax.inject.Inject

class UserInfoRepositoryImpl @Inject constructor(
    private val userInfoDataSource: UserInfoDataSource
) : UserInfoRepository  {

}