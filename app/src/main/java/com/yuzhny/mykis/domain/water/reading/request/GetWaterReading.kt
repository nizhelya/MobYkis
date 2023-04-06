package com.yuzhny.mykis.domain.water.reading.request


import com.yuzhny.mykis.data.WaterReadingRepositoryImpl
import com.yuzhny.mykis.domain.family.request.BooleanInt
import com.yuzhny.mykis.domain.interactor.UseCase
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import com.yuzhny.mykis.domain.water.reading.WaterReadingEntity
import javax.inject.Inject

class GetWaterReading @Inject constructor(
    private val waterReadingRepositoryImpl: WaterReadingRepositoryImpl
) : UseCase<List<WaterReadingEntity>, BooleanInt>(){

    override suspend fun run(params: BooleanInt): Either<Failure, List<WaterReadingEntity>> {
        return waterReadingRepositoryImpl.getWaterReading(params)
    }
}