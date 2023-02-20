package com.yuzhny.mykis.presentation.appartment.add.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.yuzhny.mykis.R
import com.yuzhny.mykis.domain.address.AddressEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.android.synthetic.main.drop_down_item.view.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HouseArrayAdapter @Inject constructor(@ApplicationContext context: Context, addressList:List<AddressEntity>)
    : ArrayAdapter<AddressEntity>(context , 0 , addressList) {
    val addressSelected = addressList
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }
    private fun initView(position: Int, convertView: View?, parent: ViewGroup): View {
        val address = getItem(position)
        val view = LayoutInflater.from(context).inflate(R.layout.drop_down_item , parent,false)
        view.item_address.text = address!!.house
        view.item_id.text = address.houseId.toString()
        return view
    }
}