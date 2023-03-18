package com.yuzhny.mykis.data.remote.payment

import com.yuzhny.mykis.data.remote.api.ApiService
import com.yuzhny.mykis.data.remote.core.Request
import com.yuzhny.mykis.domain.payment.PaymentEntity
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaymentRemoteImpl @Inject constructor(
    private val request: Request,
    private val apiService: ApiService
) : PaymentRemote {

    override fun getFlatPayments(
        addressId: Int,
        userId: Int,
        token: String
    ): Either<Failure, List<PaymentEntity>> {
        return request.make(
            apiService.getFlatPayment(
                createGetFlatPaymentMap(
                    addressId,
                    userId,
                    token
                )
            )
        ){
            it.payments
        }
    }

    private fun createGetFlatPaymentMap(
        addressId: Int,
        userId: Int,
        token: String
    ): Map<String, String> {
        val map = HashMap<String, String>()
        map.put(ApiService.ADDRESS_ID, addressId.toString())
        map.put(ApiService.PARAM_USER_ID, userId.toString())
        map.put(ApiService.PARAM_TOKEN, token)
        return map
    }
}