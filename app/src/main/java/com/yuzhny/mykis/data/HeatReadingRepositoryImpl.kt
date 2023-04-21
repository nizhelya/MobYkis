package com.yuzhny.mykis.data

import com.yuzhny.mykis.data.cache.heat.reading.HeatReadingCache
import com.yuzhny.mykis.data.cache.user.UserCache
import com.yuzhny.mykis.data.remote.GetSimpleResponse
import com.yuzhny.mykis.data.remote.heat.reading.HeatReadingRemote
import com.yuzhny.mykis.domain.family.request.BooleanInt
import com.yuzhny.mykis.domain.heat.reading.HeatReadingEntity
import com.yuzhny.mykis.domain.heat.reading.HeatReadingRepository
import com.yuzhny.mykis.domain.heat.reading.request.AddHeatReadingParams
import com.yuzhny.mykis.domain.type.*
import com.yuzhny.mykis.domain.water.reading.request.AddReadingParams
import javax.inject.Inject

class HeatReadingRepositoryImpl @Inject constructor(
    private val heatReadingCache: HeatReadingCache,
    private val heatReadingRemote: HeatReadingRemote,
    private val userCache: UserCache
) : HeatReadingRepository {
    override fun getHeatReading(params: BooleanInt): Either<Failure, List<HeatReadingEntity>> {
        return userCache.getCurrentUser()
            .flatMap {
                return@flatMap if (params.needFetch) {
                    heatReadingRemote.getHeatReadings(params.int, it.userId, it.token)
                } else {
                    Either.Right(
                        heatReadingCache.getHeatReading(params.int)
                    )
                }
            }
            .onNext {
                heatReadingCache.deleteAllReading()
            }
            .map {
                it.sortedByDescending { it.pokId }
            }
            .onNext {
                it.map {
                    heatReadingCache.insertHeatReading(listOf(it))
                }
            }
    }

    override fun addNewHeatReading(params: AddHeatReadingParams): Either<Failure, GetSimpleResponse> {
        return  userCache.getCurrentUser()
            .flatMap {
                return@flatMap heatReadingRemote.addNewHeatReading(
                    params.meterId,
                    params.newValue,
                    params.currentValue,
                    it.userId,
                    it.token
                )
            }
    }

    override fun deleteCurrentHeatReading(params: Int): Either<Failure, GetSimpleResponse> {
        return  userCache.getCurrentUser()
            .flatMap {
                return@flatMap heatReadingRemote.deleteCurrentHeatReading(
                    params , it.userId , it.token
                )
            }
    }

}