package com.yuzhny.mykis.domain.heat.reading

import com.yuzhny.mykis.data.remote.GetSimpleResponse
import com.yuzhny.mykis.domain.family.request.BooleanInt
import com.yuzhny.mykis.domain.heat.reading.request.AddHeatReadingParams
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import com.yuzhny.mykis.domain.water.reading.WaterReadingEntity
import com.yuzhny.mykis.domain.water.reading.request.AddReadingParams

interface HeatReadingRepository {
    fun getHeatReading(params: BooleanInt): Either<Failure, List<HeatReadingEntity>>
    fun addNewHeatReading(params: AddHeatReadingParams): Either<Failure, GetSimpleResponse>
    fun deleteCurrentHeatReading(params:Int): Either<Failure, GetSimpleResponse>

}