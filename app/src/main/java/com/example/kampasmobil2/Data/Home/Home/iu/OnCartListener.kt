package com.example.kampasmobil2.Data.Home.Home.iu

import com.example.kampasmobil2.DataSource.DataSource
import com.example.kampasmobil2.DataSource.direccion

interface OnCartListener {
    fun setQuanty(produc:DataSource)
    fun showTotal(total:Int)
    fun onmodelClick(model: direccion)



}