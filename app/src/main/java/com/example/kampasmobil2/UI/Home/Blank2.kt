package com.example.kampasmobil2.UI.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kampasmobil2.R
import com.example.kampasmobil2.databinding.FragmentBlank2Binding

class Blank2 : Fragment(R.layout.fragment_blank2) {
    private lateinit var binding:FragmentBlank2Binding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentBlank2Binding.bind(view)
    }}