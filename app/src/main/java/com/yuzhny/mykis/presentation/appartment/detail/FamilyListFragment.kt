package com.yuzhny.mykis.presentation.appartment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.yuzhny.mykis.databinding.FragmentFamilyListBinding
import com.yuzhny.mykis.presentation.appartment.list.AppartmentListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FamilyListFragment  : Fragment() {

    private val familyViewModel : FamilyListViewModel by activityViewModels()
    private val listViewModel : AppartmentListViewModel by activityViewModels()

    private var _binding : FragmentFamilyListBinding? = null
    private val binding get() = _binding!!
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
            binding.testText.text = it.address
            binding.testTextAddressId.text = it.addressId.toString()
            Toast.makeText(requireContext() , it.addressId.toString(), Toast.LENGTH_LONG ).show()
            familyViewModel.getFamily(it.addressId)
        }
    }
}