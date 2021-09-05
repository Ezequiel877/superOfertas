package com.example.kampasmobil2.UI.LoginSetup.Firebase

import com.example.kampasmobil2.Data.Home.Home.iu.usuario.usuarioDtaSource
import com.example.kampasmobil2.Data.Home.Home.iu.usuario.usuariosInte
import com.google.firebase.auth.FirebaseUser

class FirebaseAuth(private val data:usuarioDtaSource):usuariosInte {
    override suspend fun singUp(email: String, password: String): FirebaseUser?=data.chetAndRemote(email, password)
}