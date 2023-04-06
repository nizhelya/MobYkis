package com.yuzhny.mykis.data

import com.yuzhny.mykis.data.cache.user.UserCache
import com.yuzhny.mykis.data.cache.water.meter.WaterMeterCache
import com.yuzhny.mykis.data.remote.water.meter.WaterMeterRemote
import com.yuzhny.mykis.domain.family.request.BooleanInt
import com.yuzhny.mykis.domain.type.*
import com.yuzhny.mykis.domain.water.meter.WaterMeterEntity
import com.yuzhny.mykis.domain.water.meter.WaterMeterRepository
import javax.inject.Inject

class WaterMeterRepositoryImpl @Inject constructor(
    private val waterMeterCache: WaterMeterCache,
    private val waterMeterRemote: WaterMeterRemote,
    private val userCache: UserCache
) : WaterMeterRepository {
    override fun getWaterMeter(params: BooleanInt): Either<Failure, List<WaterMeterEntity>> {
        return userCache.getCurrentUser()
            .flatMap {
            return@flatMap if (params.needFetch) {
                waterMeterRemote.getWaterMeter(params.int, it.userId, it.token)
            } else {
                Either.Right(
                    waterMeterCache.getWaterMeter(params.int)
                )
            }
        }
            .onNext {
                it.map {
                   waterMeterCache.insertWaterMeter(listOf(it))
                }
            }
    }
}