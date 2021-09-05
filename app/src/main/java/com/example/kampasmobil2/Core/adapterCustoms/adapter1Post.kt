package com.example.kampasmobil2.Core.adapterCustoms

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kampasmobil2.Core.BaseViewHolder
import com.example.kampasmobil2.Core.BaseViewHolder2
import com.example.kampasmobil2.DataSource.DataSource
import com.example.kampasmobil2.DataSource.producto
import com.example.kampasmobil2.UI.Home.Blank1
import com.example.kampasmobil2.databinding.ImagendetalleBinding

class adapter1Postprivate(private val listaClientes: List<DataSource>, val itemCLick: Blank1) :
    RecyclerView.Adapter<BaseViewHolder2<*>>() {

    interface OnModelClick {
        fun onmodelClick(model: DataSource,)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder2<*> {
        val itemBanding =
            ImagendetalleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val TAKE = HomeScreemHolde(itemBanding, parent.context)
        itemBanding.root.setOnClickListener {
            val positon = TAKE.adapterPosition.takeIf {
                it != DiffUtil.DiffResult.NO_POSITION
            }
                ?: return@setOnClickListener
            itemCLick.onmodelClick(listaClientes[positon])


        }
        return TAKE
    }


    override fun onBindViewHolder(holder: BaseViewHolder2<*>, position: Int) {
        when (holder) {
            is HomeScreemHolde -> holder.binda(listaClientes[position])
        }
    }

    override fun getItemCount(): Int = listaClientes.size

    inner class HomeScreemHolde(val itemPost: ImagendetalleBinding, val context: Context) :
        BaseViewHolder2<DataSource>(itemPost.root) {
        override fun binda(item: DataSource) {
            Glide.with(context).load(item.imagen).centerCrop().into(itemPost.imagen)
            Glide.with(context).load(item.ImagenD).centerCrop().into(itemPost.imagen2)
            itemPost.texName.text = item.id
            itemPost.tex2.text = item.precios
            itemPost.textView.text = item.Cofertas
            itemPost.textView3.text = item.ubicacion

        }


    }

}