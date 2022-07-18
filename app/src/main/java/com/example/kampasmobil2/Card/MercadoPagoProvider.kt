package com.example.kampasmobil2.Card

import com.example.kampasmobil2.Data.Home.Home.iu.MercadoPagoApiRoute
import com.example.kampasmobil2.Data.Home.Home.iu.MercadoPagoRoute
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call

class MercadoPagoProvider {
    var mercadopagoRoute:MercadoPagoRoute?=null

    init {
        val api=MercadoPagoApiRoute()
        mercadopagoRoute=api.getMercadoPagoRoute()

    }

    fun createCardTOken(mercadopago:MercadoPagoCardTokenBody): Call<JsonObject>? {
        return mercadopagoRoute?.createCardToken(mercadopago)
    }

    fun getsInstallment(bin:String, amount:String):Call<JsonArray>?{
        return mercadopagoRoute?.getInstallments(bin,amount)
    }
}