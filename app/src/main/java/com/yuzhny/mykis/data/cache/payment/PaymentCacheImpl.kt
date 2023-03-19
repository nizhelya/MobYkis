package com.yuzhny.mykis.data.cache.payment

import com.yuzhny.mykis.data.cache.dao.PaymentDao
import com.yuzhny.mykis.domain.payment.PaymentEntity
import java.time.Year
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaymentCacheImpl @Inject constructor(
    private val paymentDao: PaymentDao
):PaymentCache {
    override fun addPayments(payments: List<PaymentEntity>) {
        paymentDao.insertPayment(payments)
    }

    override fun getPaymentsFromFlat(addressId: Int): List<PaymentEntity> {
        return paymentDao.getPaymentFromFlat(addressId)
    }

    override fun deletePaymentFromFlat(addressId: List<Int>) {
        paymentDao.deletePaymentFromFlat(addressId)
    }

    override fun getYearsFromFlat(addressId: Int):List<PaymentEntity> {
        return paymentDao.getYearsByFlat()
    }

    override fun getPaymentFromYearFlat(addressId: Int, year:Short): List<PaymentEntity> {
        return paymentDao.getPaymentsFromYearsFlat(addressId , year)
    }
}