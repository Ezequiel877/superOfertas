package com.example.kampasmobil2.UI.Carrrito

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kampasmobil2.R
import com.example.kampasmobil2.databinding.FragmentPagosBinding
import io.stormotion.creditcardflow.CreditCard
import io.stormotion.creditcardflow.CreditCardFlowListener

class FragmentPagos : Fragment(R.layout.fragment_pagos) {

    private lateinit var binding:FragmentPagosBinding

    var cvv=""
    var cardHolder=""
    var cardNumber=""
    var cardExpired=""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentPagosBinding.bind(view)
        binding.credictCard.setCreditCardFlowListener(object :CreditCardFlowListener{
            override fun onActiveCardNumberBeforeChangeToNext() {
                TODO("Not yet implemented")
            }

            override fun onActiveCardNumberBeforeChangeToPrevious() {
                TODO("Not yet implemented")
            }

            override fun onCardCvvBeforeChangeToNext() {
                TODO("Not yet implemented")
            }

            override fun onCardCvvBeforeChangeToPrevious() {
                TODO("Not yet implemented")
            }

            override fun onCardCvvValidatedSuccessfully(cvv: String) {
                TODO("Not yet implemented")
            }

            override fun onCardCvvValidationFailed(cvv: String) {
                TODO("Not yet implemented")
            }

            override fun onCardExpiryDateBeforeChangeToNext() {
                TODO("Not yet implemented")
            }

            override fun onCardExpiryDateBeforeChangeToPrevious() {
                TODO("Not yet implemented")
            }

            override fun onCardExpiryDateInThePast(expiryDate: String) {
                TODO("Not yet implemented")
            }

            override fun onCardExpiryDateValidatedSuccessfully(expiryDate: String) {
                TODO("Not yet implemented")
            }

            override fun onCardExpiryDateValidationFailed(expiryDate: String) {
                TODO("Not yet implemented")
            }

            override fun onCardHolderBeforeChangeToNext() {
                TODO("Not yet implemented")
            }

            override fun onCardHolderBeforeChangeToPrevious() {
                TODO("Not yet implemented")
            }

            override fun onCardHolderValidatedSuccessfully(cardHolder: String) {
                TODO("Not yet implemented")
            }

            override fun onCardHolderValidationFailed(cardholder: String) {
                TODO("Not yet implemented")
            }

            override fun onCardNumberValidatedSuccessfully(cardNumber: String) {
                TODO("Not yet implemented")
            }

            override fun onCardNumberValidationFailed(cardNumber: String) {
                TODO("Not yet implemented")
            }

            override fun onCreditCardFlowFinished(creditCard: CreditCard) {
                cvv=creditCard.cvc.toString()
                cardHolder=creditCard.holderName.toString()
                cardNumber=creditCard.number.toString()
                cardExpired=creditCard.expiryDate.toString()
            }

            override fun onFromActiveToInactiveAnimationStart() {
                TODO("Not yet implemented")
            }

            override fun onFromInactiveToActiveAnimationStart() {
                TODO("Not yet implemented")
            }

            override fun onInactiveCardNumberBeforeChangeToNext() {
                TODO("Not yet implemented")
            }

            override fun onInactiveCardNumberBeforeChangeToPrevious() {
                TODO("Not yet implemented")
            }

        })
    }
}
