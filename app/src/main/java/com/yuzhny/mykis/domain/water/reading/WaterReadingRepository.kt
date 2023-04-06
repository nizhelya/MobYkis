package com.yuzhny.mykis.domain.water.reading

import com.yuzhny.mykis.domain.family.request.BooleanInt
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import com.yuzhny.mykis.domain.water.reading.WaterReadingEntity

interface WaterReadingRepository {
    fun getWaterReading(params: BooleanInt): Either<Failure, List<WaterReadingEntity>>
}