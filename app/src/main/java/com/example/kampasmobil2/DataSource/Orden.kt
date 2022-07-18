package com.example.kampasmobil2.DataSource

import com.example.kampasmobil2.Core.Result
import java.sql.Timestamp

data class Orden(
    var precio:String="",
    var precio1: Int = 0,
    @get:com.google.firebase.firestore.Exclude
    var precioProduc: Int = 0,
    val ImagenD: String = "",
    val imagen: String = "",
    val cantidad: String = "",
    var descripcion: String = "",
    @get:com.google.firebase.firestore.Exclude
    var precios2: Int = 0,
    var nombre: String = "",
    val ubicacion: String = "",
    @get:com.google.firebase.firestore.Exclude
    var intCantidad: Int = 1,
    var product_Type: String = "",
    val picture: Timestamp? = null,
)


    /*(
@Entity
data class DataProductoEntity()
    @PrimaryKey
    var id: Int = 0,
    @ColumnInfo(name = "cantidad")
    var cantidad: Int = 0,
    @ColumnInfo(name = "ImagenPortada")
    val imagenPortad: String = "",
    @ColumnInfo(name = "imagen")
    val imagen: String = "",
    @ColumnInfo(name = "cantidadOfertas")
    val Cofertas: String = "",
    @ColumnInfo(name = "detallleDproducto")
    val detalles: String = "",
    @get:com.google.firebase.firestore.Exclude
    val precioProduc: Double = 0.0,
    @ColumnInfo(name = "nombre")
    val nombre: String = "",
    @ColumnInfo(name = "ubicacion")
    val ubicacion: String = "",
    @get:com.google.firebase.firestore.Exclude
    val intCantidad: Int = 1,
    @ColumnInfo(name = "product_Type")
    var product_Type: String = "",
fun DataSource.toProductoEntity(): DataProductoEntity = DataProductoEntity(

    this.precios,
    this.precio.toInt(),
    this.nombre,
    this.Cofertas,
    this.ImagenD,
    this.imagen,
    this.detalles.toDouble(),
    this.ubicacion,
    this.ubicacion,
    this.intCantidad,

    )
#!/bin/bash

sudo apt update
sudo apt install apt-transport-https ca-certificates curl software-properties-common -y
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu focal stable"
sudo apt update
apt-cache policy docker-ce
sudo apt install docker-ce -y
sudo usermod -aG docker ${USER}
sudo su - ${USER}

)*/
data class Comercios(val email: String = "", val contraseña:String="", val id: String = "", val photo: String = "", val direccioo:String="", val horarios:String="")

fun Result<List<Orden>>.toDatosInt():Orden= Orden(

    )
