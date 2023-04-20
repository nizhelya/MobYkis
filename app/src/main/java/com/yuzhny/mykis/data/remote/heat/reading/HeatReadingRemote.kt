package com.yuzhny.mykis.data.remote.heat.reading

import com.yuzhny.mykis.domain.heat.reading.HeatReadingEntity
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure


interface HeatReadingRemote {
    fun getHeatReadings(teplomerId:Int , userId :Int , token : String): Either<Failure, List<HeatReadingEntity>>
}