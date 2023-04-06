package com.yuzhny.mykis.data.remote.water.reading

import com.yuzhny.mykis.data.remote.api.ApiService
import com.yuzhny.mykis.data.remote.core.Request
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import com.yuzhny.mykis.domain.water.reading.WaterReadingEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WaterReadingRemoteImpl  @Inject constructor(private val request: Request,
                                                  private val apiService: ApiService):WaterReadingRemote{
    override fun getWaterReadings(
        vodomerId: Int,
        userId: Int,
        token: String
    ): Either<Failure, List<WaterReadingEntity>> {
        return request.make(
            apiService.getWaterReadings(
                createGetWaterReadingMap(
                    vodomerId ,
                    userId ,
                    token
                )
            )
        )
        {
            it.waterReadings
        }
    }

    private fun createGetWaterReadingMap(
        vodomerId: Int,
        userId: Int,
        token: String
    ): Map<String, String> {
        val map = HashMap<String, String>()
        map.put(ApiService.VODOMER_ID, vodomerId.toString())
        map.put(ApiService.PARAM_USER_ID, userId.toString())
        map.put(ApiService.PARAM_TOKEN, token)
        return map
    }
}