package com.yuzhny.mykis.data

import com.yuzhny.mykis.data.cache.user.UserCache
import com.yuzhny.mykis.data.remote.address.AddressRemote
import com.yuzhny.mykis.domain.address.AddressEntity
import com.yuzhny.mykis.domain.address.AddressRepository
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import com.yuzhny.mykis.domain.type.flatMap

class AddressRepositoryImpl(
    private val remote: AddressRemote,
    private val userCache: UserCache
) :AddressRepository{
    override fun getBlocks(): Either<Failure, List<AddressEntity>> {
        return userCache.getCurrentUser()
            .flatMap { return@flatMap remote.getBlocks(it.userId,it.token) }
    }

    override fun getStreetsFromBlock(blockId: Int): Either<Failure, List<AddressEntity>> {
        return  userCache.getCurrentUser()
            .flatMap { return@flatMap remote.getStreetsFromBlock(blockId , it.userId , it.token) }
    }

    override fun getHousesFromStreet(
        streetId: Int,
        blockId: Int
    ): Either<Failure, List<AddressEntity>> {
        return  userCache.getCurrentUser()
            .flatMap { return@flatMap remote.getHousesFromStreet(streetId,blockId,it.userId,it.token) }
    }


}