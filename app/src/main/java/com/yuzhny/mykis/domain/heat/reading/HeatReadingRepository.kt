package com.yuzhny.mykis.domain.heat.reading

import com.yuzhny.mykis.domain.family.request.BooleanInt
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import com.yuzhny.mykis.domain.water.reading.WaterReadingEntity

interface HeatReadingRepository {
    fun getHeatReading(params: BooleanInt): Either<Failure, List<HeatReadingEntity>>
}