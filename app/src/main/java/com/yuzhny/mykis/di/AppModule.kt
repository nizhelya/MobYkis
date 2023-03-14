package com.yuzhny.mykis.di


import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.yuzhny.mykis.data.AddressRepositoryImpl
import com.yuzhny.mykis.data.AppartmentRepositoryImpl
import com.yuzhny.mykis.data.FamilyRepositoryImpl
import com.yuzhny.mykis.data.ServiceRepositoryImpl
import com.yuzhny.mykis.data.cache.appartment.AppartmentCache
import com.yuzhny.mykis.data.cache.database.AppDatabase
import com.yuzhny.mykis.data.cache.family.FamilyCache
import com.yuzhny.mykis.data.cache.user.UserCache
import com.yuzhny.mykis.data.cache.dao.AppartmentDao
import com.yuzhny.mykis.data.cache.dao.FamilyDao
import com.yuzhny.mykis.data.cache.dao.ServiceDao
import com.yuzhny.mykis.data.cache.service.ServiceCache
import com.yuzhny.mykis.data.remote.address.AddressRemote
import com.yuzhny.mykis.data.remote.appartment.AppartmentRemote
import com.yuzhny.mykis.data.remote.family.FamilyRemote
import com.yuzhny.mykis.data.remote.api.ApiService
import com.yuzhny.mykis.data.remote.api.ApiService.Companion.BASE_URL
import com.yuzhny.mykis.data.remote.service.ServiceRemote
import com.yuzhny.mykis.domain.address.AddressRepository
import com.yuzhny.mykis.domain.appartment.AppartmentRepository
import com.yuzhny.mykis.domain.family.FamilyRepository
import com.yuzhny.mykis.domain.service.ServiceRepository
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

    @Singleton
    @Provides
    fun provideAppartmentRepository(
        appartmentRemote: AppartmentRemote,
        userCache: UserCache,
        familyCache: FamilyCache,
        appartmentCache: AppartmentCache
    ): AppartmentRepository {
        return AppartmentRepositoryImpl(appartmentRemote,appartmentCache,familyCache,userCache)
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


}