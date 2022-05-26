package com.example.kampasmobil2.Data.Home.Home.iu.usuario

import com.google.firebase.auth.FirebaseUser

interface usuarioRegister {
    suspend fun singUp(email:String, password:String, client:String): FirebaseUser?
    suspend fun direccion(cliente:String, direccion:String, ubicacion:String)



}