package com.example.kampasmobil2.Core.adapterCustoms

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kampasmobil2.Core.ComercioHolder
import com.example.kampasmobil2.DataSource.Comercios
import com.example.kampasmobil2.UI.Home.Blank1
import com.example.kampasmobil2.databinding.ImagendetalleBinding

class adapter1Postprivate(private val listaClientes: List<Comercios>, val itemCLick: Blank1) :
    RecyclerView.Adapter<ComercioHolder<*>>() {


    interface OnModelClick {
        fun onmodelClick(model: Comercios)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComercioHolder<*> {
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


    override fun onBindViewHolder(holder: ComercioHolder<*>, position: Int) {
        when (holder) {
            is HomeScreemHolde -> holder.binda(listaClientes[position])
        }
    }

    override fun getItemCount(): Int = listaClientes.size

    inner class HomeScreemHolde(val itemPost: ImagendetalleBinding, val context: Context) :
        ComercioHolder<Comercios>(itemPost.root) {
        override fun binda(item: Comercios) {

            itemPost.texName.text = item.id
            itemPost.tex2.text = item.direccioo
            itemPost.textView.text = item.horarios

        }
    }
}