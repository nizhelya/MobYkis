package com.yuzhny.mykis.domain.service.request

import com.yuzhny.mykis.data.ServiceRepositoryImpl
import com.yuzhny.mykis.domain.interactor.UseCase
import com.yuzhny.mykis.domain.service.ServiceEntity
import javax.inject.Inject

class getFlatService @Inject constructor(
    private val serviceRepository: ServiceRepositoryImpl
) : UseCase<List<ServiceEntity>, ServiceParams>() {

    override suspend fun run(params:ServiceParams) = serviceRepository.getFlatService(params)
}
data class ServiceParams(
    val addressId:Int,
    val houseId:Int ,
    val service:Byte ,
    val qty :Byte ,
    var needFetch:Boolean
)