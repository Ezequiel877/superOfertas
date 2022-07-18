package com.example.kampasmobil2.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.kampasmobil2.Core.Result
import com.example.kampasmobil2.Data.Home.Home.iu.HomeInt
import kotlinx.coroutines.Dispatchers

class viewModelPost (private val repo: HomeInt) :ViewModel(){
    fun getLeterOfert() = liveData(Dispatchers.IO) {
        emit(Result.Loading())
        try {
            emit(repo.getClientes())
        } catch (e: Exception) {
            emit(Result.Failure(e))
        }
    }
    fun favoristos() = liveData(Dispatchers.IO) {
        emit(Result.Loading())
        try {
            emit(repo.favoritos())
        } catch (e: Exception) {
            emit(Result.Failure(e))
        }
    }
    fun getOfertas(id: String) = liveData(Dispatchers.IO) {
        emit(Result.Loading())
        try {
            Log.d("FIREBASEPOSTVIEWMODEL", "getOfertas: $$id")
              emit(repo.getOfertas(id))
        } catch (e: Exception) {
            emit(Result.Failure(e))
        }
    }

    fun getDireccion(id: String) = liveData(Dispatchers.IO) {
        emit(Result.Loading())
        try {
            Log.d("FIREBASEPOSTVIEWMODEL", "getOfertas: $$id")
            emit(repo.getDireccion(id))
        } catch (e: Exception) {
            emit(Result.Failure(e))
        }
    }
}

    class HomeScreemFactory(private val repo: HomeInt) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(HomeInt::class.java).newInstance(repo)
        }
    }