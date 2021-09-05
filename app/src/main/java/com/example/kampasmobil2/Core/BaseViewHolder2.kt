package com.example.kampasmobil2.Core

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.kampasmobil2.DataSource.DataSource
import com.example.kampasmobil2.DataSource.producto

abstract class BaseViewHolder2<T>(view: View): RecyclerView.ViewHolder(view) {
    abstract fun binda(item: DataSource)

}