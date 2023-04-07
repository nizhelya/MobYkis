package com.yuzhny.mykis.data.remote.water.reading

import com.yuzhny.mykis.data.remote.GetSimpleResponse
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import com.yuzhny.mykis.domain.water.reading.WaterReadingEntity

interface WaterReadingRemote {
    fun getWaterReadings(vodomerId:Int , userId :Int , token : String): Either<Failure, List<WaterReadingEntity>>
    fun addNewWaterReading( vodomerId:Int, newValue:Int,  currentValue:Int, userId: Int ,  token: String): Either<Failure , GetSimpleResponse>
}