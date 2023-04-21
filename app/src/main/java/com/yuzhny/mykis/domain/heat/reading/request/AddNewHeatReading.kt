package com.yuzhny.mykis.domain.heat.reading.request

import com.yuzhny.mykis.data.HeatReadingRepositoryImpl
import com.yuzhny.mykis.data.WaterReadingRepositoryImpl
import com.yuzhny.mykis.data.remote.GetSimpleResponse
import com.yuzhny.mykis.domain.interactor.UseCase
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import com.yuzhny.mykis.domain.water.reading.request.AddReadingParams
import javax.inject.Inject

class AddNewHeatReading @Inject constructor(
    private val heatReadingRepositoryImpl: HeatReadingRepositoryImpl
) : UseCase<GetSimpleResponse, AddHeatReadingParams>(){

    override suspend fun run(params: AddHeatReadingParams): Either<Failure, GetSimpleResponse> {
        return heatReadingRepositoryImpl.addNewHeatReading(params)
    }
}

data class AddHeatReadingParams(
    val meterId:Int ,
    val newValue: Double ,
    val currentValue :Double
)