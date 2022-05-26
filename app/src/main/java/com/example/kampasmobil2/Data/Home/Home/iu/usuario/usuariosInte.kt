package com.example.kampasmobil2.Data.Home.Home.iu.usuario

import com.google.firebase.auth.FirebaseUser

interface usuariosInte {
    suspend fun singUp(email:String, password:String): FirebaseUser?

}