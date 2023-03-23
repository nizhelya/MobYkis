package com.yuzhny.mykis.presentation.appartment.util

import android.view.View

fun trueOrFalse(byte: Byte):Boolean = byte==1.toByte()

fun hideIfEmpty(text:String?, firstView: View){
    if(text.isNullOrEmpty() || text == """""" || text =="\"\"" || text =="0"){
        firstView.visibility = View.GONE
    }else{
        firstView.visibility = View.VISIBLE
    }
}
fun isEmptyFun(text:String?):Boolean = !(text.isNullOrEmpty() || text == """""" || text =="\"\"" || text =="0" || text == " ")
