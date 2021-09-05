package com.example.kampasmobil2.Core.Publicidad

import android.app.Application
import com.google.android.gms.ads.MobileAds

class Publicidad: Application() {
    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this)
    }
} //AIzaSyBJPxXxZ4tmss0F6WOrjQm9F4Nqi3ZEJ9w {
