package com.yuzhny.mykis.di

import com.yuzhny.mykis.data.cache.appartment.AppartmentCache
import com.yuzhny.mykis.data.cache.appartment.AppartmentCacheImpl
import com.yuzhny.mykis.data.cache.user.UserCache
import com.yuzhny.mykis.data.cache.user.UserCacheImpl
import com.yuzhny.mykis.data.remote.appartment.AppartmentRemote
import com.yuzhny.mykis.data.remote.appartment.AppartmentRemoteImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class AppartmentModule {
    @Singleton
    @Binds
    abstract fun bindAppartmentRepository(impl:AppartmentCacheImpl):AppartmentCache

    @Singleton
    @Binds
    abstract fun bindAppartmentRemote(impl: AppartmentRemoteImpl): AppartmentRemote

    @Singleton
    @Binds
    abstract fun bindUserCache(impl: UserCacheImpl):UserCache

  }