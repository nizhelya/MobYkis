package com.yuzhny.mykis.di

import com.yuzhny.mykis.data.cache.appartment.AppartmentRepository
import com.yuzhny.mykis.data.cache.appartment.AppartmentRepositoryImpl
import com.yuzhny.mykis.data.remote.appartment.AppartmentRemote
import com.yuzhny.mykis.data.remote.appartment.AppartmentRemoteImpl
import com.yuzhny.mykis.data.remote.service.ApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class AppartmentModule {
    @Singleton
    @Binds
    abstract fun bindAppartmentRepository(impl:AppartmentRepositoryImpl):AppartmentRepository

    @Singleton
    @Binds
    abstract fun bindAppartmentRemote(impl: AppartmentRemoteImpl): AppartmentRemote

  }