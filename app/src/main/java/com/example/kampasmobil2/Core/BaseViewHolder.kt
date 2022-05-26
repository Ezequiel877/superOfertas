package com.example.kampasmobil2.Core

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.kampasmobil2.DataSource.DataSource
import com.example.kampasmobil2.DataSource.Producto
import com.example.kampasmobil2.DataSource.clientesIn
import com.example.kampasmobil2.DataSource.direccion

abstract class BaseViewHolder <T>(view: View): RecyclerView.ViewHolder(view) {
    abstract fun bind(item: DataSource)

}
abstract class BaseViewDireccion <T>(view: View): RecyclerView.ViewHolder(view) {
    abstract fun bind(item: direccion)

}
