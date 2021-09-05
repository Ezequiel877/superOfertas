package com.example.kampasmobil2.Data.Home.Home.iu.Favorite

import com.example.kampasmobil2.DataSource.DataSource


interface preference {
    suspend fun setLike():DataSource?
}