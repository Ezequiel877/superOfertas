package com.example.kampasmobil2.Core.adapterCustoms

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kampasmobil2.Core.BaseViewHolder
import com.example.kampasmobil2.DataSource.DataSource
import com.example.kampasmobil2.databinding.ImagendetalleBinding

class favoritosAdapter(private val listaClientes: List<DataSource>):
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBanding =
            ImagendetalleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val TAKE = HomeScreemHolder(itemBanding, parent.context)
        itemBanding.root.setOnClickListener {
            val positon = TAKE.adapterPosition.takeIf {
                it != DiffUtil.DiffResult.NO_POSITION
            }

        }
        return TAKE
    }



    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is HomeScreemHolder->holder.bind(listaClientes[position])
        }
    }

    override fun getItemCount(): Int =listaClientes.size

    private inner class HomeScreemHolder(val itemPost: ImagendetalleBinding, val context: Context) :
        BaseViewHolder<DataSource>(itemPost.root) {
        override fun bind(item: DataSource) {
            Glide.with(context).load(item.imagen).centerCrop().into(itemPost.imagen)
            itemPost.texName.text = item.id
            itemPost.tex2.text=item.precios
            itemPost.textView.text=item.id
        }


    }

}