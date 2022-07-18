package com.example.kampasmobil2.Data.Home

import android.util.Log
import com.example.kampasmobil2.Core.Result
import com.example.kampasmobil2.DataSource.Comercios
import com.example.kampasmobil2.DataSource.Orden
import com.example.kampasmobil2.DataSource.direccion
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await


class DataSourceHome {

    suspend fun getClientes(): Result<List<Comercios>> {
        val listClientes = mutableListOf<Comercios>()
        val querySnatp =
            FirebaseFirestore.getInstance().collection("comercios").get().await()
        for (clientes in querySnatp.documents) {
            clientes.toObject(Comercios::class.java)?.let {
                listClientes.add(it)
            }
        }
        return Result.Succes(listClientes)
    }

    suspend fun getOferts(id: String): Result<List<Orden>> {
        val listClientes = mutableListOf<Orden>()
        var id_doc = id
        val datossid = FirebaseFirestore.getInstance().collection("comercios").document(id_doc)
        Log.d("TAGNAMEIDDOCUMENT", "getOferts: $id_doc")
        val dc = datossid.collection("productos").get().await()
        for (document in dc.documents) {
            Log.d("datasourcehome", "getOferts: ${document.data}")
            document.toObject(Orden::class.java)?.let {
                Log.d("datasourcehome", "getOferts: $it")
                listClientes.add(it)
            }
        }

        return Result.Succes(listClientes)

    }   

    suspend fun getDireccion(id: String): Result<List<direccion>> {
        val listClientes = mutableListOf<direccion>()
        var id_doc = id
        val datossid = FirebaseFirestore.getInstance().collection("user").document(id_doc)
        val dc = datossid.collection("direccion").get().addOnSuccessListener { documentr ->
            for (document in documentr.documents) {
                Log.d("datasourcehome", "getOferts: ${document.data}")
                document.toObject(direccion::class.java)?.let {
                    Log.d("datasourcehome", "getOferts: $it")
                    listClientes.add(it)
                }
            }

            /*documentr.let {
                val direccion=it.getString("direccion" )
                val ubicacion=it.getString("ubicacion")
                Log.d("TAGNAMEIDDOCUMENT", "getOferts: $id_doc $direccion $ubicacion")
                listClientes.add(direccion(direccion = direccion.toString(), ubicacion.toString()))

            }*/
        }.await()

        return Result.Succes(listClientes)

    }
}
