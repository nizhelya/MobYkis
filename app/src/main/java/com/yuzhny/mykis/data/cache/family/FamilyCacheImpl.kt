package com.yuzhny.mykis.data.cache.family

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.yuzhny.mykis.data.dao.FamilyDao
import com.yuzhny.mykis.domain.family.FamilyEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FamilyCacheImpl @Inject constructor(
    private val familyDao: FamilyDao
) :FamilyCache {
    override fun addAppartmentByUser(family : List<FamilyEntity>) {
        familyDao.insertFamily(family)
    }

    override fun getFamilyFromFlat(addressId:Int): List<FamilyEntity> {
        return familyDao.getFamilyFromFlat(addressId)
    }

    override fun deleteFamilyFromFlat(addressId: List<Int>) {
        familyDao.deleteFamilyFromFlat(addressId)
    }


}