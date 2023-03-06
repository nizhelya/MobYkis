package com.yuzhny.mykis.data.dao

import androidx.room.*
import com.yuzhny.mykis.data.cache.appartment.AppartmentCache
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AppartmentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addAppartmentByUser(appartment:List<AppartmentEntity>)

//    @Update
//    suspend fun updateAppartment(appartment: AppartmentEntity)
//
    @Query("delete from appartment")
//    @Query("delete from appartment")
    fun deleteAllAppartments()

    @Query("delete from appartment where address_id = :addressId")
    fun deleteFlat(addressId :Int)
//
//    @Query("select * from appartment where address_id= :addressId")
//    fun getAppartment(addressId:Int): List<AppartmentEntity>

    @Query("select * from appartment")
    fun getAppartmentsByUser(): List<AppartmentEntity>

//    @Query("select * from appartment")
//    fun getAppartments():Flow<List<AppartmentEntity>>
}