package com.yuzhny.mykis.domain.heat.reading.request

import com.yuzhny.mykis.data.HeatReadingRepositoryImpl
import com.yuzhny.mykis.domain.family.request.BooleanInt
import com.yuzhny.mykis.domain.heat.reading.HeatReadingEntity
import com.yuzhny.mykis.domain.interactor.UseCase
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import javax.inject.Inject

class GetHeatReading @Inject constructor(
    private val heatReadingRepositoryImpl: HeatReadingRepositoryImpl
) : UseCase<List<HeatReadingEntity>, BooleanInt>(){

    override suspend fun run(params: BooleanInt): Either<Failure, List<HeatReadingEntity>> {
        return heatReadingRepositoryImpl.getHeatReading(params)
    }
}