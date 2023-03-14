package com.yuzhny.mykis.data.cache.family

import com.yuzhny.mykis.domain.family.FamilyEntity

interface FamilyCache {
    fun addFamilyByUser(family:List<FamilyEntity>)
    fun getFamilyFromFlat(addressId:Int):List<FamilyEntity>
    fun deleteFamilyFromFlat(addressId:List<Int>)
}