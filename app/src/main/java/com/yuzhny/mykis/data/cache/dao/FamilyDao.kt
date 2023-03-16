package com.yuzhny.mykis.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import com.yuzhny.mykis.domain.family.FamilyEntity

@Dao
interface FamilyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFamily(appartment:List<FamilyEntity>)
    @Query("select * from family where address_id = :addressId")
    fun getFamilyFromFlat(addressId:Int): List<FamilyEntity>
    @Query("delete from family where address_id not in (:addressId)")
    fun deleteFamilyFromFlat(addressId: List<Int>)
}