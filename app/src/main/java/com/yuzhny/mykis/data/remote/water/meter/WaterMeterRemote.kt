package com.yuzhny.mykis.data.remote.water.meter

import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import com.yuzhny.mykis.domain.water.meter.WaterMeterEntity

interface WaterMeterRemote {
    fun getWaterMeter(addressId:Int , userId :Int , token : String): Either<Failure, List<WaterMeterEntity>>
}