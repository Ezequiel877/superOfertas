package com.example.kampasmobil2.Data.Home

import com.example.kampasmobil2.Core.Result
import com.example.kampasmobil2.DataSource.DataSource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await


class DataSourceHome {
    suspend fun getClientes(): Result<List<DataSource>> {
        val listClientes = mutableListOf<DataSource>()
        val querySnatp =
            FirebaseFirestore.getInstance().collection("coleccion").get().await()
        for (clientes in querySnatp.documents) {
            clientes.toObject(DataSource::class.java)?.let {

                listClientes.add(it)
            }
        }
        return Result.Succes(listClientes)

    }

    suspend fun getOferts(): Result<List<DataSource>> {
        val listClientes = mutableListOf<DataSource>()
        val datossid= FirebaseFirestore.getInstance().collection("coleccion")
        val id=datossid.id
        val datoss = FirebaseFirestore.getInstance().collection("coleccion/{${id}/ofertas").get().await()
        for (clientes in datoss.documents) {
            clientes.toObject(DataSource::class.java)?.let {

                listClientes.add(it)
            }
        }

        return Result.Succes(listClientes)

    }
}