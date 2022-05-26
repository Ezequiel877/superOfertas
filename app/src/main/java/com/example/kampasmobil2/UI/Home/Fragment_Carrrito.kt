package com.example.kampasmobil2.UI.Home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kampasmobil2.Core.Utils.SharePrf
import com.example.kampasmobil2.Core.adapterCustoms.AdapterShoppingBag
import com.example.kampasmobil2.Core.constantes.constantes
import com.example.kampasmobil2.Core.firabese.Order
import com.example.kampasmobil2.Core.firabese.PrductOrden
import com.example.kampasmobil2.Data.Home.Home.iu.OnCartListener
import com.example.kampasmobil2.DataSource.DataSource
import com.example.kampasmobil2.DataSource.direccion
import com.example.kampasmobil2.R
import com.example.kampasmobil2.Volley.NotificationS
import com.example.kampasmobil2.databinding.FragmentCarrritoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Fragment_Carrrito : Fragment(R.layout.fragment__carrrito), OnCartListener {

    private lateinit var binding: FragmentCarrritoBinding
    private lateinit var adapter: AdapterShoppingBag
    private val args by navArgs<Fragment_CarrritoArgs>()
    var share: SharePrf? = null
    var selectProducto = ArrayList<DataSource>()
    var json = Gson()
    private val total = 0.0
    private var token=""



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCarrritoBinding.bind(view)
        binding.recyclerCarf.layoutManager = LinearLayoutManager(context)
        share = SharePrf(requireContext())
        var doblw = 0


        binding.textDireccion.text = args.direccion
        binding.textUbicacion.text = args.ubicacion
        binding.texyArgsCommers.text = args.idcommers
        Log.d("CardArgDatos", "onCreateDialog: ${args.idcommers}")

        if (!share?.getData("orden").isNullOrBlank()) {
            val type = object : TypeToken<ArrayList<DataSource>>() {}.type
            selectProducto = json.fromJson(share?.getData("orden"), type)

            binding.let {
                adapter = context?.let { it1 -> AdapterShoppingBag(it1, selectProducto, this) }!!
                binding.recyclerCarf.adapter = adapter
            }

        }
        binding.idFlag.setOnClickListener {
            requestOrden()

        }
        for (s in selectProducto) {
            doblw += (s.precioProduc * s.precios)
        }
        setTotal(doblw)


    }

    private fun requestOrden() {
        val id = FirebaseAuth.getInstance().currentUser
        id?.let {
            val productos = hashMapOf<String, PrductOrden>()
            adapter.addCarritoList().forEach { prdoct ->
                productos.put(
                    prdoct.nombre,
                    PrductOrden(prdoct.nombre, prdoct.intCantidad, prdoct.precios)
                )
            }
            val orden =Order(nameCliente = it.uid, product = productos, totalPrice = total, status = 1)
            val user=FirebaseFirestore.getInstance().collection("user").document(it.uid)
            val id=user.collection("token").get().addOnSuccessListener {document->
                for ( d in document){

                    val tikenid=d.data
                    token +="${tikenid.getValue(constantes.token)}"
                    //contraseña citio web;Gvy4&v6qLciM)D6WMKU^
                }
                if (token.length >0 )token=token.dropLast(1)

            }
            val db = FirebaseFirestore.getInstance()
            val insert = db.collection("comercios").document(args.idcommers)
            insert.collection("ordenes").add(orden).addOnSuccessListener {
                val navegacion=Fragment_CarrritoDirections.actionFragmentCarrritoToFragmentPagos()
                findNavController().navigate(navegacion)
                val notificacion=NotificationS()
                notificacion.sendNotication("Tu pedido ha sido ${orden.status}", orden.nameCliente, token )

            }.addOnFailureListener {
                Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun setQuanty(produc: DataSource) {
        TODO("Not yet implemented")
    }

    override fun showTotal(total: Int) {
        TODO("Not yet implemented")
    }

    override fun onmodelClick(model: direccion) {
        TODO("Not yet implemented")
    }

    fun setTotal(total: Int) {
        binding!!.textTotalBag.text = "${total}"
    }

}