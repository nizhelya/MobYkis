package com.yuzhny.mykis.presentation.appartment.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.yuzhny.mykis.R
import com.yuzhny.mykis.databinding.FragmentAddAppartmentBinding
import com.yuzhny.mykis.databinding.FragmentTestStreetBinding
import javax.inject.Inject


class TestStreetFragment : Fragment() {
    @Inject
    lateinit var streetAdapter: StreetArrayAdapter
    private val viewModel : TestStreetViewModel by activityViewModels()
    private var _binding: FragmentTestStreetBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTestStreetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getStreetsList(4)
        viewModel.street.observe(this.viewLifecycleOwner){
            i -> i?.let {
                streetAdapter = StreetArrayAdapter(requireContext(), it)
                binding.streetSpinner.adapter = streetAdapter
            }
        }
    }


}