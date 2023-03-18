package com.yuzhny.mykis.domain.payment

import com.yuzhny.mykis.domain.family.request.BooleanInt
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure

interface PaymentRepository {
    fun getFlatPayment(params: BooleanInt): Either<Failure, List<PaymentEntity>>
}