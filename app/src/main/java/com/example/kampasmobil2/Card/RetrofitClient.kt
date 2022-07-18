package com.example.kampasmobil2.Card

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    fun getClient(url:String):Retrofit{
        return Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()
    }
    fun getClienteWithToken(url:String,token:String):Retrofit{

        val cliente=OkHttpClient.Builder()
        cliente.addInterceptor {
            val request=it.request()
            val newrequest=request.newBuilder().header("Autorization", token)
            it.proceed(newrequest.build())
        }
        return Retrofit.Builder().baseUrl(url).client(cliente.build()).addConverterFactory(GsonConverterFactory.create()).build()

    }
}