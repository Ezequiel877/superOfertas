package com.example.kampasmobil2.UI.Home.Implement

import android.util.Log
import com.example.kampasmobil2.Core.Result
import com.example.kampasmobil2.Data.Home.DataSourceHome
import com.example.kampasmobil2.Data.Home.Home.iu.HomeInt
import com.example.kampasmobil2.DataSource.DataSource
import com.example.kampasmobil2.DataSource.Producto
import com.example.kampasmobil2.DataSource.clientesIn
import com.example.kampasmobil2.DataSource.direccion

class HomeImp(private val data: DataSourceHome) :
    HomeInt {
    override suspend fun getClientes(): Result<List<DataSource>> =data.getClientes()


    override suspend fun favoritos(): Result<List<DataSource>> {
        TODO("Not yet implemented")
    }

    override suspend fun getOfertas(id: String): Result<List<DataSource>> {
        Log.d("TAGFIREBASE", "getOfertas: $id")
        return data.getOferts(id)
    }

    override suspend fun getDireccion(id: String): Result<List<direccion>> {
        return data.getDireccion(id)
    }
}
