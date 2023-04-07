package com.yuzhny.mykis.domain.water.reading

import com.yuzhny.mykis.data.remote.GetSimpleResponse
import com.yuzhny.mykis.domain.family.request.BooleanInt
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import com.yuzhny.mykis.domain.water.reading.WaterReadingEntity
import com.yuzhny.mykis.domain.water.reading.request.AddReadingParams

interface WaterReadingRepository {
    fun getWaterReading(params: BooleanInt): Either<Failure, List<WaterReadingEntity>>
    fun addNewWaterReading(params:AddReadingParams): Either<Failure, GetSimpleResponse>
}