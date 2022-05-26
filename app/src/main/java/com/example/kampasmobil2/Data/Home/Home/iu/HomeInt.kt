package com.example.kampasmobil2.Data.Home.Home.iu

import android.util.Log
import com.example.kampasmobil2.Core.Result
import com.example.kampasmobil2.DataSource.DataSource
import com.example.kampasmobil2.DataSource.Producto
import com.example.kampasmobil2.DataSource.clientesIn
import com.example.kampasmobil2.DataSource.direccion

interface HomeInt {
    suspend fun getClientes():Result<List<DataSource>>
    suspend fun favoritos():Result<List<DataSource>>
    suspend fun getOfertas(id: String):Result<List<DataSource>>
    suspend fun getDireccion(id: String):Result<List<direccion>>

}