package com.yuzhny.mykis.presentation.appartment.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    private val getFlatPaymentUseCase: GetFlatPayment
)  :BaseViewModel(){
    private val _paymentsFlat = MutableLiveData<List<PaymentEntity>>()
    val paymentsFlat : LiveData<List<PaymentEntity>> get() = _paymentsFlat

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
}