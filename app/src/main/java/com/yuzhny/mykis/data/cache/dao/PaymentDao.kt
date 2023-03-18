package com.yuzhny.mykis.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yuzhny.mykis.domain.payment.PaymentEntity
import com.yuzhny.mykis.domain.service.ServiceEntity

@Dao
interface PaymentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPayment(payments:List<PaymentEntity>)
    @Query("select * from payment where address_id = :addressId")
    fun getPaymentFromFlat(addressId:Int): List<PaymentEntity>
    @Query("delete from payment where address_id not in (:addressId)")
    fun deletePaymentFromFlat(addressId: List<Int>)
}