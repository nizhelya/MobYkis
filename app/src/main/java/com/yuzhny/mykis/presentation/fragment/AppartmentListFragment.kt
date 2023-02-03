package com.yuzhny.mykis.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.yuzhny.mykis.R
import com.yuzhny.mykis.data.cache.appartment.AppartmentRepository
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import com.yuzhny.mykis.presentation.viewmodel.AppartmentListViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.ViewModelLifecycle
import javax.inject.Inject

@AndroidEntryPoint
class AppartmentListFragment : Fragment() {


    @Inject
    lateinit var appartment :AppartmentRepository


    private val viewModel:AppartmentListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_appartment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.saveAppartmentById(1)
    }


}