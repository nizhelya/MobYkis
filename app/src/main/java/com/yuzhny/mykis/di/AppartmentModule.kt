package com.yuzhny.mykis.di

import com.yuzhny.mykis.data.AddressRepositoryImpl
import com.yuzhny.mykis.data.AppartmentRepositoryImpl
import com.yuzhny.mykis.data.cache.appartment.AppartmentCache
import com.yuzhny.mykis.data.cache.appartment.AppartmentCacheImpl
import com.yuzhny.mykis.data.cache.family.FamilyCache
import com.yuzhny.mykis.data.cache.family.FamilyCacheImpl
import com.yuzhny.mykis.data.cache.user.UserCache
import com.yuzhny.mykis.data.cache.user.UserCacheImpl
import com.yuzhny.mykis.data.remote.address.AddressRemote
import com.yuzhny.mykis.data.remote.address.AddressRemoteImpl
import com.yuzhny.mykis.data.remote.appartment.AppartmentRemote
import com.yuzhny.mykis.data.remote.appartment.AppartmentRemoteImpl
import com.yuzhny.mykis.data.remote.family.FamilyRemote
import com.yuzhny.mykis.data.remote.family.FamilyRemoteImpl
import com.yuzhny.mykis.domain.address.AddressRepository
import com.yuzhny.mykis.domain.appartment.AppartmentRepository
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
    abstract fun bindAppartmentCache(impl:AppartmentCacheImpl):AppartmentCache

    @Singleton
    @Binds
    abstract fun bindAppartmentRemote(impl: AppartmentRemoteImpl): AppartmentRemote

    @Singleton
    @Binds
    abstract fun bindFamilyCache(impl:FamilyCacheImpl): FamilyCache

    @Singleton
    @Binds
    abstract fun bindFamilyRemote(impl: FamilyRemoteImpl): FamilyRemote

    @Singleton
    @Binds
    abstract fun bindUserCache(impl: UserCacheImpl):UserCache

    @Singleton
    @Binds
    abstract fun bindAddressRemote(impl:AddressRemoteImpl):AddressRemote


  }