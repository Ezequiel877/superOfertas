package com.example.kampasmobil2.UI.Home.Implement

import com.example.kampasmobil2.Data.Home.Home.iu.Favorite.prefenceDataSource
import com.example.kampasmobil2.Data.Home.Home.iu.Favorite.preference
import com.example.kampasmobil2.DataSource.Orden

 class FavoriteImpl(private val data:prefenceDataSource):preference{
     override suspend fun setLike(): Orden? {
         TODO("Not yet implemented")
     }


 }