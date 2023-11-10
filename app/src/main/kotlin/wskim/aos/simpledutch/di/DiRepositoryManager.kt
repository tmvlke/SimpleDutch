package wskim.aos.simpledutch.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import wskim.aos.simpledutch.core.bl.dataSource.UserInfoDataSource
import wskim.aos.simpledutch.core.bl.repository.UserInfoRepository
import wskim.aos.simpledutch.core.bl.repository.lmpl.UserInfoRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DiRepositoryManager {

    @Singleton
    @Provides
    fun provideUserInfoRepository(
        userInfoDataSource: UserInfoDataSource
    ) : UserInfoRepository {
        return UserInfoRepositoryImpl(userInfoDataSource)
    }
}