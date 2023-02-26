package com.yuzhny.mykis.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import com.yuzhny.mykis.domain.family.FamilyEntity

@Dao
interface FamilyDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFamily(appartment:List<FamilyEntity>)
    @Query("select * from family")
    fun getFamilyFromFlat(): List<FamilyEntity>
}