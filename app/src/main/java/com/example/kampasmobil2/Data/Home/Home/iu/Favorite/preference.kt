package com.example.kampasmobil2.Data.Home.Home.iu.Favorite

import com.example.kampasmobil2.DataSource.Orden


interface preference {
    suspend fun setLike():Orden?
}