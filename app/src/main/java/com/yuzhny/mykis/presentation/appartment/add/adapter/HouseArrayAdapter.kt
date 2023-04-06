package com.yuzhny.mykis.presentation.appartment.add.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.yuzhny.mykis.R
import com.yuzhny.mykis.domain.address.AddressEntity


class HouseArrayAdapter ( context: Context, addressList:List<AddressEntity>)
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
        val itemAddress = view.findViewById<TextView>(R.id.item_address)
        val itemId = view.findViewById<TextView>(R.id.item_id)
        itemAddress.text = address!!.house
        itemId.text = address.houseId.toString()
        return view
    }
}