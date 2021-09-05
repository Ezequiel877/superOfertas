package com.example.kampasmobil2.Core

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.kampasmobil2.DataSource.DataSource
import com.example.kampasmobil2.R

fun ImageView.Load(
    url: String,
) {

        Glide.with(this.context).load(url).into(this)


}
fun ToObjetc():DataSource{
        return  DataSource()
}
