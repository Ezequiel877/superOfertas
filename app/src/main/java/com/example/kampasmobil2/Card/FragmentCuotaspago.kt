package com.example.kampasmobil2.Card

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.kampasmobil2.R
import com.example.kampasmobil2.databinding.FragmentCuotaspagoBinding
import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class FragmentCuotaspago : Fragment(R.layout.fragment_cuotaspago) {

    private lateinit var binding: FragmentCuotaspagoBinding
    private var mercadooagoPorvider = MercadoPagoProvider()
    private val args by navArgs<FragmentCuotaspagoArgs>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCuotaspagoBinding.bind(view)
        getInstallments()
    }

    private fun getInstallments() {
        mercadooagoPorvider.getsInstallment(args.token, args.lastNumber)?.enqueue(object : Callback<JsonArray> {
            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {

                if (response.body() !=null){
                    val installments=response.body()!!.get(0).asJsonObject.get("payer_costs").asJsonArray
                    Log.d("TAGINSTALLMENTS", "onResponse:$response")
                    Log.d("TAGINSTALLMENTS", "onResponse:$installments ")
                }

            }

            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}