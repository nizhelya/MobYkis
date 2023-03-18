package com.yuzhny.mykis.data.remote.payment

import com.yuzhny.mykis.data.remote.core.BaseResponse
import com.yuzhny.mykis.domain.payment.PaymentEntity
import com.yuzhny.mykis.domain.service.ServiceEntity

class GetPaymentResponse (
    success:Int,
    message: String,
    val payments: List<PaymentEntity>
) : BaseResponse(success, message)