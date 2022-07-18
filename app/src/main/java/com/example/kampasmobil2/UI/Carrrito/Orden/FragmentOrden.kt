package com.example.kampasmobil2.UI.Carrrito.Orden

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kampasmobil2.Core.Utils.SharePrf
import com.example.kampasmobil2.Core.adapterCustoms.OrdenAdapter
import com.example.kampasmobil2.Core.firabese.OrdenFire
import com.example.kampasmobil2.Data.Home.Home.iu.OrdenInterface
import com.example.kampasmobil2.Data.Home.Home.iu.StatusSeguimiento
import com.example.kampasmobil2.DataSource.Comercios
import com.example.kampasmobil2.R
import com.example.kampasmobil2.databinding.FragmentOrdenBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class FragmentOrden : Fragment(R.layout.fragment_orden), OrdenInterface, StatusSeguimiento {

    private lateinit var binding: FragmentOrdenBinding
    private lateinit var adapter: OrdenAdapter
    private val args by navArgs<FragmentOrdenArgs>()
    private var id: String = ""
    var sharePref: SharePrf? = null
    var selectProducto = ArrayList<Comercios>()
    var product: Comercios? = null
    var json = Gson()
    private lateinit var ordenselect:OrdenFire

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOrdenBinding.bind(view)
        recycler()
        setupRecycler()
        getOrden()
    }

    private fun setupRecycler() {
        adapter = OrdenAdapter(mutableListOf(), this)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@FragmentOrden.adapter
        }
    }

    private fun recycler() {
        sharePref = SharePrf(requireContext())
        var doblw = 0
        if (!sharePref?.getData("id").isNullOrBlank()) {
            val type = object : TypeToken<ArrayList<Comercios>>() {}.type
            selectProducto = json.fromJson(sharePref?.getData("id"), type)
        }
    }
    /*
    *
    * */
    private fun getOrden() {

        val firebase = FirebaseFirestore.getInstance().collection("comercios").document(args.id)
        firebase.collection("ordenes").get().addOnSuccessListener {
            for (d in it.documents) {
                val orden = d.toObject(OrdenFire::class.java)
                Log.d("tagdelrepodato", "recycler: $d")

                adapter.add(orden!!)
            }
        }.addOnFailureListener {
            Toast.makeText(context, "fallo al leer ordenes", Toast.LENGTH_SHORT).show()
        }
    }

    override fun orden(orden: OrdenFire) {

        val fragment=FragmentOrdenDirections.actionFragmentOrdenToFragmentSeguimiento()
        findNavController().navigate(fragment)

    }

    override fun onChatOrnde(orden: OrdenFire) {
        TODO("Not yet implemented")
    }

    override fun statusSeguimiento(): OrdenFire =ordenselect
}

