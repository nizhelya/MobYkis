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
import com.yuzhny.mykis.databinding.FragmentListAppartmentBinding
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import com.yuzhny.mykis.presentation.appartment.AppartmentAdapter
import com.yuzhny.mykis.presentation.viewmodel.AppartmentListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AppartmentListFragment : Fragment() {

    @Inject
    lateinit var viewAdapter:AppartmentAdapter

    private var _binding: FragmentListAppartmentBinding? = null
    private val binding get() = _binding!!


    private val viewModel:AppartmentListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentListAppartmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAppartmentsByUser()
        viewModel.appartment.observe(this.viewLifecycleOwner){i->
            i?.let {
                viewAdapter.submitList(it)
            }
        }
        binding.recyclerView.adapter = viewAdapter
    }

//    private fun handleAppartment(appartmens: List<AppartmentEntity>?) {
//        if (appartmens != null && appartmens.isNotEmpty()) {
//            viewAdapter.submitList(appartmens)
//        }
//    }
}