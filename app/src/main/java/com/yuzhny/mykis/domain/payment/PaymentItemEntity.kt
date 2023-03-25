package com.yuzhny.mykis.domain.payment

import java.time.Year

data class PaymentItemEntity(
    val year:Short,
    val paymentsList: List<PaymentEntity>
)