package com.example.kampasmobil2.UI.Home.Implement

import android.util.Log
import com.example.kampasmobil2.Core.Result
import com.example.kampasmobil2.Data.Home.DataSourceHome
import com.example.kampasmobil2.Data.Home.Home.iu.HomeInt
import com.example.kampasmobil2.DataSource.*

class HomeImp(private val data: DataSourceHome) :
    HomeInt {
    override suspend fun getClientes(): Result<List<Comercios>> =data.getClientes()


    override suspend fun favoritos(): Result<List<Orden>> {
        TODO("Not yet implemented")
    }

    override suspend fun getOfertas(id: String): Result<List<Orden>> {
        Log.d("TAGFIREBASE", "getOfertas: $id")
        return data.getOferts(id)
    }

    override suspend fun getDireccion(id: String): Result<List<direccion>> {
        return data.getDireccion(id)
    }
}
