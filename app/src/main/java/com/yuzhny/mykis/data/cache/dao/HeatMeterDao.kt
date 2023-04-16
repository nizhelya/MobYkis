package com.yuzhny.mykis.data.cache.dao


import android.os.Parcel
import android.os.Parcelable
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yuzhny.mykis.domain.heat.meter.HeatMeterEntity
import com.yuzhny.mykis.domain.water.meter.WaterMeterEntity

@Dao
interface HeatMeterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHeatMeter(waterMeter:List<HeatMeterEntity>)
    @Query("select *   from heat_meter where address_id = :addressId order by work")
    fun getHeatMeter(addressId:Int): List<HeatMeterEntity>
    @Query("delete from heat_meter where address_id not in (:addressId)")
    fun deleteHeatMeter(addressId: List<Int>)
}