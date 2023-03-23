package com.yuzhny.mykis.presentation.appartment.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yuzhny.mykis.data.cache.payment.PaymentCacheImpl
import com.yuzhny.mykis.domain.family.request.BooleanInt
import com.yuzhny.mykis.domain.payment.PaymentEntity
import com.yuzhny.mykis.domain.payment.request.GetFlatPayment
import com.yuzhny.mykis.domain.service.ServiceEntity
import com.yuzhny.mykis.domain.service.request.getFlatService
import com.yuzhny.mykis.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PaymentListViewModel @Inject constructor(
    private val getFlatPaymentUseCase: GetFlatPayment,
     val paymentCacheImpl:PaymentCacheImpl
)  :BaseViewModel(){
    private val _paymentsFlat = MutableLiveData<List<PaymentEntity>>()
    val paymentsFlat : LiveData<List<PaymentEntity>> get() = _paymentsFlat

    private val _years = MutableLiveData<List<PaymentEntity>>()
    val years : LiveData<List<PaymentEntity>> get() = _years

    private val _payments = MutableLiveData<List<PaymentEntity>>()
    val payments : LiveData<List<PaymentEntity>> get() = _payments
    fun getFlatPayments(addressId:Int , needFetch:Boolean = false){
        getFlatPaymentUseCase(
            BooleanInt(
                addressId,
                needFetch
            )
        ) { it ->
                it.either(::handleFailure) {
                    handlePayment(
                        it , addressId , !needFetch
                    )
                }
            }
    }

    private fun handlePayment(payments: List<PaymentEntity>,
                              addressId: Int,
                              fromCache: Boolean,
    ) {
        _paymentsFlat.value = payments
        updateProgress(false)

        if (fromCache) {
            updateProgress(true)
            getFlatPayments(addressId , true)
        }
    }
    fun getYearsFromFlat(addressId:Int){
       _years.value = paymentCacheImpl.getYearsFromFlat(addressId)
    }
}