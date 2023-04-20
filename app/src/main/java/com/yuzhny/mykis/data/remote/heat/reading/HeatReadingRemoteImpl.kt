package com.yuzhny.mykis.data.remote.heat.reading

import com.yuzhny.mykis.data.remote.api.ApiService
import com.yuzhny.mykis.data.remote.core.Request
import com.yuzhny.mykis.data.remote.water.reading.WaterReadingRemote
import com.yuzhny.mykis.domain.heat.reading.HeatReadingEntity
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HeatReadingRemoteImpl @Inject constructor(private val request: Request,
                                                private val apiService: ApiService
): HeatReadingRemote {
    override fun getHeatReadings(
        teplomerId: Int,
        userId: Int,
        token: String
    ): Either<Failure, List<HeatReadingEntity>> {
        return request.make(
            apiService.getHeatReadings(
                createGetHeatReadingMap(
                    teplomerId ,
                    userId ,
                    token
                )
            )
        )
        {
            it.heatReadings
        }
    }

    private fun createGetHeatReadingMap(
        teplomerId: Int,
        userId: Int,
        token: String
    ): Map<String, String> {
        val map = HashMap<String, String>()
        map.put(ApiService.TEPLOMER_ID, teplomerId.toString())
        map.put(ApiService.PARAM_USER_ID, userId.toString())
        map.put(ApiService.PARAM_TOKEN, token)
        return map
    }
}