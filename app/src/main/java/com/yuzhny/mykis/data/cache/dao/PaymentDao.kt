package com.yuzhny.mykis.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yuzhny.mykis.domain.payment.PaymentEntity
import com.yuzhny.mykis.domain.payment.PaymentItemEntity
import com.yuzhny.mykis.domain.service.ServiceEntity
import java.time.Year

@Dao
interface PaymentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPayment(payments:List<PaymentEntity>)
    @Query("select * from payment where address_id = :addressId")
    fun getPaymentFromFlat(addressId:Int): List<PaymentEntity>
    @Query("delete from payment where address_id not in (:addressId)")
    fun deletePaymentFromFlat(addressId: List<Int>)
    @Query("select *  from payment where address_id=:addressId group by address_id,year order by year DESC")
    fun getYearsByFlat(addressId: Int):List<PaymentEntity>
    @Query("select * from payment where address_id = :addressId and year = :year ORDER BY data DESC")
    fun getPaymentsFromYearsFlat(addressId: Int , year: Short):List<PaymentEntity>
}