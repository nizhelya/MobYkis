package com.yuzhny.mykis.domain.heat.meter.request

import com.yuzhny.mykis.data.HeatMeterRepositoryImpl
import com.yuzhny.mykis.data.WaterMeterRepositoryImpl
import com.yuzhny.mykis.domain.family.request.BooleanInt
import com.yuzhny.mykis.domain.heat.meter.HeatMeterEntity
import com.yuzhny.mykis.domain.interactor.UseCase
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import com.yuzhny.mykis.domain.water.meter.WaterMeterEntity
import javax.inject.Inject

class GetHeatMeter @Inject constructor(
    private val heatMeterRepositoryImpl: HeatMeterRepositoryImpl
) : UseCase<List<HeatMeterEntity>, BooleanInt>(){

    override suspend fun run(params: BooleanInt): Either<Failure, List<HeatMeterEntity>> {
        return heatMeterRepositoryImpl.getHeatMeter(params)
    }
}