package com.yuzhny.mykis.data.remote.address

import com.yuzhny.mykis.domain.address.AddressEntity
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure

interface AddressRemote {
    fun getBlocks(userId:Int, token: String): Either<Failure, List<AddressEntity>>
    fun getStreetsFromBlock(blockId:Int, userId:Int, token: String): Either<Failure, List<AddressEntity>>
}