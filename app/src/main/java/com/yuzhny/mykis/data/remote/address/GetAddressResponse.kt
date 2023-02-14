package com.yuzhny.mykis.data.remote.address

import com.yuzhny.mykis.data.remote.core.BaseResponse
import com.yuzhny.mykis.domain.address.AddressEntity
import com.yuzhny.mykis.domain.appartment.AppartmentEntity

class GetAddressResponse (
    success:Int,
    message: String,
    val address: List<AddressEntity>
) : BaseResponse(success, message)