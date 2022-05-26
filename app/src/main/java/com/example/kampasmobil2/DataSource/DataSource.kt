package com.example.kampasmobil2.DataSource

import com.example.kampasmobil2.Core.Result
import java.math.BigInteger
import java.sql.Timestamp

data class DataSource(
    var precios: Int = 0,
    @get:com.google.firebase.firestore.Exclude
    var precioProduc: Int = 0,
    val ImagenD: String = "",
    val imagen: String = "",
    val Cofertas: String = "",
    var detalles: String = "",
    @get:com.google.firebase.firestore.Exclude
    var precio: Int = 0,
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

)*/



fun Result<List<DataSource>>.toDatosInt():DataSource= DataSource(

    )
data class Producto(
    val precio: String,
    val nombre: String = "",
    val descripcion: String = "",
    val imagen: String = ""
)



