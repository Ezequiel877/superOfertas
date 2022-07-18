package com.example.kampasmobil2.Data.Home.Home.iu

import com.example.kampasmobil2.Core.Result
import com.example.kampasmobil2.DataSource.*

interface HomeInt {
    suspend fun getClientes():Result<List<Comercios>>
    suspend fun favoritos():Result<List<Orden>>
    suspend fun getOfertas(id: String):Result<List<Orden>>
    suspend fun getDireccion(id: String):Result<List<direccion>>

}