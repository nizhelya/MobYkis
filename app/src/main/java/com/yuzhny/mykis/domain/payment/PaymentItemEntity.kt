package com.yuzhny.mykis.domain.payment

import java.time.Year

data class PaymentItemEntity(
    val year:Int = 0,
    val paymentsList: List<PaymentEntity>,
    var isExpandable : Boolean = false
)