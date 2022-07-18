package com.example.kampasmobil2.Core.adapterCustoms

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kampasmobil2.Core.BaseViewDireccion
import com.example.kampasmobil2.Data.Home.Home.iu.OnCartListener
import com.example.kampasmobil2.DataSource.direccion
import com.example.kampasmobil2.databinding.ImagendetalleBinding

class Adapterdireccion(private val listaClientes: List<direccion>, val itemCLick: OnCartListener) :
    RecyclerView.Adapter<BaseViewDireccion<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewDireccion<*> {
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


    override fun onBindViewHolder(holder: BaseViewDireccion<*>, position: Int) {
        when (holder) {
            is HomeScreemHolde -> holder.bind(listaClientes[position])
        }
    }

    override fun getItemCount(): Int = listaClientes.size

    inner class HomeScreemHolde(val itemPost: ImagendetalleBinding, val context: Context) :
        BaseViewDireccion<direccion>(itemPost.root) {
        override fun bind(item: direccion) {
            itemPost.texName.text = item.ubicacion
            itemPost.tex2.text = item.direccion

        }
    }
}