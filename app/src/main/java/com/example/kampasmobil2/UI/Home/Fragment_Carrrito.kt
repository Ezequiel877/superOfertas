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
import com.example.kampasmobil2.Core.firabese.OrdenFire
import com.example.kampasmobil2.Core.firabese.PrductOrden
import com.example.kampasmobil2.Data.Home.Home.iu.OnCartListener
import com.example.kampasmobil2.DataSource.Orden
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
    var selectProducto = ArrayList<Orden>()
    var json = Gson()
    private val total = 0.0
    private val id = ""
    private var token = ""
    private var ordendesc = ""
    private var doblw = 0
    private  var nametoken = ""


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCarrritoBinding.bind(view)
        binding.recyclerCarf.layoutManager = LinearLayoutManager(context)
        share = SharePrf(requireContext())
        binding.textDireccion.text = args.direccion
        binding.textUbicacion.text = args.ubicacion
        binding.texyArgsCommers.text = args.idcommers

        if (!share?.getData("orden").isNullOrBlank()) {
            val type = object : TypeToken<ArrayList<Orden>>() {}.type
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
            doblw +=  s.precios2
            Log.d("TAGCarritoDialog", "recycler: $s")

        }
        setTotal(doblw)
    }

    private fun requestOrden() {
        val iduser = FirebaseAuth.getInstance().currentUser
        iduser?.let {
            val productos = hashMapOf<String, PrductOrden>()
            adapter.addCarritoList().forEach { prdoct ->
                productos.put(
                    prdoct.nombre,
                    PrductOrden("","${  prdoct.nombre}", prdoct.precio1)
                )
            }
            val usertoken = FirebaseFirestore.getInstance().collection("user").document(iduser.uid)
                .get().addOnSuccessListener {document->
                document.let {
                    val ittimedata = document.getString("token")
                    nametoken = ittimedata!!
                    Log.d("TAGDATOSTOKEN", "requestOrden: $nametoken")
                }
            }

            val orden = OrdenFire(
                "",
                nametoken,
                product = productos,
                totalPrice = doblw
            )
            val user = FirebaseFirestore.getInstance().collection("comercios").document(args.idcommers)
            val tokenEcommers = user.collection("token").get().addOnSuccessListener { document ->
                for (d in document) {

                    val tokendelServer = d.data
                    token += "${tokendelServer.getValue(constantes.token)},"
                    //contraseña citio web;Gvy4&v6qLciM)D6WMKU^
                }
                if (token.length > 0) token = token.dropLast(1)

            }
            orden.product.forEach {
                ordendesc += "${it.value.id}, "

            }
            ordendesc = ordendesc.dropLast(2)
            val db = FirebaseFirestore.getInstance()
            val insert = db.collection("comercios").document(args.idcommers)
            insert.collection("ordenes").add(orden).addOnSuccessListener {
                val notificacion = NotificationS()
                notificacion.sendNotication("Tu pedido ha sido ", ordendesc, token)
                Log.d("CardArgDatosDelanotif", "onCreateDialog: $ordendesc $token ")
                Toast.makeText(context, "notificacion enviada", Toast.LENGTH_SHORT).show()
                val navegacion = Fragment_CarrritoDirections.actionFragmentCarrritoToFragmentPagos(doblw.toString())
                findNavController().navigate(navegacion)

            }.addOnFailureListener {
                Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
            }

        }
    }


    override fun setQuanty(produc: Orden) {
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