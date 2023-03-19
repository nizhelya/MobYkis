package com.yuzhny.mykis.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yuzhny.mykis.domain.payment.PaymentEntity
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
    @Query("select  DISTINCT(year) as year  ,0 as rec_id, 0 as address_id, \"Unknown\" as address ,\"Unknown\" as data , 0.0 as kvartplata,0.0 as remont , 0.0 as otoplenie , 0.0 as voda ,0.0 as tbo , 0.0 as summa , \"Unknown\" as prixod ,\"Unknown\" as kassa , \"Unknown\" as nomer ,\"Unknown\"  as data_in , 0 as isExpandable  from   payment where  address_id = 8256")
    fun getYearsByFlat():List<PaymentEntity>
    @Query("select * from payment where address_id = :addressId and year = :year")
    fun getPaymentsFromYearsFlat(addressId: Int , year: Short):List<PaymentEntity>
}