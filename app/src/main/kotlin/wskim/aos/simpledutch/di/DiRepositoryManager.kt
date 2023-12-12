package wskim.aos.simpledutch.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import wskim.aos.simpledutch.core.bl.dataSource.DutchInfoDataSource
import wskim.aos.simpledutch.core.bl.repository.DutchInfoRepository
import wskim.aos.simpledutch.core.bl.repository.lmpl.DutchInfoRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DiRepositoryManager {

    @Singleton
    @Provides
    fun provideUserInfoRepository(
        dutchInfoDataSource: DutchInfoDataSource
    ) : DutchInfoRepository {
        return DutchInfoRepositoryImpl(dutchInfoDataSource)
    }
}