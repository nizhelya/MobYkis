package com.yuzhny.mykis.di

import com.yuzhny.mykis.data.cache.appartment.AppartmentCache
import com.yuzhny.mykis.data.cache.appartment.AppartmentCacheImpl
import com.yuzhny.mykis.data.cache.family.FamilyCache
import com.yuzhny.mykis.data.cache.family.FamilyCacheImpl
import com.yuzhny.mykis.data.cache.heat.meter.HeatMeterCache
import com.yuzhny.mykis.data.cache.heat.meter.HeatMeterCacheImpl
import com.yuzhny.mykis.data.cache.heat.reading.HeatReadingCache
import com.yuzhny.mykis.data.cache.heat.reading.HeatReadingCacheImpl
import com.yuzhny.mykis.data.cache.payment.PaymentCache
import com.yuzhny.mykis.data.cache.payment.PaymentCacheImpl
import com.yuzhny.mykis.data.cache.service.ServiceCache
import com.yuzhny.mykis.data.cache.service.ServiceCacheImpl
import com.yuzhny.mykis.data.cache.user.UserCache
import com.yuzhny.mykis.data.cache.user.UserCacheImpl
import com.yuzhny.mykis.data.cache.water.meter.WaterMeterCache
import com.yuzhny.mykis.data.cache.water.meter.WaterMeterCacheImpl
import com.yuzhny.mykis.data.cache.water.reading.WaterReadingCache
import com.yuzhny.mykis.data.cache.water.reading.WaterReadingCacheImpl
import com.yuzhny.mykis.data.remote.address.AddressRemote
import com.yuzhny.mykis.data.remote.address.AddressRemoteImpl
import com.yuzhny.mykis.data.remote.appartment.AppartmentRemote
import com.yuzhny.mykis.data.remote.appartment.AppartmentRemoteImpl
import com.yuzhny.mykis.data.remote.family.FamilyRemote
import com.yuzhny.mykis.data.remote.family.FamilyRemoteImpl
import com.yuzhny.mykis.data.remote.heat.meter.HeatMeterRemote
import com.yuzhny.mykis.data.remote.heat.meter.HeatMeterRemoteImpl
import com.yuzhny.mykis.data.remote.heat.reading.HeatReadingRemote
import com.yuzhny.mykis.data.remote.heat.reading.HeatReadingRemoteImpl
import com.yuzhny.mykis.data.remote.payment.PaymentRemote
import com.yuzhny.mykis.data.remote.payment.PaymentRemoteImpl
import com.yuzhny.mykis.data.remote.service.ServiceRemote
import com.yuzhny.mykis.data.remote.service.ServiceRemoteImpl
import com.yuzhny.mykis.data.remote.water.meter.WaterMeterRemote
import com.yuzhny.mykis.data.remote.water.meter.WaterMeterRemoteImpl
import com.yuzhny.mykis.data.remote.water.reading.WaterReadingRemote
import com.yuzhny.mykis.data.remote.water.reading.WaterReadingRemoteImpl
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

    @Singleton
    @Binds
    abstract fun bindWaterReadingCache(impl: WaterReadingCacheImpl): WaterReadingCache

    @Singleton
    @Binds
    abstract fun bindWaterReadingRemote(impl: WaterReadingRemoteImpl): WaterReadingRemote

    @Singleton
    @Binds
    abstract fun bindHeatMeterCache(impl: HeatMeterCacheImpl): HeatMeterCache

    @Singleton
    @Binds
    abstract fun bindHeatMeterRemote(impl: HeatMeterRemoteImpl): HeatMeterRemote

    @Singleton
    @Binds
    abstract fun bindHeatReadingCache(impl: HeatReadingCacheImpl): HeatReadingCache

    @Singleton
    @Binds
    abstract fun bindHeatReadingRemote(impl: HeatReadingRemoteImpl): HeatReadingRemote
  }