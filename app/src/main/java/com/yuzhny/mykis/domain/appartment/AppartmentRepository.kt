package com.yuzhny.mykis.domain.appartment

import com.yuzhny.mykis.data.remote.GetSimpleResponse
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure

interface AppartmentRepository {
    fun getAppartmentsByUser(needFetch: Boolean): Either<Failure, List<AppartmentEntity>>
    fun deleteFlatByUser(addressId:Int ): Either<Failure, GetSimpleResponse>
}