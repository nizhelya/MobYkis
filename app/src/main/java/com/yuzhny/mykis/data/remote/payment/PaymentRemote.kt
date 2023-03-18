package com.yuzhny.mykis.data.remote.payment

import com.yuzhny.mykis.domain.payment.PaymentEntity
import com.yuzhny.mykis.domain.service.ServiceEntity
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure

interface PaymentRemote {
    fun getFlatPayments(addressId:Int ,
                        userId :Int ,
                        token : String): Either<Failure, List<PaymentEntity>>
}