package com.example.kampasmobil2.Core.adapterCustoms

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kampasmobil2.Core.firabese.OrdenFire
import com.example.kampasmobil2.Data.Home.Home.iu.OrdenInterface
import com.example.kampasmobil2.R
import com.example.kampasmobil2.databinding.OrdenPedidosInitBinding

class OrdenAdapter(
    private val ordenList: MutableList<OrdenFire>,
    private val listener: OrdenInterface
) : RecyclerView.Adapter<OrdenAdapter.ViewHolder>() {

    private lateinit var context: Context
    private val aValues:Array<String> by lazy {
        context.resources.getStringArray(R.array.status_value)
    }
    private val aStatus:Array<Int> by lazy {
        context.resources.getIntArray(R.array.status_key).toTypedArray()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context=parent.context
        val inflate= LayoutInflater.from(context).inflate(R.layout.orden_pedidos_init, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val orden=ordenList[position]
        holder.clicker(orden)
        holder.binding.tid.text=orden.id

        var name=""
        orden.product.forEach{
            name += "${it.value.name}"
        }

        holder.binding.nombredelosproduct.text=name.dropLast(2)
        holder.binding.totaprecio.text=orden.totalPrice.toString()
        val index=aStatus.indexOf(orden.status)
        val statusOrden=if (index != -1) aValues[index] else context.getString(R.string.orden_track_)
        holder.binding.estado.text=context.getString(R.string.orden_status, statusOrden)

    }

    override fun getItemCount(): Int =ordenList.size

    fun add(orden: OrdenFire){
        ordenList.add(orden)
        notifyItemInserted(ordenList.size - 1)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val binding = OrdenPedidosInitBinding.bind(view)

        fun clicker(orden: OrdenFire) {
            binding.chip1.setOnCloseIconClickListener {
                listener.onChatOrnde(orden)
            }
            binding.btnorden.setOnClickListener {
                listener.orden(orden)
            }
        }

    }

}