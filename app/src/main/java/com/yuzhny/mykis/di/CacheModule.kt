package com.yuzhny.mykis.di

import android.content.Context
import androidx.room.Room
import com.yuzhny.mykis.BaseApplication
import com.yuzhny.mykis.data.dao.AppartmentDao
import com.yuzhny.mykis.data.dao.FamilyDao
import com.yuzhny.mykis.data.cache.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CacheModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }
    @Provides
    fun provideAppartmentDao(db: AppDatabase): AppartmentDao {
        return db.appartmentDao()
    }
    @Provides
    fun provideFamilyDao(db: AppDatabase): FamilyDao {
        return db.familyDao()
    }
}