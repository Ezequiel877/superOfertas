package com.example.kampasmobil2.model

import android.media.Session2Command
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.kampasmobil2.Core.Result
import com.example.kampasmobil2.Data.Home.Home.iu.usuario.usuarioRegister
import com.example.kampasmobil2.Data.Home.Home.iu.usuario.usuariosInte
import kotlinx.coroutines.Dispatchers

class modelRegistro(private val loginRepo:usuarioRegister ):ViewModel() {
    fun singip(email: String, password: String, cliente:String, )= liveData(Dispatchers.IO){
        emit(Result.Loading())
        try {
            emit(Result.Succes(loginRepo.singUp(email, password, cliente)))
        }catch (e:Exception){
            emit(Result.Failure(e))
        }
    }
    fun getDireccion(direccion:String, ubicacion:String, cliente:String)= liveData(Dispatchers.IO){
        emit(Result.Loading())
        try {
            emit(Result.Succes(loginRepo.direccion( direccion,ubicacion, cliente)))
        }catch (e:Exception){
            emit(Result.Failure(e))
        }
    }
}


class UthRegistro(private val loginRepo: usuarioRegister) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelRegistro(loginRepo) as T
    }

}
