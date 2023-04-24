package com.yuzhny.mykis.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yuzhny.mykis.domain.heat.reading.HeatReadingEntity
import com.yuzhny.mykis.domain.water.reading.WaterReadingEntity


@Dao
interface HeatReadingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHeatReading(heatMeter:List<HeatReadingEntity>)
    @Query("select * from heat_reading where teplomer_id = :teplomerId")
    fun getHeatReading(teplomerId:Int): List<HeatReadingEntity>
    @Query("delete from water_reading")
    fun deleteAllReadings()
    @Query("delete from heat_reading where address_id not in (:addressId)")
    fun deleteReadingFromFlat(addressId: List<Int>)
}