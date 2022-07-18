package com.example.kampasmobil2.UI.Carrrito

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kampasmobil2.Card.CardFragmentDialogArgs
import com.example.kampasmobil2.Card.Cardholder
import com.example.kampasmobil2.Card.MercadoPagoCardTokenBody
import com.example.kampasmobil2.Card.MercadoPagoProvider
import com.example.kampasmobil2.Core.toas
import com.example.kampasmobil2.R
import com.example.kampasmobil2.databinding.FragmentPagosBinding
import com.google.gson.JsonObject
import io.stormotion.creditcardflow.CreditCard
import io.stormotion.creditcardflow.CreditCardFlowListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentPagos : Fragment(R.layout.fragment_pagos) {

    private lateinit var binding:FragmentPagosBinding

    var cvv=""
    var cardHolder=""
    var cardNumber=""
    var cardExpired=""
    private var mercadooagoPorvider:MercadoPagoProvider= MercadoPagoProvider()
    private val args by navArgs<FragmentPagosArgs>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentPagosBinding.bind(view)
        binding.credictCard.setCreditCardFlowListener(object :CreditCardFlowListener{
            override fun onActiveCardNumberBeforeChangeToNext() {
            }

            override fun onActiveCardNumberBeforeChangeToPrevious() {
            }

            override fun onCardCvvBeforeChangeToNext() {
            }

            override fun onCardCvvBeforeChangeToPrevious() {
            }

            override fun onCardCvvValidatedSuccessfully(cvv: String) {
            }

            override fun onCardCvvValidationFailed(cvv: String) {
            }

            override fun onCardExpiryDateBeforeChangeToNext() {
            }

            override fun onCardExpiryDateBeforeChangeToPrevious() {
            }

            override fun onCardExpiryDateInThePast(expiryDate: String) {
            }

            override fun onCardExpiryDateValidatedSuccessfully(expiryDate: String) {
            }

            override fun onCardExpiryDateValidationFailed(expiryDate: String) {
            }

            override fun onCardHolderBeforeChangeToNext() {
            }

            override fun onCardHolderBeforeChangeToPrevious() {
            }

            override fun onCardHolderValidatedSuccessfully(cardHolder: String) {
            }

            override fun onCardHolderValidationFailed(cardholder: String) {
            }

            override fun onCardNumberValidatedSuccessfully(cardNumber: String) {
            }

            override fun onCardNumberValidationFailed(cardNumber: String) {
            }

            override fun onCreditCardFlowFinished(creditCard: CreditCard) {
                cardExpired=creditCard.holderName.toString()
                cardNumber=creditCard.number.toString()
                cvv=creditCard.expiryDate.toString()
                cardHolder=creditCard.cvc.toString()
                Log.d("TAGPROCESARPAGOS", "cvv:$cvv ")
                Log.d("TAGPROCESARPAGOS", "cardExpeired:$cardNumber ")
                Log.d("TAGPROCESARPAGOS", "nombre:$cardHolder ")
                Log.d("TAGPROCESARPAGOS", "expired:$cardExpired ")

                credictcardToken()
            }

            override fun onFromActiveToInactiveAnimationStart() {
            }

            override fun onFromInactiveToActiveAnimationStart() {
            }

            override fun onInactiveCardNumberBeforeChangeToNext() {
            }

            override fun onInactiveCardNumberBeforeChangeToPrevious() {
            }

        })

    }
    private fun credictcardToken(){
        val expered=cardExpired.split("/").toTypedArray()
        val month=expered[0]
        val years="20${expered[1]}"
        val holdercard=Cardholder(name = cardHolder)
        cardNumber=cardNumber.replace(" ", "" )
        val mercadotokenBody=MercadoPagoCardTokenBody(
            securityCode = cvv,
            expirationYear = years,
            expirationMonth = month.toInt(),
            cardNumber=cardNumber,
            cardHolder = holdercard
        )
        mercadooagoPorvider.createCardTOken(mercadotokenBody)?.enqueue(object:Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.body() != null){
                    val cardToken=response.body()?.get("id")?.asString
                    val firtsDigil=response.body()?.get("first_six_digits")?.asString
                    val navegacion=FragmentPagosDirections.actionFragmentPagosToFragmentCuotaspago(args.totalapagar, firtsDigil.toString(), cardToken.toString())
                    findNavController().navigate(navegacion)
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Toast.makeText(context, "error al procesar${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }
    private fun onBack(){
        binding.credictCard.previousState()
    }
}
