package com.yuzhny.mykis.presentation.appartment.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yuzhny.mykis.R
import com.yuzhny.mykis.data.remote.GetSimpleResponse
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
            onSuccess(appartments, ::handleAppartments)
            onSuccess(resultText, ::handleResultText)
            onFailure(failureData, ::handleFailure)
        }
       _binding = FragmentListAppartmentBinding.inflate(inflater,container,false)
        binding.recyclerView.adapter = viewAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appartmentListViewModel.getAppartmentsByUser()
        appartmentListViewModel.appartments.observe(this.viewLifecycleOwner){
            viewAdapter.submitList(it)
            checkIsEmptyRecycleView(it)
        }
        viewAdapter.appartmentShortListener.onItemClick = {
            appartmentListViewModel.getAppartment(it)
            findNavController().navigate(AppartmentListFragmentDirections
                .actionAppartmentFragmentToViewPagerFragment(addressId=it.addressId , houseId = it.houseId))

        }
        viewAdapter.appartmentLongListener.onItemLongClick= {
            showFinalScoreDialog(it.addressId , it.address)
        }
        binding.recyclerView.adapter = viewAdapter
        binding.addPlantFab.setOnClickListener {
            findNavController().navigate(R.id.action_appartmentFragment_to_addAppartmentFragment)
        }

    }
    private fun handleAppartments(appartmentEntity:  List<AppartmentEntity>?) {
        if (appartmentEntity != null && appartmentEntity.isNotEmpty()) {
            appartmentListViewModel.getFlatFromCache(appartmentListViewModel.currentAddress)
        }
    }
    private fun handleResultText(getSimpleResponse: GetSimpleResponse?) {
        getSimpleResponse?.let {
            if(it.success == 1){
                it.success = 0
                Toast.makeText(requireContext() ,getString(R.string.success_delete_flat), Toast.LENGTH_SHORT ).show()
                appartmentListViewModel.getAppartmentsByUser()
            }
        }
    }
    private fun checkIsEmptyRecycleView(addressEntity: List<AppartmentEntity>){
        if(addressEntity.isEmpty()){
            binding.noDataImage.visibility = View.VISIBLE
            binding.noDataTitle.visibility = View.VISIBLE
            binding.noDataSubtitle.visibility = View.VISIBLE
        }else {
            binding.noDataImage.visibility = View.GONE
            binding.noDataTitle.visibility = View.GONE
            binding.noDataSubtitle.visibility = View.GONE
        }
    }

    private fun showFinalScoreDialog(addressId :Int , address:String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.title_delete))
            .setMessage(getString((R.string.desc_delete),address))
            .setCancelable(true)
            .setNegativeButton(getString(R.string.cancel)) { _, _ ->
            }
            .setIcon(R.drawable.ic_delete)
            .setPositiveButton(getString(R.string.delete)) { _, _ ->
                appartmentListViewModel.deleteFlat(addressId)
            }
            .show()
    }
}