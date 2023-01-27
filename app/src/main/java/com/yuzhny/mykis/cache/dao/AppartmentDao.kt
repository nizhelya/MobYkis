package com.yuzhny.mykis.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.yuzhny.mykis.domain.appartment.AppartmentEntity

@Dao
interface AppartmentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAppartment(appartment:List<AppartmentEntity>)
}