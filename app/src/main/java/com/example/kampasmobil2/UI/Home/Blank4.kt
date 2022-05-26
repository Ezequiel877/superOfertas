package com.example.kampasmobil2.UI.Home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kampasmobil2.Card.CardFragmentDialog
import com.example.kampasmobil2.Core.Result
import com.example.kampasmobil2.Core.adapterCustoms.adapter1Postprivate
import com.example.kampasmobil2.Core.adapterCustoms.adapterDetalles
import com.example.kampasmobil2.Core.visivility.hide
import com.example.kampasmobil2.Core.visivility.show
import com.example.kampasmobil2.Data.Home.DataSourceHome
import com.example.kampasmobil2.Data.Home.Home.iu.OnCartListener
import com.example.kampasmobil2.DataSource.DataSource
import com.example.kampasmobil2.DataSource.MainAx
import com.example.kampasmobil2.DataSource.Producto
import com.example.kampasmobil2.DataSource.direccion
import com.example.kampasmobil2.R
import com.example.kampasmobil2.UI.Home.Implement.HomeImp
import com.example.kampasmobil2.databinding.FragmentBlank4Binding
import com.example.kampasmobil2.model.HomeScreemFactory
import com.example.kampasmobil2.model.viewModelPost
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.reflect.TypeToken

class Blank4 : Fragment(R.layout.fragment_blank4), adapterDetalles.OnModelClick, OnCartListener {

    private val args by navArgs<Blank4Args>()
    private lateinit var binding: FragmentBlank4Binding
    private val viewModel: viewModelPost by activityViewModels<viewModelPost> {
        HomeScreemFactory(
            HomeImp(
                DataSourceHome()
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBlank4Binding.bind(view)
        binding.textViewId.text = args.detalles
        Log.d("TEXTVIEWID", "onViewCreated: + $id")
        val id = binding.textViewId.text.toString()
        binding.let {
            it.btnVerCarrito.setOnClickListener {

            }
        }

        viewModel.getOfertas(id).observe(viewLifecycleOwner, Observer {
            when (it) {
                is Result.Loading -> {
                    Log.d("FIREBASE", "onViewCreatedloading: ${it.toString()}")
                    binding.deltarelative.show()


                }
                is Result.Succes -> {
                    Log.d("DETALLESDELASOFEERTAS", "onViewCreated: ${it.data}")
                    binding.deltarelative.hide()

                    binding.detallesO.adapter = adapterDetalles(it.data, this)


                }
                is Result.Failure -> {
                    binding.deltarelative.hide()
                    Log.d("FIREBASE", "onViewCreated:${it.exception} ")
                    Toast.makeText(
                        requireContext(),
                        "ocurrio un error:${it.exception}",
                        Toast.LENGTH_SHORT
                    ).show()

                }

            }
        })
        buttonRecycler()


        val userid = FirebaseAuth.getInstance().currentUser

    }

    fun setTotal(total: Int) {
        binding.tevcarrito.text = "${total}"
    }


    private fun buttonRecycler() {

        binding.btnVerCarrito.setOnClickListener {
           val fragmentDireccion=Blank4Directions.actionBlank4ToCardFragmentDialog(binding.textViewId.text.toString())
           findNavController().navigate(fragmentDireccion)
        }
    }
    /*
    private fun getProductShare() {

        if (!sharePref?.getData("orden").isNullOrBlank()) {
            val type = object : TypeToken<ArrayList<DataSource>>() {}.type
            selectProducto = json.fromJson(sharePref?.getData("orden"), type)

            val index = getIndexOf(bindinf.textViewId.text.toString())
            if (index != -1) {

                product = selectProducto[index]
                product?.precios = selectProducto[index].precios
                product?.precio = selectProducto[index].precio
                bindinf.textInputNewCantidad.text = "${product?.precios}"

                Log.d("DEBUGVALORTEXTVIEW", "getProductShare: ${product?.precios}")
                precioOF = product?.precio!! * product?.precios!!
                bindinf.ttPrecio.text = "${product?.precio}"
            }
            for (p in selectProducto) {
                Log.d("PRODUCTDETALLECARRITO", "getProductShare: $p ")
            }

        }
    }
*/

    override fun onmodelClick(model: DataSource) {
        val action = Blank4Directions.actionBlank4ToFragmentDetalleProducto(
            model.imagen,
            model.ImagenD,
            model.Cofertas,
            model.detalles,
            model.nombre,
            model.precios.toString(),
        )
        findNavController().navigate(action)

    }

    override fun setQuanty(produc: DataSource) {
        TODO("Not yet implemented")
    }

    override fun showTotal(total: Int) {
        setTotal(total)
    }

    override fun onmodelClick(model: direccion) {
        TODO("Not yet implemented")
    }
    /*      val id=binding.textViewId.text.toString()
                 id.let {
                     val datos = FirebaseFirestore.getInstance().collection("comercios").document(it).get()
                         .addOnSuccessListener { document ->
                             document?.let {id->
                                 Log.d("FIREBASE", "onViewCreated: ${id.data}")
                             }
                         }
                 }*/


    /*val productos=datos.collection("ofertas").get().addOnSuccessListener {
        val product=it.toObjects(DataSource::class.java)
        binding.detallesO.adapter=adapterDetalles(product)
    }
    binding.botonNotificacion.setOnClickListener {
        Firebase.messaging.subscribeToTopic("$id")
            .addOnCompleteListener { task ->
                var msg = getString(R.string.open)
                if (!task.isSuccessful) {
                    msg = getString(R.string.close)
                }

                Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
            }

    }*/
    /* fun setFavorite(comercio: DataSource) {
         val firebase = FirebaseFirestore.getInstance()
         firebase.collection("like").document().set(comercio).addOnSuccessListener {
             Toast.makeText(requireContext(), "sent favorite", Toast.LENGTH_SHORT).show()
         }

     }
     fun sent(binding: FragmentBlank4Binding) {
        val userid = FirebaseAuth.getInstance().currentUser
        binding.let {
            userid?.displayName.let { userid ->
                val datos = DataSource(
                    precios = it.favorite.text.toString()
                        .trim(),
                    Cofertas = it.carrito.text.toString()
                        .trim(),
                    detalles = it.textViewId.text.toString().trim(),
                    nombre = userid.toString(),
                    ImagenD = it.Imagencrop.toString()

                )
            }
        }

        binding.botonFav.setOnClickListener {
            binding.let { fracagment ->
                userid?.displayName?.let { userid ->
                    val datos = DataSource(
                        precios = fracagment.favorite.text.toString()
                            .trim(),
                        Cofertas = fracagment.carrito.text.toString()
                            .trim(),
                        detalles = fracagment.textViewId.text.toString().trim(),
                        nombre = userid,
                        ImagenD = fracagment.backList.toString(),
                        imagen = fracagment.Imagencrop.toString()

                    )

                }
            }
        }
    }

     */


/*

    override fun getProductCard(): MutableList<DataSource> {
        val mutablelist= mutableListOf<DataSource>()
        (1..5).forEach { int ->
            val produv =
                DataSource(int.toString(), "", "", "$int", "prudcto prueba", 2.3, "productoo", "", 5)
            mutablelist.add(produv)
        }
        return mutablelist
    }

    override fun getProductSelectedd(): DataSource? =producto
*/

}






