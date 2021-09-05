package com.example.kampasmobil2.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.kampasmobil2.Core.Result
import com.example.kampasmobil2.Data.Home.Home.iu.usuario.usuarioRegister
import com.example.kampasmobil2.Data.Home.Home.iu.usuario.usuariosInte
import kotlinx.coroutines.Dispatchers

class modelLoginSetup(private val loginrepo:usuariosInte):ViewModel() {
    fun singip(email: String, password: String)=liveData(Dispatchers.IO){
        emit(Result.Loading())
        try {
            emit(Result.Succes(loginrepo.singUp(email, password)))
        }catch (e:Exception){
            emit(Result.Failure(e))
        }
    }


class repo(private val loginRepo: usuariosInte) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelLoginSetup(loginRepo) as T
    }

}}