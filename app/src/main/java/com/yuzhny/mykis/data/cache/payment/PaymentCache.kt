package com.yuzhny.mykis.data.cache.payment

import com.yuzhny.mykis.domain.payment.PaymentEntity
import com.yuzhny.mykis.domain.service.ServiceEntity
import java.time.Year

interface PaymentCache {
    fun addPayments(payments:List<PaymentEntity>)
    fun getPaymentsFromFlat(addressId:Int):List<PaymentEntity>
    fun deletePaymentFromFlat(addressId:List<Int>)
    fun getYearsFromFlat(addressId: Int):List<Int>
    suspend fun getPaymentFromYearFlat(addressId: Int , year:Int):List<PaymentEntity>
}