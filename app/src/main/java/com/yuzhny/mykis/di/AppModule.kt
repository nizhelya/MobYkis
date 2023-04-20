package com.yuzhny.mykis.di


import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.yuzhny.mykis.data.*
import com.yuzhny.mykis.data.cache.appartment.AppartmentCache
import com.yuzhny.mykis.data.cache.dao.*
import com.yuzhny.mykis.data.cache.database.AppDatabase
import com.yuzhny.mykis.data.cache.family.FamilyCache
import com.yuzhny.mykis.data.cache.heat.HeatMeterCache
import com.yuzhny.mykis.data.cache.heat.reading.HeatReadingCache
import com.yuzhny.mykis.data.cache.user.UserCache
import com.yuzhny.mykis.data.cache.payment.PaymentCache
import com.yuzhny.mykis.data.cache.service.ServiceCache
import com.yuzhny.mykis.data.cache.water.meter.WaterMeterCache
import com.yuzhny.mykis.data.cache.water.reading.WaterReadingCache
import com.yuzhny.mykis.data.remote.address.AddressRemote
import com.yuzhny.mykis.data.remote.appartment.AppartmentRemote
import com.yuzhny.mykis.data.remote.family.FamilyRemote
import com.yuzhny.mykis.data.remote.api.ApiService
import com.yuzhny.mykis.data.remote.api.ApiService.Companion.BASE_URL
import com.yuzhny.mykis.data.remote.heat.HeatMeterRemote
import com.yuzhny.mykis.data.remote.heat.reading.HeatReadingRemote
import com.yuzhny.mykis.data.remote.payment.PaymentRemote
import com.yuzhny.mykis.data.remote.service.ServiceRemote
import com.yuzhny.mykis.data.remote.water.meter.WaterMeterRemote
import com.yuzhny.mykis.data.remote.water.reading.WaterReadingRemote
import com.yuzhny.mykis.domain.address.AddressRepository
import com.yuzhny.mykis.domain.appartment.AppartmentRepository
import com.yuzhny.mykis.domain.family.FamilyRepository
import com.yuzhny.mykis.domain.heat.meter.HeatMeterRepository
import com.yuzhny.mykis.domain.heat.reading.HeatReadingRepository
import com.yuzhny.mykis.domain.payment.PaymentRepository
import com.yuzhny.mykis.domain.service.ServiceRepository
import com.yuzhny.mykis.domain.water.meter.WaterMeterRepository
import com.yuzhny.mykis.domain.water.reading.WaterReadingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi,okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)


    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }



    @Provides
    fun provideAppartmentDao(db: AppDatabase): AppartmentDao {
        return db.appartmentDao()
    }

    @Provides
    fun provideFamilyDao(db: AppDatabase): FamilyDao {
        return db.familyDao()
    }

    @Provides
    fun provideServiceDao(db: AppDatabase): ServiceDao {
        return db.serviceDao()
    }
    @Provides
    fun providePaymentDao(db: AppDatabase): PaymentDao {
        return db.paymentDao()
    }
    @Provides
    fun provideWaterMeterDao(db: AppDatabase):WaterMeterDao{
        return  db.waterMeterDao()
    }
    @Provides
    fun provideWaterReadingDao(db: AppDatabase):WaterReadingDao{
        return  db.waterReadingDao()
    }
    @Provides
    fun provideHeatMeterDao(db: AppDatabase):HeatMeterDao{
        return  db.heatMeterDao()
    }

    @Provides
    fun provideHeatReadingDao(db: AppDatabase):HeatReadingDao{
        return  db.heatReadingDao()
    }
    @Singleton
    @Provides
    fun provideAppartmentRepository(
        appartmentRemote: AppartmentRemote,
        userCache: UserCache,
        familyCache: FamilyCache,
        serviceCache: ServiceCache,
        paymentCache: PaymentCache,
        appartmentCache: AppartmentCache
    ): AppartmentRepository {
        return AppartmentRepositoryImpl(appartmentRemote,appartmentCache,familyCache,serviceCache, paymentCache,userCache)
    }

    @Singleton
    @Provides
    fun provideAddressRepository(
        addressRemote: AddressRemote,
        userCache: UserCache,
    ): AddressRepository {
        return AddressRepositoryImpl(addressRemote,userCache)
    }
    @Singleton
    @Provides
    fun provideFamilyRepository(
        familyCache: FamilyCache,
        familyRemote: FamilyRemote,
        userCache: UserCache
    ):FamilyRepository {
        return FamilyRepositoryImpl(familyCache , familyRemote, userCache )
    }

    @Singleton
    @Provides
    fun provideServiceRepository(
        serviceCache: ServiceCache,
        serviceRemote: ServiceRemote,
        userCache: UserCache
    ): ServiceRepository {
        return ServiceRepositoryImpl(serviceCache , serviceRemote, userCache )
    }

    @Singleton
    @Provides
    fun providePaymentRepository(
        paymentCache: PaymentCache,
        paymentRemote: PaymentRemote,
        userCache: UserCache
    ): PaymentRepository {
        return PaymentRepositoryImpl(paymentCache , paymentRemote, userCache )
    }
    @Singleton
    @Provides
    fun provideWaterMeterRepository(
        waterMeterCache: WaterMeterCache,
        waterMeterRemote: WaterMeterRemote,
        userCache: UserCache
    ): WaterMeterRepository {
        return  WaterMeterRepositoryImpl(waterMeterCache ,waterMeterRemote , userCache)
    }
    @Singleton
    @Provides
    fun provideWaterReadingRepository(
        waterReadingCache :WaterReadingCache,
        waterReadingRemote: WaterReadingRemote,
        userCache: UserCache
    ): WaterReadingRepository {
        return  WaterReadingRepositoryImpl(waterReadingCache ,waterReadingRemote , userCache)
    }
    @Singleton
    @Provides
    fun provideHeatMeterRepository(
        heatMeterCache: HeatMeterCache,
        heatMeterRemote: HeatMeterRemote,
        userCache: UserCache
    ): HeatMeterRepository {
        return  HeatMeterRepositoryImpl(heatMeterCache ,heatMeterRemote , userCache)
    }
    @Singleton
    @Provides
    fun provideHeatReadingRepository(
        heatReadingCache : HeatReadingCache,
        heatReadingRemote: HeatReadingRemote,
        userCache: UserCache
    ): HeatReadingRepository {
        return  HeatReadingRepositoryImpl(heatReadingCache ,heatReadingRemote , userCache)
    }
}