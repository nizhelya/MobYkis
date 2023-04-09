package com.yuzhny.mykis.data.cache.water.reading

import com.yuzhny.mykis.domain.water.reading.WaterReadingEntity

interface WaterReadingCache {
    fun insertWaterReading(waterReading:List<WaterReadingEntity>)
    fun getWaterReading(vodomerId:Int):List<WaterReadingEntity>
    fun deleteAllReading()
}