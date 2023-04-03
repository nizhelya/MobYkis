package com.yuzhny.mykis.data

import android.util.Log
import com.yuzhny.mykis.data.cache.user.UserCache
import com.yuzhny.mykis.data.cache.water.WaterMeterCache
import com.yuzhny.mykis.data.remote.water.WaterMeterRemote
import com.yuzhny.mykis.domain.family.request.BooleanInt
import com.yuzhny.mykis.domain.type.*
import com.yuzhny.mykis.domain.water.WaterMeterEntity
import com.yuzhny.mykis.domain.water.WaterMeterRepository
import javax.inject.Inject

class WaterMeterRepositoryImpl @Inject constructor(
    private val waterMeterCache: WaterMeterCache ,
    private val waterMeterRemote: WaterMeterRemote,
    private val userCache: UserCache
) : WaterMeterRepository{
    override fun getWaterMeter(params: BooleanInt): Either<Failure, List<WaterMeterEntity>> {
        return userCache.getCurrentUser()
            .flatMap {
            return@flatMap if (params.needFetch) {
                Log.d("water" , "1")
                waterMeterRemote.getWaterMeter(params.addressId, it.userId, it.token)
            } else {
                Either.Right(
                    waterMeterCache.getWaterMeter(params.addressId)
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