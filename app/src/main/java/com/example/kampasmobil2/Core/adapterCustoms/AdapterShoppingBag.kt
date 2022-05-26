package com.example.kampasmobil2.Core.adapterCustoms

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kampasmobil2.Core.Utils.SharePrf
import com.example.kampasmobil2.Data.Home.Home.iu.OnCartListener
import com.example.kampasmobil2.DataSource.DataSource
import com.example.kampasmobil2.R
import com.example.kampasmobil2.databinding.DetalleshppingbagBinding
import com.example.kampasmobil2.databinding.FragmentItemProductoBinding

class AdapterShoppingBag(private var context: Context,
                         private val producList: ArrayList<DataSource>, private val listener:OnCartListener
) : RecyclerView.Adapter<AdapterShoppingBag.ViewHolder>() {

    val share = SharePrf(context)

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = DetalleshppingbagBinding.bind(view)
        fun setListener(product: DataSource) {
            binding.bntMas.setOnClickListener {
            }
            binding.bntMENOS.setOnClickListener {
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view =
            LayoutInflater.from(context).inflate(R.layout.detalleshppingbag, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = producList[position]
        holder.setListener(product)
        holder.binding.Textview1Cantidad.text = product.precios.toString()
        holder.binding.texName.text = product.nombre
        holder.binding.texviewPrecioFinal.text = product.precio.toString()
        Glide.with(context).load(product.imagen).centerCrop().into(holder.binding.imagen)
        holder.binding.bntMas.setOnClickListener {
            add(product, holder)
        }
        holder.binding.bntMENOS.setOnClickListener {
            delete(product, holder)
        }

    }
    fun addCarritoList():List<DataSource> = producList

    override fun getItemCount(): Int = producList.size

    fun add(product: DataSource, holder: ViewHolder){
        val id = getIndexOf(holder.binding.texName.text.toString())
        product.precios = product.precios + 1
        producList[id].precios = product.precios
        holder.binding.Textview1Cantidad.text = "${product.precios}"
        holder.binding.texviewPrecioFinal.text = "${product?.precios * product.precio}"
        listener.showTotal(getTotal())
        share?.save("orden", producList)

    }

    fun updateProduc(product: DataSource) {
        val index = producList.indexOf(product)
        if (index != -1) {
            producList.set(index, product)
            notifyItemChanged(index)
            listener.showTotal(getTotal())
        }
    }

    private fun getIndexOf(idProducto: String): Int {
        var index = 0
        for (p in producList) {
            if (p.nombre == idProducto) {
                Log.d("INDEXSEELOGD", "getIndexOf:$p")

                return index
                break
            }
            index++
        }
        return -1
    }


    fun delete(product: DataSource, holder: ViewHolder) {

        if (product.precios > 1) {
            val id = getIndexOf(holder.binding.texName.text.toString())
            product.precios = product.precios - 1
            producList[id].precios = product.precios
            holder.binding.Textview1Cantidad.text = "${product?.precios.toString()}"
            holder.binding.texviewPrecioFinal.text = "${product?.precios * product.precio}"
            share.save("orden", producList)
            listener.showTotal(getTotal())
        }else{
            updateProduc(product)
            share.save("orden", producList)
            listener.showTotal(getTotal())
        }

    }
    fun getTotal(): Int {
        var total = 0
        for (s in producList) {
            total += (s.precioProduc * s.precios)
        }
        return total
    }


}
