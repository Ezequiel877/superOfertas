package com.example.kampasmobil2.Core.adapterCustoms

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kampasmobil2.Core.BaseViewDireccion
import com.example.kampasmobil2.Core.BaseViewHolder2
import com.example.kampasmobil2.Core.Utils.SharePrf
import com.example.kampasmobil2.Data.Home.Home.iu.OnCartListener
import com.example.kampasmobil2.DataSource.DataSource
import com.example.kampasmobil2.DataSource.direccion
import com.example.kampasmobil2.R
import com.example.kampasmobil2.UI.Carrrito.Orden.FragmentDelicery
import com.example.kampasmobil2.UI.Home.Blank1
import com.example.kampasmobil2.databinding.ImagendetalleBinding
import com.google.gson.Gson

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