package com.yuzhny.mykis.domain.heat.meter

import com.yuzhny.mykis.domain.family.request.BooleanInt
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import com.yuzhny.mykis.domain.water.meter.WaterMeterEntity

interface HeatMeterRepository {
   fun getHeatMeter(params: BooleanInt): Either<Failure, List<HeatMeterEntity>>
}