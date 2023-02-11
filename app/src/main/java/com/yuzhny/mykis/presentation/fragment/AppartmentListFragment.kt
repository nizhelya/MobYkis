package com.yuzhny.mykis.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.yuzhny.mykis.R
import com.yuzhny.mykis.data.cache.appartment.AppartmentCache
import com.yuzhny.mykis.presentation.viewmodel.AppartmentListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AppartmentListFragment : Fragment() {


    @Inject
    lateinit var appartment :AppartmentCache


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
//        viewModel.saveAppartmentById(1)
        Log.d("Test1",viewModel.getAppartments().toString())
    }


}