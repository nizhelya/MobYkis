package com.yuzhny.mykis.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.yuzhny.mykis.R
import com.yuzhny.mykis.data.cache.appartment.AppartmentRepository
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AppartmentFragment : Fragment() {


    @Inject
    lateinit var appartment :AppartmentRepository


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_appartment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        try{
            lifecycleScope.launch {
                val appartmentTest:AppartmentEntity = appartment.remoteGetAppartments(6314)
            }

//        }catch(e:Exception){
//            Toast.makeText(activity , "Неудачно" , Toast.LENGTH_LONG).show()
//        }

    }


}