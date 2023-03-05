package com.yuzhny.mykis.presentation.appartment.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.yuzhny.mykis.R
import com.yuzhny.mykis.databinding.FragmentListAppartmentBinding
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import com.yuzhny.mykis.presentation.core.BaseFragment
import com.yuzhny.mykis.presentation.core.ext.onFailure
import com.yuzhny.mykis.presentation.core.ext.onSuccess
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AppartmentListFragment : BaseFragment()
{
    private var _binding : FragmentListAppartmentBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var viewAdapter: AppartmentListAdapter

    private val  appartmentListViewModel: AppartmentListViewModel by activityViewModels()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        appartmentListViewModel.apply {
            onSuccess(appartments, ::handleAppartment)
            onFailure(failureData, ::handleFailure)
        }
       _binding = FragmentListAppartmentBinding.inflate(inflater,container,false)
        binding.recyclerView.adapter = viewAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appartmentListViewModel.getAppartmentsByUser()


        appartmentListViewModel.appartments.observe(this.viewLifecycleOwner){i->
            i?.let {
                viewAdapter.submitList(it)
                checkIsEmptyRecycleView(it)
            }
        }



        viewAdapter.appartmentListener.onItemClick = {
            appartmentListViewModel.getAppartment(it)
//            Toast.makeText(requireContext() , it.addressId.toString(), Toast.LENGTH_LONG ).show()
            findNavController().navigate(AppartmentListFragmentDirections
                .actionAppartmentFragmentToViewPagerFragment(it.addressId))

        }
        binding.recyclerView.adapter = viewAdapter
        binding.addPlantFab.setOnClickListener {
            findNavController().navigate(R.id.action_appartmentFragment_to_addAppartmentFragment)
        }
    }
    private fun handleAppartment(appartmentEntity:  List<AppartmentEntity>?) {
        if (appartmentEntity != null && appartmentEntity.isNotEmpty()) {
            viewAdapter.submitList(appartmentEntity)
            checkIsEmptyRecycleView(appartmentEntity)

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