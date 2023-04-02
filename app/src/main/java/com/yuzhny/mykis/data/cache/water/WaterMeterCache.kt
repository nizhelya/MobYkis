package com.yuzhny.mykis.data.cache.water

import com.yuzhny.mykis.domain.water.WaterMeterEntity

interface WaterMeterCache {
    fun insertWaterMeter(waterMeters:List<WaterMeterEntity>)
    fun getWaterMeter(addressId:Int):List<WaterMeterEntity>
    fun deleteWaterMeter(addressIdList:List<Int>)
}