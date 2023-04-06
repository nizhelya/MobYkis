package com.yuzhny.mykis.data.cache.water.meter

import com.yuzhny.mykis.domain.water.meter.WaterMeterEntity

interface WaterMeterCache {
    fun insertWaterMeter(waterMeters:List<WaterMeterEntity>)
    fun getWaterMeter(addressId:Int):List<WaterMeterEntity>
    fun deleteWaterMeter(addressIdList:List<Int>)
}