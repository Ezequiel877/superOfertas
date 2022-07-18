package com.example.kampasmobil2.Core

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.kampasmobil2.DataSource.Orden

fun ImageView.Load(
    url: String,
) {

        Glide.with(this.context).load(url).into(this)


}
fun ToObjetc():Orden{
        return  Orden()
}
