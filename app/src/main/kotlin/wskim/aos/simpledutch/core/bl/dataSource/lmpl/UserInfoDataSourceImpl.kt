package wskim.aos.simpledutch.core.bl.dataSource.lmpl

import wskim.aos.simpledutch.core.SharedPreferencesManager
import wskim.aos.simpledutch.core.bl.dataSource.UserInfoDataSource
import javax.inject.Inject

class UserInfoDataSourceImpl @Inject constructor(
    private val sharedPreferencesManager: SharedPreferencesManager
) : UserInfoDataSource {

}