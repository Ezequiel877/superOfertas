package com.example.kampasmobil2.Data.Home.Home.iu.usuario

import com.example.kampasmobil2.Data.Home.Home.iu.Favorite.prefenceDataSource
import com.example.kampasmobil2.DataSource.clientesIn
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class usuarioDtaSource {

        suspend fun chetAndRemote(email: String, password: String): FirebaseUser? {
            val passWork= FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).await()
            return passWork.user
        }
    suspend fun chetAndRegistro(email: String, password: String, client: String): FirebaseUser? {
        val passWork =
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).await()
        passWork.user?.uid?.let {
            FirebaseFirestore.getInstance().collection("user").document(it)
                .set(clientesIn(email, client, " username")).await()
        }
        return passWork.user

    }

    }
