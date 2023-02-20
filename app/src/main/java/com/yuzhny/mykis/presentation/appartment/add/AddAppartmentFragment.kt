package com.yuzhny.mykis.presentation.appartment.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.yuzhny.mykis.databinding.FragmentAddAppartmentBinding
import com.yuzhny.mykis.domain.address.AddressEntity
import com.yuzhny.mykis.presentation.appartment.add.adapter.BlockArrayAdapter
import com.yuzhny.mykis.presentation.appartment.add.adapter.HouseArrayAdapter
import com.yuzhny.mykis.presentation.appartment.add.adapter.StreetArrayAdapter
import javax.inject.Inject


class AddAppartmentFragment : Fragment()  {

    private val viewModel: AddAppartmentViewModel by activityViewModels()

    private var _binding: FragmentAddAppartmentBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var blockAdapter: BlockArrayAdapter

    @Inject
    lateinit var streetAdapter: StreetArrayAdapter

    @Inject
    lateinit var houseAdapter:HouseArrayAdapter

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
                blockAdapter = BlockArrayAdapter(requireContext(), it)
                binding.blockSpinner.adapter = blockAdapter
            }
        }

            binding.blockSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
                ) {
                        viewModel.getStreetList(blockAdapter.addressSelected[position].blockId)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }
        viewModel.streets.observe(this.viewLifecycleOwner) { i ->
            i?.let {
                streetAdapter = StreetArrayAdapter(requireContext(), it)
                binding.streetSpinner.adapter = streetAdapter
            }
        }
        binding.streetSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.getHousesList(streetAdapter.addressSelected[position].streetId , streetAdapter.addressSelected[position].blockId)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
        viewModel.houses.observe(this.viewLifecycleOwner) { i ->
            i?.let {
                houseAdapter = HouseArrayAdapter(requireContext(), it)
                binding.houseSpinner.adapter = houseAdapter
            }
        }


        binding.tipCode.setOnClickListener { Toast.makeText(requireContext(),"Код можна отримати в касах прийому комунальних платежів при оплаті. Код знаходиться у верхньому лівому кутку роздруківки про оплату"
            ,Toast.LENGTH_LONG).show() }
    }


}


