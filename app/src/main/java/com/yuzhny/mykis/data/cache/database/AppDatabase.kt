package com.yuzhny.mykis.data.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yuzhny.mykis.data.cache.dao.AppartmentDao
import com.yuzhny.mykis.data.cache.dao.FamilyDao
import com.yuzhny.mykis.data.cache.dao.PaymentDao
import com.yuzhny.mykis.data.cache.dao.ServiceDao
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import com.yuzhny.mykis.domain.family.FamilyEntity
import com.yuzhny.mykis.domain.payment.PaymentEntity
import com.yuzhny.mykis.domain.service.ServiceEntity

@Database(entities = [
    AppartmentEntity::class ,
    FamilyEntity::class ,
    ServiceEntity::class ,
    PaymentEntity::class] , version = 2 ,exportSchema = false)
abstract class AppDatabase:RoomDatabase() {
    abstract fun appartmentDao(): AppartmentDao
    abstract fun familyDao(): FamilyDao
    abstract fun serviceDao(): ServiceDao
    abstract fun paymentDao(): PaymentDao

        companion object{
           val DATABASE_NAME:String = "mykis_db"
        }
}