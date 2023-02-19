package com.yuzhny.mykis.presentation.appartment.add

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yuzhny.mykis.R
import com.yuzhny.mykis.databinding.FragmentAddAppartmentBinding
import com.yuzhny.mykis.databinding.FragmentListAppartmentBinding
import kotlinx.coroutines.delay


class AddAppartmentFragment : Fragment() {

    private val viewModel: AddAppartmentViewModel by activityViewModels()

    private var _binding: FragmentAddAppartmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddAppartmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getBlocksList()
        viewModel.address.observe(this.viewLifecycleOwner) { i ->
            i?.let {
                val adapter = AddressArrayAdapter(requireContext(), it)
                binding.blockSpinner.adapter = adapter

            }
        }
        binding.tipCode.setOnClickListener { Toast.makeText(requireContext(),"Код можна отримати в касах прийому комунальних платежів при оплаті. Код знаходиться у верхньому лівому кутку роздруківки про оплату"
            ,Toast.LENGTH_LONG).show() }
        binding.button2.setOnClickListener { findNavController().navigate(R.id.action_addAppartmentFragment_to_testStreetFragment) }
    }
}


