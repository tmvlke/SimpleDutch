package wskim.aos.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import wskim.aos.data.dataSource.DutchInfoDataSource
import wskim.aos.data.dataSource.lmpl.DutchInfoDataSourceImpl
import wskim.aos.data.dataStorage.local.preferences.SharedPreferencesManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DiDataSourceManager {

    @Singleton
    @Provides
    fun provideUserInfoDataSource(
        sharedPreferencesManager: SharedPreferencesManager
    ): DutchInfoDataSource {
        return DutchInfoDataSourceImpl(sharedPreferencesManager)
    }

}