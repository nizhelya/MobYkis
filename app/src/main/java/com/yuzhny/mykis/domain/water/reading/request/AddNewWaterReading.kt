package com.yuzhny.mykis.domain.water.reading.request

import com.yuzhny.mykis.data.WaterReadingRepositoryImpl
import com.yuzhny.mykis.data.remote.GetSimpleResponse
import com.yuzhny.mykis.domain.family.request.BooleanInt
import com.yuzhny.mykis.domain.interactor.UseCase
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import com.yuzhny.mykis.domain.water.reading.WaterReadingEntity
import javax.inject.Inject

class AddNewWaterReading @Inject constructor(
    private val waterReadingRepositoryImpl: WaterReadingRepositoryImpl
) : UseCase<GetSimpleResponse, AddReadingParams>(){

    override suspend fun run(params: AddReadingParams): Either<Failure, GetSimpleResponse> {
        return waterReadingRepositoryImpl.addNewWaterReading(params)
    }
}
data class AddReadingParams(
    val meterId:Int ,
    val newValue: Int ,
    val currentValue :Int
)