package com.example.kampasmobil2.DataSource

data class clientesIn(
    val email: String,
    var name: String = "",
    val potho: String = "",
    var direccioN: direccion ?= null,
    var ubicacion: String = ""
)
data class direccion(var direccion:String="", var ubicacion: String="", var ciudad:String="")