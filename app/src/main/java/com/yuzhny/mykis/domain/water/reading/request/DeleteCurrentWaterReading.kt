package com.yuzhny.mykis.domain.water.reading.request

import com.yuzhny.mykis.data.WaterReadingRepositoryImpl
import com.yuzhny.mykis.data.remote.GetSimpleResponse
import com.yuzhny.mykis.domain.interactor.UseCase
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import javax.inject.Inject

class DeleteCurrentWaterReading @Inject constructor(
    private val waterReadingRepositoryImpl: WaterReadingRepositoryImpl
) : UseCase<GetSimpleResponse, Int>(){

    override suspend fun run(params:Int): Either<Failure, GetSimpleResponse> {
        return waterReadingRepositoryImpl.deleteCurrentWaterReading(params)
    }
}