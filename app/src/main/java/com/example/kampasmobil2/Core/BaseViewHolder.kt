package com.example.kampasmobil2.Core

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.kampasmobil2.DataSource.Orden
import com.example.kampasmobil2.DataSource.direccion

abstract class BaseViewHolder <T>(view: View): RecyclerView.ViewHolder(view) {
    abstract fun bind(item: Orden)

}
abstract class BaseViewDireccion <T>(view: View): RecyclerView.ViewHolder(view) {
    abstract fun bind(item: direccion)

}
