package com.yuzhny.mykis.data.remote.appartment

import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure

interface AppartmentRemote {
    fun getAppartments(addressId: Int, token: String): Either<Failure, List<AppartmentEntity>>
}