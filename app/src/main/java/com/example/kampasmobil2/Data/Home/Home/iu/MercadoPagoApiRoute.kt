package com.example.kampasmobil2.Data.Home.Home.iu

import com.example.kampasmobil2.Card.RetrofitClient

class MercadoPagoApiRoute {
    val API_URL="https://api.mercadopago.com/"
    val retrofit=RetrofitClient()

    fun getMercadoPagoRoute():MercadoPagoRoute{
        return retrofit.getClient(API_URL).create(MercadoPagoRoute::class.java)
    }
}