package com.yuzhny.mykis.presentation.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.yuzhny.mykis.R
import com.yuzhny.mykis.data.cache.appartment.AppartmentRepository
import com.yuzhny.mykis.data.cache.database.AppDatabase
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
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
        appartment.addAppartment(loadAppartment())

    }
    fun loadAppartment():List<AppartmentEntity> {
        return listOf<AppartmentEntity>(
            AppartmentEntity(
                0,
                "Химиков 14",
                "Шулик Родион Виталиевич",
                "ШРВ",
                "GFG65464564",
                "25/06/2022",
                50.00,
                45.00,
                5.00,
                0.00,
                50.00,
                4,
                1,
                0,
                "rshulik74@gmail.com",
                "+380634742842",
                "4323534654"

            )
        )
    }
}