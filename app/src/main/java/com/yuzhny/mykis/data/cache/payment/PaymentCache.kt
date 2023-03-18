package com.yuzhny.mykis.data.cache.payment

import com.yuzhny.mykis.domain.payment.PaymentEntity
import com.yuzhny.mykis.domain.service.ServiceEntity

interface PaymentCache {
    fun addPayments(payments:List<PaymentEntity>)
    fun getPaymentsFromFlat(addressId:Int):List<PaymentEntity>
    fun deletePaymentFromFlat(addressId:List<Int>)
}