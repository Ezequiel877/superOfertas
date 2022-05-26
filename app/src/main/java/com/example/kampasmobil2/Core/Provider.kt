package com.example.kampasmobil2.Core

import com.google.firebase.firestore.FirebaseFirestore

class Provider {
    val data=FirebaseFirestore.getInstance().collection("comercios")
}