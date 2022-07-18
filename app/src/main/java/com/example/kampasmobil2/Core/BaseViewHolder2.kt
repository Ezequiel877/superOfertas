package com.example.kampasmobil2.Core

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.kampasmobil2.DataSource.Comercios
import com.example.kampasmobil2.DataSource.Orden


abstract class BaseViewHolder2<T>(view: View): RecyclerView.ViewHolder(view) {
    abstract fun binda(item: Orden)

}

abstract class ComercioHolder<T>(view: View): RecyclerView.ViewHolder(view) {
    abstract fun binda(item: Comercios)

}