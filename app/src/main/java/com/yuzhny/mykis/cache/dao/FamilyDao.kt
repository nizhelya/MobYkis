package com.yuzhny.mykis.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import com.yuzhny.mykis.domain.family.FamilyEntity

@Dao
interface FamilyDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFamily(appartment:List<FamilyEntity>)
}