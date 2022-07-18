package com.example.kampasmobil2.Data.Home.Home.iu

import com.example.kampasmobil2.DataSource.Orden
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ofertasRepositoi {
    suspend fun getOfertas() {

        val listClientes = mutableListOf<Orden>()
        val querySnatp = FirebaseFirestore.getInstance().collection("Post_Clientes")
            .whereEqualTo("ofertas", "ofertas").get().addOnSuccessListener { id ->
                for (documentos in id) {
                    documentos.toObject(Orden::class.java).let {
                        listClientes.add(it)
                    }
                }
            }.await()
    }
  //addOnSuccessListener {
    //                    for (documentos in it){
    //                        documentos.toObject(DataSource::class.java).let {
    //                            listClientes.add(it)
    //                        }
    //                    }
    //                }.await()
    //
    //        return Result.Succes(listClientes)
    //    }
}