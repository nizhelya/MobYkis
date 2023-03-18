package com.yuzhny.mykis.domain.payment.request

import com.yuzhny.mykis.data.PaymentRepositoryImpl
import com.yuzhny.mykis.domain.family.request.BooleanInt
import com.yuzhny.mykis.domain.interactor.UseCase
import com.yuzhny.mykis.domain.payment.PaymentEntity
import javax.inject.Inject

class GetFlatPayment @Inject constructor(
    private val paymentRepository: PaymentRepositoryImpl
) : UseCase<List<PaymentEntity>, BooleanInt>() {
    override suspend fun run(params: BooleanInt) = paymentRepository.getFlatPayment(params)
}