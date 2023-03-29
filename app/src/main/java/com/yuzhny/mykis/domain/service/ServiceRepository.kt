package com.yuzhny.mykis.domain.service

import com.yuzhny.mykis.domain.service.request.ServiceParams
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure

interface ServiceRepository {
    fun getFlatService(params:ServiceParams): Either<Failure, List<ServiceEntity>>
    fun getTotalFlatService(addressId:Int): Either<Failure, ServiceEntity?>
}