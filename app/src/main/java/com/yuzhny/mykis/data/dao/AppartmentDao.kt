package com.yuzhny.mykis.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yuzhny.mykis.domain.appartment.AppartmentEntity

@Dao
interface AppartmentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAppartment(appartment:List<AppartmentEntity>)

//    @Query("select * from appartment")
//    fun getAppartments():List<AppartmentEntity>
}