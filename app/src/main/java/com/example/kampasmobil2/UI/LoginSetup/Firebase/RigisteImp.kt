package com.example.kampasmobil2.UI.LoginSetup.Firebase

import com.example.kampasmobil2.Data.Home.Home.iu.usuario.usuarioDtaSource
import com.example.kampasmobil2.Data.Home.Home.iu.usuario.usuarioRegister
import com.google.firebase.auth.FirebaseUser

class RigisteImp(private val data:usuarioDtaSource):usuarioRegister {
    override suspend fun singUp(email: String, password: String, client: String): FirebaseUser? =data.chetAndRegistro(email, password, client)
}