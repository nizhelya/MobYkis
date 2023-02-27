package com.yuzhny.mykis.presentation.appartment.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yuzhny.mykis.domain.address.AddressEntity
import com.yuzhny.mykis.domain.family.FamilyEntity
import com.yuzhny.mykis.presentation.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class AppartmentDetailViewModel  : BaseViewModel(){
    private val _family = MutableLiveData<List<FamilyEntity>>()
    val family : LiveData<List<FamilyEntity>> = _family


}