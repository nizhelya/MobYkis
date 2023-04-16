package com.yuzhny.mykis.data

import com.yuzhny.mykis.data.cache.heat.HeatMeterCache
import com.yuzhny.mykis.data.cache.user.UserCache
import com.yuzhny.mykis.data.cache.water.meter.WaterMeterCache
import com.yuzhny.mykis.data.remote.heat.HeatMeterRemote
import com.yuzhny.mykis.data.remote.water.meter.WaterMeterRemote
import com.yuzhny.mykis.domain.family.request.BooleanInt
import com.yuzhny.mykis.domain.heat.meter.HeatMeterEntity
import com.yuzhny.mykis.domain.heat.meter.HeatMeterRepository
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import com.yuzhny.mykis.domain.type.flatMap
import com.yuzhny.mykis.domain.type.onNext
import com.yuzhny.mykis.domain.water.meter.WaterMeterEntity
import com.yuzhny.mykis.domain.water.meter.WaterMeterRepository
import javax.inject.Inject

class HeatMeterRepositoryImpl @Inject constructor(
    private val heatMeterCache: HeatMeterCache,
    private val heatMeterRemote: HeatMeterRemote,
    private val userCache: UserCache
) : HeatMeterRepository {
    override fun getHeatMeter(params: BooleanInt): Either<Failure, List<HeatMeterEntity>> {
        return userCache.getCurrentUser()
            .flatMap {
                return@flatMap if (params.needFetch) {
                    heatMeterRemote.getHeatMeter(params.int, it.userId, it.token)
                } else {
                    Either.Right(
                        heatMeterCache.getHeatMeter(params.int)
                    )
                }
            }
            .onNext {
                it.map {
                    heatMeterCache.insertHeatMeter(listOf(it))
                }
            }
    }
}
