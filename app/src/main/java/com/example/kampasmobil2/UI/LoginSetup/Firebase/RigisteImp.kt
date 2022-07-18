package com.example.kampasmobil2.UI.LoginSetup.Firebase

import com.example.kampasmobil2.Data.Home.Home.iu.usuario.usuarioDtaSource
import com.example.kampasmobil2.Data.Home.Home.iu.usuario.usuarioRegister
import com.google.firebase.auth.FirebaseUser

class RigisteImp(private val data:usuarioDtaSource):usuarioRegister {
    override suspend fun singUp(email: String, password: String, client: String): FirebaseUser? =data.chetAndRegistro(email, password, client)
    override suspend fun direccion(cliente: String, direccion: String, ubicacion: String) =data.getDireccion(cliente, ubicacion, direccion)
    /*
    * override suspend fun getDireccion(direccion: String): Result<List<Producto>> {
        return data.getID(direccion)}
}*/}