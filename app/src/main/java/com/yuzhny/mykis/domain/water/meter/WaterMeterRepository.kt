package com.yuzhny.mykis.domain.water.meter


import com.yuzhny.mykis.domain.family.request.BooleanInt
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import com.yuzhny.mykis.domain.water.meter.WaterMeterEntity

interface WaterMeterRepository {
    fun getWaterMeter(params: BooleanInt): Either<Failure, List<WaterMeterEntity>>
}