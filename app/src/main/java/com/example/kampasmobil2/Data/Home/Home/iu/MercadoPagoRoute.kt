package com.example.kampasmobil2.Data.Home.Home.iu

import com.example.kampasmobil2.Card.MercadoPagoCardTokenBody
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MercadoPagoRoute {

    @GET("v1/payment_methods/installments?access_token=TEST-3743146975726078-071707-905bdd0053f00ddfa904489df0d4ce73-259551641")
    fun getInstallments(@Query("bin")bin:String, @Query("amount")amount:String):Call<JsonArray>

    @POST("v1/card_tokens?public_key=TEST-d4d5c5bf-d5bc-4f05-927f-96488c12f157")
    fun createCardToken(@Body body:MercadoPagoCardTokenBody):Call<JsonObject>
}

