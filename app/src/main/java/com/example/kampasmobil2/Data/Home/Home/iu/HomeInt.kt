package com.example.kampasmobil2.Data.Home.Home.iu

import com.example.kampasmobil2.Core.Result
import com.example.kampasmobil2.DataSource.DataSource
import com.example.kampasmobil2.DataSource.producto

interface HomeInt {
    suspend fun getClientes():Result<List<DataSource>>
    suspend fun favoritos():Result<List<DataSource>>


}