package com.yuzhny.mykis.data.cache.heat

import com.yuzhny.mykis.domain.heat.meter.HeatMeterEntity

interface HeatMeterCache {
    fun insertHeatMeter(waterMeters:List<HeatMeterEntity>)
    fun getHeatMeter(addressId:Int):List<HeatMeterEntity>
    fun deleteHeatMeter(addressIdList:List<Int>)
}