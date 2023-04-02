package com.yuzhny.mykis.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yuzhny.mykis.domain.family.FamilyEntity
import com.yuzhny.mykis.domain.water.WaterMeterEntity

@Dao
interface WaterMeterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWaterMeter(waterMeter:List<WaterMeterEntity>)
    @Query("select * from water_meter where address_id = :addressId")
    fun getWaterMeter(addressId:Int): List<WaterMeterEntity>
    @Query("delete from water_meter where address_id not in (:addressId)")
    fun deleteWaterMeter(addressId: List<Int>)
}