package com.yuzhny.mykis.data.remote.heat.meter

import com.yuzhny.mykis.domain.heat.meter.HeatMeterEntity
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import com.yuzhny.mykis.domain.water.meter.WaterMeterEntity

interface HeatMeterRemote {
    fun getHeatMeter(addressId:Int , userId :Int , token : String): Either<Failure, List<HeatMeterEntity>>
}