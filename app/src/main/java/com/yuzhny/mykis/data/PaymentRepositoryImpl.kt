package com.yuzhny.mykis.data

import com.yuzhny.mykis.data.cache.payment.PaymentCache
import com.yuzhny.mykis.data.cache.user.UserCache
import com.yuzhny.mykis.data.remote.payment.PaymentRemote
import com.yuzhny.mykis.domain.family.request.BooleanInt
import com.yuzhny.mykis.domain.payment.PaymentEntity
import com.yuzhny.mykis.domain.payment.PaymentRepository
import com.yuzhny.mykis.domain.type.*
import javax.inject.Inject

class PaymentRepositoryImpl @Inject constructor(
    private val paymentCache: PaymentCache,
    private val paymentRemote: PaymentRemote,
    private val userCache: UserCache
) : PaymentRepository {
    override fun getFlatPayment(params: BooleanInt): Either<Failure, List<PaymentEntity>> {
        return userCache.getCurrentUser()
            .flatMap {
                return@flatMap if (params.needFetch) {
                    paymentRemote.getFlatPayments(
                        params.addressId,
                        it.userId,
                        it.token
                    )
                } else {
                    Either.Right(
                        paymentCache.getPaymentsFromFlat(params.addressId)
                    )
                }
            }
            .onNext {
                it.map {
                    paymentCache.addPayments(listOf(it))
                }
            }
        }
}