package com.yuzhny.mykis.data.remote.water

import com.yuzhny.mykis.domain.service.ServiceEntity
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import com.yuzhny.mykis.domain.water.WaterMeterEntity

interface WaterMeterRemote {
    fun getWaterMeter(addressId:Int , userId :Int , token : String): Either<Failure, List<WaterMeterEntity>>
}