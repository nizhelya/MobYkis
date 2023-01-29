package com.yuzhny.mykis.data.dao

import androidx.room.*
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AppartmentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAppartment(appartment:AppartmentEntity)

    @Update
    suspend fun updateAppartment(appartment: AppartmentEntity)

    @Delete
    suspend fun deleteAppartment(appartment:AppartmentEntity)

    @Query("select * from appartment where address_id= :addressId")
    fun getAppartment(addressId:Int): Flow<AppartmentEntity>

    @Query("select * from appartment")
    fun getAppartments():Flow<List<AppartmentEntity>>
}