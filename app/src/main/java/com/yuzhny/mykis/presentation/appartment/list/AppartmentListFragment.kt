package com.yuzhny.mykis.presentation.appartment.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yuzhny.mykis.R
import com.yuzhny.mykis.databinding.FragmentListAppartmentBinding
import com.yuzhny.mykis.domain.address.AddressEntity
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import com.yuzhny.mykis.presentation.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AppartmentListFragment : BaseFragment<FragmentListAppartmentBinding>() {

    @Inject
    lateinit var viewAdapter: AppartmentAdapter

    private val viewModel: AppartmentListViewModel by viewModels()

    override fun getViewBinding(view: View): FragmentListAppartmentBinding = FragmentListAppartmentBinding.bind(view)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
       _binding = FragmentListAppartmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAppartmentsByUser()
        viewModel.getFamily(6314)
        viewModel.appartments.observe(this.viewLifecycleOwner){i->
            i?.let {
                viewAdapter.submitList(it)
                checkIsEmptyRecycleView(it)
            }
        }
        binding.recyclerView.adapter = viewAdapter
        binding.addPlantFab.setOnClickListener {
            findNavController().navigate(R.id.action_appartmentFragment_to_addAppartmentFragment)
        }
    }
    private fun checkIsEmptyRecycleView(addressEntity: List<AppartmentEntity>){
        if(addressEntity.isNullOrEmpty()){
            binding.noDataImage.visibility = View.VISIBLE
            binding.noDataTitle.visibility = View.VISIBLE
            binding.noDataSubtitle.visibility = View.VISIBLE
        }else {
            binding.noDataImage.visibility = View.GONE
            binding.noDataTitle.visibility = View.GONE
            binding.noDataSubtitle.visibility = View.GONE
        }
    }
}