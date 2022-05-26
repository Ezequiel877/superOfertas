package com.example.kampasmobil2.Core.firabese

data class Order( val nameCliente:String, val product:Map<String, PrductOrden> = hashMapOf(), val totalPrice:Double=0.0, val status:Int=0)
data class PrductOrden(val id:String, val name:Int, val cantidad:Int)
