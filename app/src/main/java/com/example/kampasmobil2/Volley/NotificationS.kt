package com.example.kampasmobil2.Volley

import android.util.Log
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.kampasmobil2.Core.constantes.constantes
import org.json.JSONException
import org.json.JSONObject
import kotlin.jvm.Throws

class NotificationS {

    fun sendNotication(title: String, messege: String, token: String) {
        val params=JSONObject()
        params.put(constantes.Method_, constantes.SEND_NOTIF)
        params.put(constantes.title_, title)
        params.put(constantes.message, messege)
        params.put(constantes.token, token)

        val jsonObject:JsonObjectRequest= object :JsonObjectRequest(Method.POST,constantes.APPWEB, params, Response.Listener { response->

            try {
                val succes=response.getInt(constantes.Success)
                Log.d("NOTIFVOLLEY", "sendNotication: ${succes.toString()} ")
                Log.d("NOTIFVOLLEY", "sendNotication: ${response.toString()} ")
            }catch (e:JSONException){
                e.printStackTrace()
                Log.e("volleyid", "sendNotication: ${e.localizedMessage}")
            }
        }, Response.ErrorListener {error->
            if (error.localizedMessage != null){
                error.message
                  Log.e("NOTIFVOLLEY", "sendNotication: ${error.localizedMessage} + ${error.message}")
            }
        }){
            @Throws(AuthFailureError::class)
            override fun getHeaders(): MutableMap<String, String> {
                val headers=HashMap<String, String>()
                headers["Content-Type"]="application/json; charset=utf-8"
                return super.getHeaders()
            }
        }
        MarketIt.volleyHelper.addtoRequest(jsonObject)
    }
}