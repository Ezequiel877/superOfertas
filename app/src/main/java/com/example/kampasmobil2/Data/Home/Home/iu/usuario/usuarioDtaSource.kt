package com.example.kampasmobil2.Data.Home.Home.iu.usuario

import com.example.kampasmobil2.DataSource.clientesIn
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class usuarioDtaSource {

    suspend fun chetAndRemote(email: String, password: String): FirebaseUser? {
        val passWork =
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).await()
        return passWork.user
    }

    suspend fun chetAndRegistro(
        email: String,
        password: String,
        client: String,
    ): FirebaseUser? {
        val passWork =
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).await()
        passWork.user?.uid?.let {
            FirebaseFirestore.getInstance().collection("user").document(it)
                .set(clientesIn(email, client, " username")).await()
        }
        return passWork.user

    }

    suspend fun getDireccion(
        client: String,
        direccion: String,
        ubicacion: String
    ) {
        val bd= FirebaseFirestore.getInstance()
        bd.collection("user").document(client).collection("direccion").document().set(hashMapOf("Direccion" to client, "ubicacion" to ubicacion,"direccion" to direccion)).await()

    }

/*
    suspend fun getID(id: String): Result<List<Producto>> {
        val listClientes = mutableListOf<Producto>()
        val datossid =
            FirebaseFirestore.getInstance().collection("user").document("${id}").get()
                .addOnSuccessListener {
                    if (it.exists()) {
                        val direcion = it.getString("direccion")
                        val ubicacion = it.getString("ubicacion")
                        val data = Producto(direcion!!, ubicacion!!)
                        listClientes.add(data)

                    }
                }.await()
        return Result.Succes(listClientes)
    }
    */
}
