package com.example.kampasmobil2.DataSource

import java.sql.Timestamp

data class DataSource(
    val precios: String = "",
    val ImagenD: String = "",
    val imagen: String = "",
    val Cofertas: String="",
    val detalles: String = "",
    val id: String = "",
    val ubicacion: String = "",
    val picture: Timestamp? = null,
)

data class producto(val precio: String, val id: String = "", val ml: String = "", val imagen: String="")
data class clientesIn(val email: String, val name: String = "", val potho: String = "")



