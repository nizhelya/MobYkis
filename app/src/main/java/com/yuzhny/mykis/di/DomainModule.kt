package com.yuzhny.mykis.di

import com.yuzhny.mykis.domain.address.AddressRepository
import com.yuzhny.mykis.domain.address.GetBlocks
import com.yuzhny.mykis.domain.address.GetStreetsFromBlock
import com.yuzhny.mykis.domain.appartment.AppartmentRepository
import com.yuzhny.mykis.domain.appartment.GetAppartments
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

//@Module
//@InstallIn(ViewModelComponent::class)
//class DomainModule {
//
//    @Provides
//    fun getStreetsFromBlock(addressRepository: AddressRepository):GetStreetsFromBlock = GetStreetsFromBlock(addressRepository)
//
//    @Provides
//    fun getBlock(addressRepository: AddressRepository): GetBlocks = GetBlocks(addressRepository)
//
//    @Provides
//    fun getAppartments(appartmentRepository: AppartmentRepository): GetAppartments = GetAppartments(appartmentRepository)
//}