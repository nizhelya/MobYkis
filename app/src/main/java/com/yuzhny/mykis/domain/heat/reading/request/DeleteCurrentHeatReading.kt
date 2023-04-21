package com.yuzhny.mykis.domain.heat.reading.request

import com.yuzhny.mykis.data.HeatReadingRepositoryImpl
import com.yuzhny.mykis.data.WaterReadingRepositoryImpl
import com.yuzhny.mykis.data.remote.GetSimpleResponse
import com.yuzhny.mykis.domain.interactor.UseCase
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import javax.inject.Inject

class DeleteCurrentHeatReading @Inject constructor(
    private val heatReadingRepositoryImpl: HeatReadingRepositoryImpl
) : UseCase<GetSimpleResponse, Int>(){

    override suspend fun run(params:Int): Either<Failure, GetSimpleResponse> {
        return heatReadingRepositoryImpl.deleteCurrentHeatReading(params)
    }
}