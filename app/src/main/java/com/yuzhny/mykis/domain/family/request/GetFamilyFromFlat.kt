package com.yuzhny.mykis.domain.family.request

import com.yuzhny.mykis.domain.family.FamilyEntity
import com.yuzhny.mykis.domain.family.FamilyRepository
import com.yuzhny.mykis.domain.interactor.UseCase
import javax.inject.Inject

class GetFamilyFromFlat @Inject constructor(
    private val familyRepository:FamilyRepository
) : UseCase<List<FamilyEntity>, BooleanInt>() {

    override suspend fun run(params:BooleanInt) = familyRepository.getFamilyFromFlat(params)
}
data class BooleanInt(
    val int:Int,
    val needFetch:Boolean
)