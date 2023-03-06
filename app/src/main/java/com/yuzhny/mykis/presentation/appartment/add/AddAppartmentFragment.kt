package com.yuzhny.mykis.presentation.appartment.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.yuzhny.mykis.R
import com.yuzhny.mykis.data.remote.GetSimpleResponse
import com.yuzhny.mykis.data.remote.address.GetAddressResponse
import com.yuzhny.mykis.databinding.FragmentAddAppartmentBinding
import com.yuzhny.mykis.domain.address.AddressEntity
import com.yuzhny.mykis.presentation.appartment.add.adapter.BlockArrayAdapter
import com.yuzhny.mykis.presentation.appartment.add.adapter.FlatArrayAdapter
import com.yuzhny.mykis.presentation.appartment.add.adapter.HouseArrayAdapter
import com.yuzhny.mykis.presentation.appartment.add.adapter.StreetArrayAdapter
import com.yuzhny.mykis.presentation.core.BaseFragment
import com.yuzhny.mykis.presentation.core.ext.onFailure
import com.yuzhny.mykis.presentation.core.ext.onSuccess
import javax.inject.Inject


class AddAppartmentFragment : BaseFragment()
{
    private var _binding: FragmentAddAppartmentBinding? = null
    private val binding get() = _binding!!

    private val addAppartmentViewModel: AddAppartmentViewModel by activityViewModels()

    private var chooseBlockId: Int = 0
    private var chooseHouseId: Int = 0
    private var selectedFlat: Int = 0

    @Inject
    lateinit var blockAdapter: BlockArrayAdapter

    @Inject
    lateinit var streetAdapter: StreetArrayAdapter

    @Inject
    lateinit var houseAdapter: HouseArrayAdapter

    @Inject
    lateinit var flatAdapter: FlatArrayAdapter

//    override fun getViewBinding(view: View): FragmentAddAppartmentBinding = FragmentAddAppartmentBinding.bind(view)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addAppartmentViewModel.apply {
            onSuccess(blocks , ::handleBlocks)
            onSuccess(streets , ::handleStreets)
            onSuccess(houses , ::handleHouses)
            onSuccess(flats , ::handleFlats)
            onSuccess(resultText , ::handleResultText)
            onSuccess(checkText , ::handleCheckText)
            onFailure(failureData, ::handleFailure)
        }
        _binding = FragmentAddAppartmentBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addAppartmentViewModel.getBlocksList()
        binding.blockSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                addAppartmentViewModel.getStreetList(blockAdapter.addressSelected[position].blockId)
                chooseBlockId = blockAdapter.addressSelected[position].blockId
                addAppartmentViewModel.clearLiveData()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
        binding.streetSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                addAppartmentViewModel.getHousesList(
                    streetAdapter.addressSelected[position].streetId,
                    chooseBlockId
                )
                addAppartmentViewModel.clearLiveData()
                if (houseAdapter.addressSelected.size > 1) {
                    binding.houseSpinner.visibility = View.GONE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
        binding.houseSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                addAppartmentViewModel.getFlatsList(
                    houseAdapter.addressSelected[position].houseId
                )
                chooseHouseId = houseAdapter.addressSelected[position].houseId
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
        binding.flatSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedFlat = flatAdapter.addressSelected[position].flatId
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        binding.tipCode.setOnClickListener {
            Toast.makeText(
                requireContext(),
                getString(R.string.tooltip_code),
                Toast.LENGTH_LONG
            ).show()
        }
        binding.checkCode.setOnClickListener {
            checkSecretCode(
                binding.codeInput.text.toString(),
                selectedFlat
            )
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        addAppartmentViewModel.clearAllLiveData()
    }

    private fun handleBlocks(addressEntity: List<AddressEntity>?){
        addressEntity?.let {
            blockAdapter = BlockArrayAdapter(requireContext(), it)
            binding.blockSpinner.adapter = blockAdapter
        }
    }
    private fun handleStreets(addressEntity: List<AddressEntity>?){
        addressEntity?.let {
            streetAdapter = StreetArrayAdapter(requireContext(), it)
            binding.streetSpinner.adapter = streetAdapter
        }
    }
    private fun handleHouses(addressEntity: List<AddressEntity>?){
        addressEntity?.let {
            houseAdapter = HouseArrayAdapter(requireContext(), it)
            binding.houseSpinner.adapter = houseAdapter
            setVisibleHouse(it)
        }
    }
    private fun handleFlats(addressEntity: List<AddressEntity>?){
        addressEntity?.let {
            flatAdapter = FlatArrayAdapter(requireContext(), it)
            binding.flatSpinner.adapter = flatAdapter
            if (it.isNotEmpty()) {
                binding.codeLabel.visibility = View.VISIBLE
                binding.checkCode.visibility = View.VISIBLE
                binding.tipCode.visibility = View.VISIBLE

            } else {
                binding.codeLabel.visibility = View.GONE
                binding.checkCode.visibility = View.GONE
                binding.tipCode.visibility = View.GONE
            }
        }
    }
    private fun handleResultText(getSimpleResponse: GetSimpleResponse?) {
        getSimpleResponse?.let {
            if(it.success == 1){
                it.success = 0
                Toast.makeText(requireContext() , getString(R.string.success_add_flat) , Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_addAppartmentFragment_to_appartmentFragment)
            }
        }
    }
    private fun handleCheckText(getSimpleResponse: GetSimpleResponse?) {
        getSimpleResponse?.let {
            if(it.success == 1) {
                it.success = 0
                addAppartmentViewModel.addFlat(selectedFlat)
            }
        }


    }
    private fun checkSecretCode(userSecretCode: String, addressId: Int) {
        addAppartmentViewModel.checkCode(userSecretCode,addressId)
    }

    private fun setVisibleHouse(listAddress: List<AddressEntity>) {
        if (listAddress.isEmpty()) {
            binding.houseSpinner.visibility = View.GONE
        } else if (listAddress.size == 1) {
            binding.houseSpinner.visibility = View.GONE
            addAppartmentViewModel.getFlatsList(listAddress[0].houseId)
        } else {
            binding.houseSpinner.visibility = View.VISIBLE
        }
    }

}



