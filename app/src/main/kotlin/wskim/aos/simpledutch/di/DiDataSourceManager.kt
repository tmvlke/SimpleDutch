package wskim.aos.simpledutch.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import wskim.aos.simpledutch.core.SharedPreferencesManager
import wskim.aos.simpledutch.core.bl.dataSource.UserInfoDataSource
import wskim.aos.simpledutch.core.bl.dataSource.lmpl.UserInfoDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DiDataSourceManager {

    @Singleton
    @Provides
    fun provideUserInfoDataSource(
        sharedPreferencesManager: SharedPreferencesManager
    ): UserInfoDataSource {
        return UserInfoDataSourceImpl(sharedPreferencesManager)
    }

}