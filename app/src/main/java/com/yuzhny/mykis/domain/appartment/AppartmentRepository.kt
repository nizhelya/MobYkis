package com.yuzhny.mykis.domain.appartment

import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure

interface AppartmentRepository {
    fun getAppartments(needFetch: Boolean): Either<Failure, List<AppartmentEntity>>
}