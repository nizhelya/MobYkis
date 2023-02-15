package com.yuzhny.mykis.data

import com.yuzhny.mykis.data.cache.user.UserCache
import com.yuzhny.mykis.data.remote.address.AddressRemote
import com.yuzhny.mykis.domain.address.AddressEntity
import com.yuzhny.mykis.domain.address.AddressRepository
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure

class AddressRepositoryImpl(
    private val remote: AddressRemote,
    private val userCache: UserCache
) :AddressRepository{
    override fun getBlocks(): Either<Failure, List<AddressEntity>> {
        TODO("Not yet implemented")
    }

    override fun getHouses(blockId: Int): Either<Failure, List<AddressEntity>> {
        TODO("Not yet implemented")
    }

}