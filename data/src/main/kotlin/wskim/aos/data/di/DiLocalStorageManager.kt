package wskim.aos.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import wskim.aos.data.dataStorage.local.preferences.SharedPreferencesManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DiLocalStorageManager {

    @Singleton
    @Provides
    fun provideSharedPreferencesManager(
        @ApplicationContext context: Context
    ) : SharedPreferencesManager {
        return SharedPreferencesManager(context)
    }

    // 추후 room 사용 시
//    @Singleton
//    @Provides
//    fun provideRoomDatabase(
//        @ApplicationContext context: Context
//    ) : RoomDatabase {
//        return Room.databaseBuilder(
//            context,
//            RoomDatabase::class.java,
//            "provideRoomDatabase"
//        ).fallbackToDestructiveMigration() // 자동 마이그레이션
//            .build()
//    }
}