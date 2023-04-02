package com.yuzhny.mykis.domain.water


import com.yuzhny.mykis.domain.family.request.BooleanInt
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure

interface WaterMeterRepository {
    fun getWaterMeter(params: BooleanInt): Either<Failure, List<WaterMeterEntity>>
}