package wskim.aos.simpledutch.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import wskim.aos.simpledutch.core.SharedPreferencesManager
import wskim.aos.simpledutch.core.bl.dataSource.DutchInfoDataSource
import wskim.aos.simpledutch.core.bl.dataSource.lmpl.DutchInfoDataSourceImpl
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