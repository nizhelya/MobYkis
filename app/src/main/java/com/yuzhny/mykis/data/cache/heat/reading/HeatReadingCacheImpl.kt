package com.yuzhny.mykis.data.cache.heat.reading

import com.yuzhny.mykis.data.cache.dao.HeatReadingDao
import com.yuzhny.mykis.domain.heat.reading.HeatReadingEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HeatReadingCacheImpl @Inject constructor(
    private val heatReadingDao: HeatReadingDao
):HeatReadingCache {
    override fun insertHeatReading(heatReading: List<HeatReadingEntity>) {
        heatReadingDao.insertHeatReading(heatReading)
    }

    override fun getHeatReading(teplomerId: Int): List<HeatReadingEntity> {
        return heatReadingDao.getHeatReading(teplomerId)
    }

    override fun deleteAllReading() {
        heatReadingDao.deleteAllReadings()
    }

    override fun deleteReadingFromFlat(addressId: List<Int>) {
        heatReadingDao.deleteReadingFromFlat(addressId)
    }


}