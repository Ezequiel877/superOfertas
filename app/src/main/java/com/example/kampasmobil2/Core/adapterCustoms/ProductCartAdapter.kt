package com.example.kampasmobil2.Core.adapterCustoms

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kampasmobil2.Card.CardFragmentDialog
import com.example.kampasmobil2.Core.Utils.SharePrf
import com.example.kampasmobil2.Data.Home.Home.iu.OnCartListener
import com.example.kampasmobil2.DataSource.DataSource
import com.example.kampasmobil2.DataSource.direccion
import com.example.kampasmobil2.R
import com.example.kampasmobil2.databinding.FragmentItemProductoBinding

class ProductCartAdapter(
    private var context: Context,
    private val producList: ArrayList<DataSource>, private var listener: OnCartListener) : RecyclerView.Adapter<ProductCartAdapter.ViewHolder>() {


    val share = SharePrf(context)

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = FragmentItemProductoBinding.bind(view)
        fun setListener(product: DataSource) {
            binding.bntMas.setOnClickListener {
            }
            binding.bntMENOS.setOnClickListener {
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_item_producto, parent, false)
        val post=ViewHolder(view)
        val lstList= ArrayList<direccion>()
        view.rootView.setOnClickListener {
            val positon = post.adapterPosition.takeIf {
                it != DiffUtil.DiffResult.NO_POSITION
            }
                ?: return@setOnClickListener
            listener.onmodelClick(lstList[positon])

        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = producList[position]
        holder.setListener(product)
        holder.binding.Textview1Cantidad.text = product.precios.toString()
        holder.binding.Textview2PRECIO.text = product.nombre
        holder.binding.preciotexto.text = product.precio.toString()
        Glide.with(context).load(product.imagen).centerCrop().into(holder.binding.imagenPro)
        holder.binding.bntMas.setOnClickListener {
            add(product, holder)
        }
        holder.binding.bntMENOS.setOnClickListener {
            delete(product, holder)
        }
        holder.binding.bntDelete.setOnClickListener {
            removeItem(position)
        }
            }

    override fun getItemCount(): Int = producList.size

    fun add(product: DataSource, holder: ViewHolder) {
        val id = getIndexOf(holder.binding.Textview2PRECIO.text.toString())
        product.precios = product.precios + 1
        producList[id].precios = product.precios
        producList[id].precioProduc = product.precioProduc
        holder.binding.Textview1Cantidad.text = "${product.precios}"
        holder.binding.preciotexto.text = "${product.precios * product.precioProduc.toInt()}"
        producList[id].precio=holder.binding.preciotexto.text.toString().toInt()
        listener.showTotal(getTotal())
        share.save("orden", producList)

    }

    fun getTotal(): Int {
        var total = 0
        for (s in producList) {
            total += (s.precioProduc * s.precios)
        }
        return total
    }

    private fun getIndexOf(idProducto: String): Int {
        var index = 0
        for (p in producList) {
            if (p.nombre == idProducto) {
                return index
                break
            }
            index++
        }
        return -1
    }


    fun delete(product: DataSource, holder: ViewHolder) {
        if (product.precios > 1) {
            val id = getIndexOf(holder.binding.Textview2PRECIO.text.toString())
            product.precios = product.precios -1
            producList[id].precios = product.precios
            holder.binding.Textview1Cantidad.text = "${product.precios}"
            val datos= product.precios * product.precioProduc.toInt()
            holder.binding.preciotexto.text = "$datos"
            producList[id].precio=holder.binding.preciotexto.text.toString().toInt()
            listener.showTotal(getTotal())
            share.save("orden", producList)
        }
    }

    private fun removeItem(id: Int) {
        producList.removeAt(id)
        notifyItemRemoved(id)
        notifyItemRangeRemoved(id, producList.size)
        listener.showTotal(getTotal())
        share.save("orden", producList)
    }
}
