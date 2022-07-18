package com.example.kampasmobil2.Core.adapterCustoms

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kampasmobil2.Core.BaseViewHolder
import com.example.kampasmobil2.DataSource.Orden
import com.example.kampasmobil2.UI.Home.Blank4
import com.example.kampasmobil2.databinding.ImagendetalleBinding

class adapterDetalles(private val listaClientes: List<Orden>, val itemCLick: Blank4) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {


    interface OnModelClick {
        fun onmodelClick(model: Orden)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBanding =
                ImagendetalleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val TAKE = HomeScreem(itemBanding, parent.context)
        itemBanding.root.setOnClickListener {
            val positon = TAKE.adapterPosition.takeIf {
                it != DiffUtil.DiffResult.NO_POSITION
            }
                ?: return@setOnClickListener
            itemCLick.onmodelClick(listaClientes[positon])


        }
        return TAKE
    }


    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is HomeScreem -> holder.bind(listaClientes[position])
        }
    }

    override fun getItemCount(): Int = listaClientes.size

    inner class HomeScreem(val itemPost: ImagendetalleBinding, val context: Context) :
        BaseViewHolder<Orden>(itemPost.root) {
        override fun bind(item: Orden) {
            Glide.with(context).load(item.imagen).centerCrop().into(itemPost.imagenconfirme)
            itemPost.texName.text = item.nombre
            itemPost.textView.text = item.descripcion
            itemPost.tex2.text = item.precio


        }


    }

}