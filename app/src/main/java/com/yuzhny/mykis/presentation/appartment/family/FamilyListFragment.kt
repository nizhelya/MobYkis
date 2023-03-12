package com.yuzhny.mykis.presentation.appartment.family

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.yuzhny.mykis.databinding.FragmentFamilyListBinding
import com.yuzhny.mykis.presentation.appartment.list.AppartmentListViewModel
import com.yuzhny.mykis.presentation.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FamilyListFragment  : BaseFragment() {

    private val familyViewModel : FamilyListViewModel by activityViewModels()
    private val listViewModel : AppartmentListViewModel by activityViewModels()

    private var _binding : FragmentFamilyListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var familyAdapter: FamilyListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFamilyListBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listViewModel.appartment.observe(this.viewLifecycleOwner){
            familyViewModel.getFamily(it.addressId)
        }
        familyViewModel.family.observe(this.viewLifecycleOwner){
            i -> i?.let {
                familyAdapter.submitList(it)
            }
        }
        binding.recyclerView.adapter = familyAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}