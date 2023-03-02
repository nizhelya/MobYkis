package com.yuzhny.mykis.data.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yuzhny.mykis.data.dao.AppartmentDao
import com.yuzhny.mykis.data.dao.FamilyDao
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import com.yuzhny.mykis.domain.family.FamilyEntity

@Database(entities = [AppartmentEntity::class , FamilyEntity::class] , version = 2 ,exportSchema = false)
abstract class AppDatabase:RoomDatabase() {
    abstract fun appartmentDao(): AppartmentDao
    abstract fun familyDao(): FamilyDao

        companion object{
           val DATABASE_NAME:String = "mykis_db"
        }
}