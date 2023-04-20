package com.yuzhny.mykis.data.cache.heat.reading

import com.yuzhny.mykis.domain.heat.reading.HeatReadingEntity
import com.yuzhny.mykis.domain.water.meter.WaterMeterEntity
import com.yuzhny.mykis.domain.water.reading.WaterReadingEntity

interface HeatReadingCache {
    fun insertHeatReading(heatReading:List<HeatReadingEntity>)
    fun getHeatReading(teplomerId:Int):List<HeatReadingEntity>
    fun deleteAllReading()
}