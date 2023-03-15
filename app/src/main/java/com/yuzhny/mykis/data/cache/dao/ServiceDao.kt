
package com.yuzhny.mykis.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yuzhny.mykis.domain.family.FamilyEntity
import com.yuzhny.mykis.domain.service.ServiceEntity


@Dao
interface ServiceDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertService(service:List<ServiceEntity>)
    @Query("select * from service where address_id = :addressId")
    fun getFamilyFromFlat(addressId:Int): List<ServiceEntity>
    @Query("delete from service where address_id not in (:addressId)")
    fun deleteServiceFromFlat(addressId: List<Int>)
}