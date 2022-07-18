package com.example.kampasmobil2.UI.Carrrito.Orden

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kampasmobil2.Core.firabese.OrdenFire
import com.example.kampasmobil2.Data.Home.Home.iu.StatusSeguimiento
import com.example.kampasmobil2.R
import com.example.kampasmobil2.databinding.FragmentSeguimientoBinding

class FragmentSeguimiento : Fragment(R.layout.fragment_seguimiento) {

    private lateinit var binding:FragmentSeguimientoBinding
    private var orden:OrdenFire= OrdenFire()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentSeguimientoBinding.bind(view)
         getStatus()
    }

    private fun getStatus() {

    }

    private fun getOrdenStatus() {
        binding.let {
            it.progresIndicator.progress=orden.status * (100/3) -15
            it.check1.isChecked=orden.status > 0
            it.check2.isChecked=orden.status > 1
            it.check3.isChecked=orden.status > 2
            it.check4.isChecked=orden.status > 3
        }
    }

}