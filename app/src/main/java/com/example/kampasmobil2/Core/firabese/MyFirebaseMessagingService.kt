package com.example.kampasmobil2.Core.firabese

import android.util.Log
import androidx.core.content.edit
import com.example.kampasmobil2.Core.constantes.constantes
import com.google.firebase.messaging.FirebaseMessagingService
import androidx.preference.PreferenceManager

class MyFirebaseMessagingService: FirebaseMessagingService() {
    override fun onNewToken(newToken: String) {
        super.onNewToken(newToken)
        usuarioId(newToken)
    }
    private fun usuarioId(newToken:String){
        val preferce= PreferenceManager.getDefaultSharedPreferences(this)
        preferce.edit {
            putString(constantes.TOKEN_ID, newToken)
                .apply()
        }

        Log.i("new token", newToken)

    }
}