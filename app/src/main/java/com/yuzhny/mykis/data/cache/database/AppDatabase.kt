package com.yuzhny.mykis.data.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yuzhny.mykis.data.cache.dao.*
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import com.yuzhny.mykis.domain.family.FamilyEntity
import com.yuzhny.mykis.domain.heat.meter.HeatMeterEntity
import com.yuzhny.mykis.domain.payment.PaymentEntity
import com.yuzhny.mykis.domain.service.ServiceEntity
import com.yuzhny.mykis.domain.water.meter.WaterMeterEntity
import com.yuzhny.mykis.domain.water.reading.WaterReadingEntity

@Database(entities = [
    AppartmentEntity::class ,
    FamilyEntity::class ,
    ServiceEntity::class ,
    PaymentEntity::class ,
    WaterMeterEntity::class ,
    WaterReadingEntity::class ,
    HeatMeterEntity::class] , version = 3 ,exportSchema = false)
abstract class AppDatabase:RoomDatabase() {
    abstract fun appartmentDao(): AppartmentDao
    abstract fun familyDao(): FamilyDao
    abstract fun serviceDao(): ServiceDao
    abstract fun paymentDao(): PaymentDao
    abstract fun waterMeterDao(): WaterMeterDao
    abstract fun waterReadingDao(): WaterReadingDao
    abstract fun heatMeterDao(): HeatMeterDao

        companion object{
           val DATABASE_NAME:String = "mykis_db"
        }
}