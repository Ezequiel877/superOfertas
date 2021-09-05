package com.example.kampasmobil2.UI.Home.Implement

import com.example.kampasmobil2.Core.Result
import com.example.kampasmobil2.Data.Home.DataSourceHome
import com.example.kampasmobil2.Data.Home.Home.iu.HomeInt
import com.example.kampasmobil2.DataSource.DataSource
import com.example.kampasmobil2.DataSource.producto

class HomeImp(private val data:DataSourceHome):HomeInt {
    override suspend fun getClientes(): Result<List<DataSource>> =data.getClientes()
    override suspend fun favoritos(): Result<List<DataSource>> = data.getOferts()
}