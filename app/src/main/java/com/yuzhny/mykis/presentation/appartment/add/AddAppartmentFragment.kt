package com.yuzhny.mykis.presentation.appartment.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.yuzhny.mykis.databinding.FragmentAddAppartmentBinding
import com.yuzhny.mykis.domain.address.AddressEntity
import com.yuzhny.mykis.presentation.appartment.add.adapter.BlockArrayAdapter
import com.yuzhny.mykis.presentation.appartment.add.adapter.FlatArrayAdapter
import com.yuzhny.mykis.presentation.appartment.add.adapter.HouseArrayAdapter
import com.yuzhny.mykis.presentation.appartment.add.adapter.StreetArrayAdapter
import javax.inject.Inject


class AddAppartmentFragment : Fragment() {

    private val viewModel: AddAppartmentViewModel by activityViewModels()

    private var _binding: FragmentAddAppartmentBinding? = null
    private val binding get() = _binding!!
    private var chooseBlockId: Int = 0
    private var chooseHouseId: Int = 0
    private var chooseSecretCode: Long = 0

    @Inject
    lateinit var blockAdapter: BlockArrayAdapter

    @Inject
    lateinit var streetAdapter: StreetArrayAdapter

    @Inject
    lateinit var houseAdapter: HouseArrayAdapter

    @Inject
    lateinit var flatAdapter: FlatArrayAdapter

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
        viewModel.blocks.observe(this.viewLifecycleOwner) { i ->
            i?.let {
                blockAdapter = BlockArrayAdapter(requireContext(), it)
                binding.blockSpinner.adapter = blockAdapter
            }
        }

        binding.blockSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.getStreetList(blockAdapter.addressSelected[position].blockId)
                chooseBlockId = blockAdapter.addressSelected[position].blockId
                viewModel.clearLiveData()
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
        binding.streetSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.getHousesList(
                    streetAdapter.addressSelected[position].streetId,
                    chooseBlockId
                )
                viewModel.clearLiveData()
                if (houseAdapter.addressSelected.size > 1) {
                    binding.houseSpinner.visibility = View.GONE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
        viewModel.houses.observe(this.viewLifecycleOwner) { i ->
            i?.let {
                houseAdapter = HouseArrayAdapter(requireContext(), it)
                binding.houseSpinner.adapter = houseAdapter
                setVisibleHouse(it)
            }
        }
        binding.houseSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.getFlatsList(
                    houseAdapter.addressSelected[position].houseId
                )
                chooseHouseId = houseAdapter.addressSelected[position].houseId
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
        viewModel.flats.observe(this.viewLifecycleOwner) { it ->
            it?.let {
                flatAdapter = FlatArrayAdapter(requireContext(), it)
                binding.flatSpinner.adapter = flatAdapter
                if(it.isNotEmpty()){
                    binding.codeLabel.visibility = View.VISIBLE
                    binding.checkCode.visibility = View.VISIBLE
                    binding.tipCode.visibility = View.VISIBLE

                }else {
                    binding.codeLabel.visibility = View.GONE
                    binding.checkCode.visibility = View.GONE
                    binding.tipCode.visibility = View.GONE
                }

            }
        }
        binding.flatSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                chooseSecretCode = flatAdapter.addressSelected[position].secretCode
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        binding.tipCode.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Код можна отримати в касах прийому комунальних платежів при оплаті. Код знаходиться у верхньому лівому кутку роздруківки про оплату",
                Toast.LENGTH_LONG
            ).show()
        }
        binding.checkCode.setOnClickListener {
            checkSecretCode(
                chooseSecretCode.toString(),
                binding.codeInput.text.toString()
            )
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.clearAllLiveData()
    }

    private fun checkSecretCode(secretCode: String, userSecretCode: String) {
        if (secretCode == userSecretCode) {
            Toast.makeText(requireContext(), "Код верный", Toast.LENGTH_LONG).show()
        } else Toast.makeText(requireContext(), "Код неверный", Toast.LENGTH_LONG).show()

    }
    private fun setVisibleHouse(listAddress:List<AddressEntity>){
        if (listAddress.isEmpty()) {
            binding.houseSpinner.visibility = View.GONE
        } else if (listAddress.size == 1) {
            binding.houseSpinner.visibility = View.GONE
            viewModel.getFlatsList(listAddress[0].houseId)
        } else {
            binding.houseSpinner.visibility = View.VISIBLE
        }
    }
}



