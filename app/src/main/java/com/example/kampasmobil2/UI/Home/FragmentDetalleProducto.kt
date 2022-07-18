package com.example.kampasmobil2.UI.Home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.kampasmobil2.Core.Utils.SharePrf
import com.example.kampasmobil2.DataSource.Orden
import com.example.kampasmobil2.R
import com.example.kampasmobil2.databinding.FragmentDetalleProductoBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FragmentDetalleProducto : Fragment(R.layout.fragment_detalle_producto) {


    private lateinit var bindinf: FragmentDetalleProductoBinding
    private val args by navArgs<FragmentDetalleProductoArgs>()

    var sharePref: SharePrf? = null
    var selectProducto = ArrayList<Orden>()

    var product: Orden? = null
    var json = Gson()

    var counter = 1
    var precioOF = 0
    var precioProducto = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindinf = FragmentDetalleProductoBinding.bind(view)
        setHasOptionsMenu(true)
        bindinf.textViewId.text = args.descripcion
        bindinf.ttPrecioProduct.text = args.nombre

        sharePref = SharePrf(requireContext())
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

        product?.precio1 = bindinf.ttPrecio.text.toString().toInt()
        product?.nombre = bindinf.textViewId.text.toString()
        product?.precios2 = bindinf.textInputNewCantidad.text.toString().toInt()
        product?.descripcion = bindinf.iddescription.text.toString()
        product?.precioProduc = bindinf.ttPrecioProduct.text.toString().toInt()

        getProductShare()
        bindinf.extendedFloatingActionButton.setOnClickListener {

            addToBag()
        }

    }

    private fun addToBag() {
        product = Orden(
            "",
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
            if (product?.precio1 == 0) {
                product?.precio1 = 1
                Log.d("LOGDEADDBAG", "addToBag: LOGDEADDBAG   $product")
            }
            selectProducto.add(product!!)
        } else {
            selectProducto[index].precios2 = counter
            selectProducto[index].precio1 = bindinf.ttPrecio.text.trim().toString().toInt()
        }
        sharePref?.save("orden", selectProducto)
        Toast.makeText(context, "SE AÑADIO A TU CARRITO DE COMPRAS", Toast.LENGTH_SHORT).show()


    }

    private fun getProductShare() {
        sharePref = SharePrf(requireContext())

        if (!sharePref?.getData("orden").isNullOrBlank()) {
            val type = object : TypeToken<ArrayList<Orden>>() {}.type
            selectProducto = json.fromJson(sharePref?.getData("orden"), type)

            val index = getIndexOf(bindinf.textViewId.text.toString())
            if (index != -1) {

                product = selectProducto[index]
                product?.precios2 = selectProducto[index].precios2
                product?.precio1 = selectProducto[index].precio1
                product?.precioProduc = selectProducto[index].precioProduc
                bindinf.textInputNewCantidad.text = "${product?.precio1}"
                precioOF = product?.precio1!! * product?.precios2!!
                bindinf.ttPrecio.text = " Total $${product?.precios2}"
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
