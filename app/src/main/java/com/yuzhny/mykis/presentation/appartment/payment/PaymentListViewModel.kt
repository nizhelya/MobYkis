package com.yuzhny.mykis.presentation.appartment.payment

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.yuzhny.mykis.data.cache.payment.PaymentCacheImpl
import com.yuzhny.mykis.domain.family.request.BooleanInt
import com.yuzhny.mykis.domain.payment.PaymentEntity
import com.yuzhny.mykis.domain.payment.PaymentItemEntity
import com.yuzhny.mykis.domain.payment.request.GetFlatPayment
import com.yuzhny.mykis.domain.service.ServiceEntity
import com.yuzhny.mykis.domain.service.request.getFlatService
import com.yuzhny.mykis.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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

    private val _paymentItem = mutableListOf<PaymentItemEntity>()
    val paymentItem = MutableLiveData<List<PaymentItemEntity>>()
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
//    fun getYearsFromFlat(addressId:Int){
//       _years.value = paymentCacheImpl.getYearsFromFlat(addressId)
//    }
    fun getPaymentItem(addressId: Int, loadingView : View, recyclerView: RecyclerView){
    _paymentItem.clear()
//    paymentItem.value = _paymentItem
        val yearList = paymentCacheImpl.getYearsFromFlat(addressId)
        viewModelScope.launch {
            for (i in yearList) {
                _paymentItem.add(PaymentItemEntity(
                        i,
                        paymentCacheImpl.getPaymentFromYearFlat(addressId, i)
                    )
                )

            }
            paymentItem.value = _paymentItem
            if(!_paymentItem.isNullOrEmpty()){
                loadingView.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        }
    }
    fun clearPaymentList(){
        _paymentItem.clear()
        paymentItem.value = _paymentItem
    }
}