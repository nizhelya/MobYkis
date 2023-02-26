package com.yuzhny.mykis.data.remote.family

import com.yuzhny.mykis.data.remote.core.BaseResponse
import com.yuzhny.mykis.domain.address.AddressEntity
import com.yuzhny.mykis.domain.family.FamilyEntity

class GetFamilyResponse (
    success:Int,
    message: String,
    val family: List<FamilyEntity>
) : BaseResponse(success, message)