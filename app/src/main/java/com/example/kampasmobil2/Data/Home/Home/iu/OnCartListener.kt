package com.example.kampasmobil2.Data.Home.Home.iu

import com.example.kampasmobil2.DataSource.Orden
import com.example.kampasmobil2.DataSource.direccion

interface OnCartListener {
    fun setQuanty(produc:Orden)
    fun showTotal(total:Int)
    fun onmodelClick(model: direccion)



}