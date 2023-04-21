package com.yuzhny.mykis.data.remote.heat.meter

import com.yuzhny.mykis.data.remote.api.ApiService
import com.yuzhny.mykis.data.remote.core.Request
import com.yuzhny.mykis.data.remote.heat.meter.HeatMeterRemote
import com.yuzhny.mykis.domain.heat.meter.HeatMeterEntity
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HeatMeterRemoteImpl @Inject constructor(private val request: Request,
                                              private val apiService: ApiService
) : HeatMeterRemote {
        override fun getHeatMeter(
            addressId: Int,
            userId: Int,
            token: String
        ): Either<Failure, List<HeatMeterEntity>> {
            return request.make(
                apiService.getHeatMeter(
                    createGetHeatMeterMap(
                        addressId ,
                        userId ,
                        token
                    )
                )
            )
            {
                it.heatMeters
            }
        }



        private fun createGetHeatMeterMap(
            addressId: Int,
            userId: Int,
            token: String
        ): Map<String, String> {
            val map = HashMap<String, String>()
            map.put(ApiService.ADDRESS_ID, addressId.toString())
            map.put(ApiService.PARAM_USER_ID, userId.toString())
            map.put(ApiService.PARAM_TOKEN, token)
            return map
        }
}
