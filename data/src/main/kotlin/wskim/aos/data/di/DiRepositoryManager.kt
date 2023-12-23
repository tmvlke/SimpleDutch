package wskim.aos.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import wskim.aos.data.dataSource.DutchInfoDataSource
import wskim.aos.data.repository.DutchInfoRepositoryImpl
import wskim.aos.domain.repository.DutchInfoRepository
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