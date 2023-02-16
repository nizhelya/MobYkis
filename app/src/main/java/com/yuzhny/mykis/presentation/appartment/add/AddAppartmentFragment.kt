package com.yuzhny.mykis.presentation.appartment.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.yuzhny.mykis.R
import com.yuzhny.mykis.databinding.FragmentAddAppartmentBinding
import com.yuzhny.mykis.databinding.FragmentListAppartmentBinding
import kotlinx.coroutines.delay


class AddAppartmentFragment : Fragment() {

    private val viewModel:AddAppartmentViewModel by activityViewModels()

    private var _binding: FragmentAddAppartmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddAppartmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getBlocksList()
        viewModel.listBlocks.observe(this.viewLifecycleOwner){
            i -> i?.let {
            binding.blocksList.setAdapter(  ArrayAdapter(requireContext(),R.layout.drop_down_item, it))
            }
        }

    }
}


