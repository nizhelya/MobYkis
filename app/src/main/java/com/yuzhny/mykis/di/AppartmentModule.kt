package com.yuzhny.mykis.di

import com.yuzhny.mykis.data.cache.appartment.AppartmentRepository
import com.yuzhny.mykis.data.cache.appartment.AppartmentRepositoryImpl
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
    abstract fun bindAppartment(impl:AppartmentRepositoryImpl):AppartmentRepository
}