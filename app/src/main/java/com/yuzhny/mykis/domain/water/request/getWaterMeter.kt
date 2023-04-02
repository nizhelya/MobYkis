package com.yuzhny.mykis.domain.water.request

import com.yuzhny.mykis.data.WaterMeterRepositoryImpl
import com.yuzhny.mykis.domain.family.request.BooleanInt
import com.yuzhny.mykis.domain.interactor.UseCase
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import com.yuzhny.mykis.domain.water.WaterMeterEntity
import javax.inject.Inject

class GetWaterMeter @Inject constructor(
    private val waterMeterRepositoryImpl: WaterMeterRepositoryImpl
) : UseCase<List<WaterMeterEntity>, BooleanInt>(){

    override suspend fun run(params: BooleanInt ): Either<Failure, List<WaterMeterEntity>> {
       return waterMeterRepositoryImpl.getWaterMeter(params)
    }
}