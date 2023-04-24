package com.yuzhny.mykis.data

import com.yuzhny.mykis.data.cache.user.UserCache
import com.yuzhny.mykis.data.cache.water.reading.WaterReadingCache
import com.yuzhny.mykis.data.remote.GetSimpleResponse
import com.yuzhny.mykis.data.remote.water.reading.WaterReadingRemote
import com.yuzhny.mykis.domain.family.request.BooleanInt
import com.yuzhny.mykis.domain.type.*
import com.yuzhny.mykis.domain.water.reading.WaterReadingEntity
import com.yuzhny.mykis.domain.water.reading.WaterReadingRepository
import com.yuzhny.mykis.domain.water.reading.request.AddReadingParams
import io.reactivex.rxjava3.internal.util.HalfSerializer.onNext
import javax.inject.Inject

class WaterReadingRepositoryImpl @Inject constructor(
    private val waterReadingCache: WaterReadingCache,
    private val waterReadingRemote: WaterReadingRemote,
    private val userCache: UserCache
) : WaterReadingRepository {
    override fun getWaterReading(params: BooleanInt): Either<Failure, List<WaterReadingEntity>> {
        return userCache.getCurrentUser()
            .flatMap {
                return@flatMap if (params.needFetch) {
                    waterReadingRemote.getWaterReadings(params.int, it.userId, it.token)
                } else {
                    Either.Right(
                        waterReadingCache.getWaterReading(params.int)
                    )
                }
            }
//            .onNext {
//                waterReadingCache.deleteAllReading()
//            }
            .map {
                it.sortedByDescending { it.pokId }
            }
            .onNext {
                it.map {
                    waterReadingCache.insertWaterReading(listOf(it))
                }
            }
    }

    override fun addNewWaterReading(params: AddReadingParams): Either<Failure, GetSimpleResponse> {
        return  userCache.getCurrentUser()
            .flatMap {
                return@flatMap waterReadingRemote.addNewWaterReading(
                    params.meterId,
                    params.newValue,
                    params.currentValue,
                    it.userId,
                    it.token
                )
            }
    }

    override fun deleteCurrentWaterReading(params: Int): Either<Failure, GetSimpleResponse> {
        return  userCache.getCurrentUser()
            .flatMap {
                return@flatMap waterReadingRemote.deleteCurrentWaterReading(
                    params , it.userId , it.token
                )
            }
    }
}