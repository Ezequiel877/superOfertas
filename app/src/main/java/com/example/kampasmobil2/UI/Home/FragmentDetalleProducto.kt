package com.example.kampasmobil2.UI.Home

import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.IntegerRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.kampasmobil2.Core.Utils.SharePrf
import com.example.kampasmobil2.DataSource.DataSource
import com.example.kampasmobil2.R
import com.example.kampasmobil2.databinding.FragmentDetalleProductoBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FragmentDetalleProducto : Fragment(R.layout.fragment_detalle_producto) {


    private lateinit var bindinf: FragmentDetalleProductoBinding
    private val args by navArgs<FragmentDetalleProductoArgs>()

    var sharePref: SharePrf? = null
    var selectProducto = ArrayList<DataSource>()

    var product: DataSource? = null
    var json = Gson()

    var counter = 1
    var precioOF = 0
    var precioProducto=0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindinf = FragmentDetalleProductoBinding.bind(view)
        setHasOptionsMenu(true)
        bindinf.textViewId.text = args.detalles
        bindinf.ttPrecio.text = args.nombre
        bindinf.ttPrecioProduct.text=args.nombre




        sharePref = SharePrf(requireContext())
        //val cantidad = bindinf?.ttPrecio!!.text.trim().toString().toInt()
        bindinf.imagenBtnRResta.setOnClickListener {
            if (counter > 1) {
                counter--
                precioOF = args.nombre.toInt() * counter
                bindinf!!.textInputNewCantidad.setText("$counter")
                bindinf!!.ttPrecio.text = "${precioOF}"
            }
        }

        bindinf!!.imagenBtnSuma.setOnClickListener {
            counter++
            precioOF = args.nombre.toInt() * counter
            bindinf!!.textInputNewCantidad.setText("$counter")
            bindinf!!.ttPrecio.setText("$precioOF")

        }

        product?.precio = bindinf.ttPrecio.text.toString().toInt()
        product?.nombre = bindinf.textViewId.text.toString()
        product?.precios = bindinf.textInputNewCantidad.text.toString().toInt()
        product?.detalles = bindinf.iddescription.text.toString()
        product?.precioProduc = bindinf.ttPrecioProduct.text.toString().toInt()

        getProductShare()
        bindinf.extendedFloatingActionButton.setOnClickListener {

            addToBag()
        }

    }

    private fun addToBag() {
        Log.d("precioPreductARG", "onViewCreated: ${args.nombre + args.cofertas + args.detalles} ")
        product = DataSource(
            bindinf.textInputNewCantidad.text.toString().toInt(),
            bindinf.ttPrecioProduct.text.toString().toInt(),
            "",
            "",
            "",
            "",
            bindinf.ttPrecio.text.toString().toInt(),
            bindinf.textViewId.text.toString()
        )


        var index = getIndexOf(bindinf?.textViewId!!.text.toString())
        Log.d("INDEXPRIMERID", "addToBag:${index} ")
        if (index == -1) {
            if (product?.precios == 0) {
                product?.precios = 1
                Log.d("LOGDEADDBAG", "addToBag: LOGDEADDBAG   $product")

            }
            selectProducto.add(product!!)
        } else {
            selectProducto[index].precios = counter
            selectProducto[index].precio = bindinf.ttPrecio.text.trim().toString().toInt()
        }
        sharePref?.save("orden", selectProducto)
        Toast.makeText(context, "SE AÑADIO A TU CARRITO DE COMPRAS", Toast.LENGTH_SHORT).show()


    }

    private fun getProductShare() {
        sharePref= SharePrf(requireContext())

        if (!sharePref?.getData("orden").isNullOrBlank()) {
            val type = object : TypeToken<ArrayList<DataSource>>() {}.type
            selectProducto = json.fromJson(sharePref?.getData("orden"), type)

            val index = getIndexOf(bindinf.textViewId.text.toString())
            if (index != -1) {

                product = selectProducto[index]
                product?.precios = selectProducto[index].precios
                product?.precio = selectProducto[index].precio
                product?.precioProduc = selectProducto[index].precioProduc
                bindinf.textInputNewCantidad.text = "${product?.precios}"

                Log.d("DEBUGVALORTEXTVIEW", "getProductShare: ${product?.precios}")
                precioOF = product?.precioProduc!! * product?.precios!!
                bindinf.ttPrecio.text = "${precioOF}"
            }
            for (p in selectProducto) {
                Log.d("PRODUCTDETALLECARRITO", "getProductShare: $p ")
            }

        }
    }

    private fun getIndexOf(idProducto: String): Int {
        var index = 0
        for (p in selectProducto) {
            if (p.nombre == idProducto) {
                Log.d("INDEXSEELOGD", "getIndexOf:$p")

                return index
                break
            }
            index++
        }
        return -1
    }
}
