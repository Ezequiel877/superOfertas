package com.example.kampasmobil2.Core.firabese

data class OrdenFire(@get:com.google.firebase.firestore.Exclude var id:String="",var name:String="", val product:Map<String, PrductOrden> = hashMapOf(), val totalPrice:Int=0, val status:Int=0)
data class PrductOrden(@get:com.google.firebase.firestore.Exclude var id:String="",var name:String="", val cantidad:Int=0)
