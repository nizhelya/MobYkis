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
import com.yuzhny.mykis.presentation.appartment.add.adapter.FlatArrayAdapter
import com.yuzhny.mykis.presentation.appartment.add.adapter.HouseArrayAdapter
import com.yuzhny.mykis.presentation.appartment.add.adapter.StreetArrayAdapter
import javax.inject.Inject


class AddAppartmentFragment : Fragment()  {

    private val viewModel: AddAppartmentViewModel by activityViewModels()

    private var _binding: FragmentAddAppartmentBinding? = null
    private val binding get() = _binding!!
    private var chooseBlockId :Int = 0
    private var chooseHouseId :Int = 0
    private var chooseSecretCode :Long = 0
    @Inject
    lateinit var blockAdapter: BlockArrayAdapter

    @Inject
    lateinit var streetAdapter: StreetArrayAdapter

    @Inject
    lateinit var houseAdapter:HouseArrayAdapter

    @Inject
    lateinit var flatAdapter:FlatArrayAdapter

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

            binding.blockSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
                ) {
                        viewModel.getStreetList(blockAdapter.addressSelected[position].blockId)
                    chooseBlockId = blockAdapter.addressSelected[position].blockId
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
                viewModel.getHousesList(streetAdapter.addressSelected[position].streetId , chooseBlockId)
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
        viewModel.flats.observe(this.viewLifecycleOwner){
            it -> it?.let {
                flatAdapter = FlatArrayAdapter(requireContext() , it)
                binding.flatSpinner.adapter = flatAdapter
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
                binding.checkCode.visibility = View.VISIBLE
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
        binding.tipCode.setOnClickListener { Toast.makeText(requireContext(),"Код можна отримати в касах прийому комунальних платежів при оплаті. Код знаходиться у верхньому лівому кутку роздруківки про оплату"
            ,Toast.LENGTH_LONG).show()
        }
        binding.checkCode.setOnClickListener { checkSecretCode(chooseSecretCode.toString(),
            binding.codeInput.text.toString()
        ) }

    }
    private fun checkSecretCode(secretCode:String , userSecretCode:String) {
        if(secretCode==userSecretCode){
            Toast.makeText(requireContext(), "Код верный" , Toast.LENGTH_LONG).show()
        }else Toast.makeText(requireContext(), "Код неверный" , Toast.LENGTH_LONG).show()

    }

}



