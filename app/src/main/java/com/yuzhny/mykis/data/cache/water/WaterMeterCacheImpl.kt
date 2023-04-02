package com.yuzhny.mykis.data.cache.water

import com.yuzhny.mykis.data.cache.dao.WaterMeterDao
import com.yuzhny.mykis.domain.water.WaterMeterEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WaterMeterCacheImpl @Inject constructor(
    private val waterMeterDao: WaterMeterDao
) :WaterMeterCache {
    override fun insertWaterMeter(waterMeters: List<WaterMeterEntity>) {
        waterMeterDao.insertWaterMeter(waterMeters)
    }

    override fun getWaterMeter(addressId: Int): List<WaterMeterEntity> {
        return waterMeterDao.getWaterMeter(addressId)
    }

    override fun deleteWaterMeter(addressIdList: List<Int>) {
        waterMeterDao.deleteWaterMeter(addressIdList)
    }
}