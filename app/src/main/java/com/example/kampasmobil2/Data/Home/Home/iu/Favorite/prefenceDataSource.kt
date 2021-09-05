package com.example.kampasmobil2.Data.Home.Home.iu.Favorite

import com.example.kampasmobil2.Core.Result
import com.example.kampasmobil2.DataSource.DataSource
import com.example.kampasmobil2.DataSource.clientesIn
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class prefenceDataSource {
    suspend fun setLike(): Result.Succes<String> {
        val datos = FirebaseFirestore.getInstance().collection("Post_clientes").get().await()
        val model= mutableListOf(datos).toString()
        val user=FirebaseAuth.getInstance().currentUser
        user?.displayName?.let {
            FirebaseFirestore.getInstance().collection("Like").document().set(clientesIn(model.toString(), it))

        }
        return Result.Succes(model)
    }
}