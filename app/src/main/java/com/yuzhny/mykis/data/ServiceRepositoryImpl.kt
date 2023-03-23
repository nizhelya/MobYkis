package com.yuzhny.mykis.data

import com.yuzhny.mykis.data.cache.service.ServiceCache
import com.yuzhny.mykis.data.cache.user.UserCache
import com.yuzhny.mykis.data.remote.service.ServiceRemote
import com.yuzhny.mykis.domain.service.ServiceEntity
import com.yuzhny.mykis.domain.service.ServiceRepository
import com.yuzhny.mykis.domain.service.request.ServiceParams
import com.yuzhny.mykis.domain.type.*
import io.reactivex.rxjava3.internal.util.HalfSerializer.onNext
import javax.inject.Inject

class ServiceRepositoryImpl @Inject constructor(
    private val serviceCache: ServiceCache,
    private val serviceRemote: ServiceRemote,
    private val userCache: UserCache
) : ServiceRepository {
    override fun getFlatService(params: ServiceParams): Either<Failure, List<ServiceEntity>> {
        return userCache.getCurrentUser()
                .flatMap {
                    return@flatMap if (params.needFetch) {
                        serviceRemote.getFlatServices(
                            params.addressId,
                            params.houseId,
                            params.qty,
                            params.service,
                            params.total,
                            it.userId,
                            it.token
                        )
                    } else {
                        Either.Right(
                            serviceCache.getServiceFromFlat(params.addressId)
                        )
                    }
                }
                .onNext {
                    it.map {
                        serviceCache.addService(listOf(it))
                    }
                }
        }
//    else{
//            userCache.getCurrentUser()
//                .flatMap { return@flatMap serviceRemote.getFlatServices(
//                    params.addressId ,
//                    params.houseId,
//                    params.qty,
//                    params.service,
//                    params.total,
//                    it.userId,
//                    it.token
//                ) }
//        }

}