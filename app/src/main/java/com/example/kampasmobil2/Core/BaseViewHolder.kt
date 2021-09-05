package com.example.kampasmobil2.Core

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.kampasmobil2.DataSource.DataSource

abstract class BaseViewHolder <T>(view: View): RecyclerView.ViewHolder(view) {
    abstract fun bind(item: DataSource)

}