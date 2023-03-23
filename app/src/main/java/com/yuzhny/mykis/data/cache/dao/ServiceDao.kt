
package com.yuzhny.mykis.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import com.yuzhny.mykis.domain.family.FamilyEntity
import com.yuzhny.mykis.domain.service.ServiceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ServiceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertService(service:List<ServiceEntity>)
    @Query("select * from service where address_id = :addressId")
    fun getServiceFromFlat(addressId:Int): List<ServiceEntity>
    @Query("delete from service where address_id not in (:addressId)")
    fun deleteServiceFromFlat(addressId: List<Int>)
    @Query("select * from service where address_id = :addressId and service = 'total' ")
    fun getTotalDebt(addressId:Int):ServiceEntity
    @Query("select * from service where address_id = :addressId and service = :service  ORDER by data DESC ")
    suspend fun getDetailService(addressId: Int , service: String):List<ServiceEntity>
}