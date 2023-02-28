package com.yuzhny.mykis.presentation.appartment.detail

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.yuzhny.mykis.R
import com.yuzhny.mykis.presentation.appartment.list.AppartmentListViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class AppartmentDetailFragment  : Fragment() {

    private val detailViewModel : AppartmentDetailViewModel by viewModels()
    private val listViewModel : AppartmentListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailViewModel.getFamily(6314)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_appartment_detail, container, false)

    }


}