package com.yuzhny.mykis.data.cache.heat

import com.yuzhny.mykis.data.cache.dao.HeatMeterDao
import com.yuzhny.mykis.domain.heat.meter.HeatMeterEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HeatMeterCacheImpl @Inject constructor(
    private val heatMeterDao: HeatMeterDao
) :HeatMeterCache{
    override fun insertHeatMeter(waterMeters: List<HeatMeterEntity>) {
        heatMeterDao.insertHeatMeter(waterMeters)
    }

    override fun getHeatMeter(addressId: Int): List<HeatMeterEntity> {
        return heatMeterDao.getHeatMeter(addressId)
    }

    override fun deleteHeatMeter(addressIdList: List<Int>) {
        heatMeterDao.deleteHeatMeter(addressIdList)
    }
}