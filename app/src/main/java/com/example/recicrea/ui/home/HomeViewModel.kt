package com.example.recicrea.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "¡Bienvenidos a la App Recicrea!, una segunda vida a tu creatividad"
    }
    val text: LiveData<String> = _text
}