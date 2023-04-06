package com.yuzhny.mykis.data.cache.water.reading

import com.yuzhny.mykis.data.cache.dao.WaterReadingDao
import com.yuzhny.mykis.domain.water.reading.WaterReadingEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WaterReadingCacheImpl @Inject constructor(
    private val waterReadingDao: WaterReadingDao
) : WaterReadingCache {
    override fun insertWaterReading(waterReading: List<WaterReadingEntity>) {
        waterReadingDao.insertWaterReading(waterReading)
    }

    override fun getWaterReading(vodomerId: Int): List<WaterReadingEntity> {
        return waterReadingDao.getWaterReading(vodomerId)
    }
}