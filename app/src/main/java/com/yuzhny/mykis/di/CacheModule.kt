package com.yuzhny.mykis.di

import androidx.room.Room
import com.yuzhny.mykis.BaseApplication
import com.yuzhny.mykis.cache.dao.AppartmentDao
import com.yuzhny.mykis.cache.dao.FamilyDao
import com.yuzhny.mykis.cache.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CacheModule {

    @Singleton
    @Provides
    fun provideDb(app:BaseApplication):AppDatabase{
        return Room
            .databaseBuilder(app,AppDatabase::class.java , AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideAppartmentDao(db: AppDatabase): AppartmentDao{
        return db.appartmentDao()
    }
    @Singleton
    @Provides
    fun provideFamilyDao(db: AppDatabase): FamilyDao{
        return db.familyDao()
    }
}