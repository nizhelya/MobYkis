package com.yuzhny.mykis.di

import com.yuzhny.mykis.data.AddressRepositoryImpl
import com.yuzhny.mykis.data.AppartmentRepositoryImpl
import com.yuzhny.mykis.data.cache.appartment.AppartmentCache
import com.yuzhny.mykis.data.cache.appartment.AppartmentCacheImpl
import com.yuzhny.mykis.data.cache.family.FamilyCache
import com.yuzhny.mykis.data.cache.family.FamilyCacheImpl
import com.yuzhny.mykis.data.cache.payment.PaymentCache
import com.yuzhny.mykis.data.cache.payment.PaymentCacheImpl
import com.yuzhny.mykis.data.cache.service.ServiceCache
import com.yuzhny.mykis.data.cache.service.ServiceCacheImpl
import com.yuzhny.mykis.data.cache.user.UserCache
import com.yuzhny.mykis.data.cache.user.UserCacheImpl
import com.yuzhny.mykis.data.cache.water.WaterMeterCache
import com.yuzhny.mykis.data.cache.water.WaterMeterCacheImpl
import com.yuzhny.mykis.data.remote.address.AddressRemote
import com.yuzhny.mykis.data.remote.address.AddressRemoteImpl
import com.yuzhny.mykis.data.remote.appartment.AppartmentRemote
import com.yuzhny.mykis.data.remote.appartment.AppartmentRemoteImpl
import com.yuzhny.mykis.data.remote.family.FamilyRemote
import com.yuzhny.mykis.data.remote.family.FamilyRemoteImpl
import com.yuzhny.mykis.data.remote.payment.PaymentRemote
import com.yuzhny.mykis.data.remote.payment.PaymentRemoteImpl
import com.yuzhny.mykis.data.remote.service.ServiceRemote
import com.yuzhny.mykis.data.remote.service.ServiceRemoteImpl
import com.yuzhny.mykis.data.remote.water.WaterMeterRemote
import com.yuzhny.mykis.data.remote.water.WaterMeterRemoteImpl
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

    @Singleton
    @Binds
    abstract fun bindServiceCache(impl:ServiceCacheImpl): ServiceCache

    @Singleton
    @Binds
    abstract fun bindServiceRemote(impl: ServiceRemoteImpl): ServiceRemote

    @Singleton
    @Binds
    abstract fun bindPaymentCache(impl: PaymentCacheImpl): PaymentCache

    @Singleton
    @Binds
    abstract fun bindPaymentRemote(impl: PaymentRemoteImpl): PaymentRemote

    @Singleton
    @Binds
    abstract fun bindWaterMeterCache(impl: WaterMeterCacheImpl): WaterMeterCache

    @Singleton
    @Binds
    abstract fun bindWaterMeterRemote(impl: WaterMeterRemoteImpl): WaterMeterRemote
  }