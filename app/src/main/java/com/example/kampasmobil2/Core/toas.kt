package com.example.kampasmobil2.Core

import android.content.Context
import android.widget.Toast

class toas {
    fun Context.toas(messege:String , leng:Int =Toast.LENGTH_LONG){
        Toast.makeText(this,messege, leng ).show()
    }
}