package com.example.recicrea.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GalleryViewModel : ViewModel() {

    private val _texts = MutableLiveData<List<String>>().apply {
        value = (1..16).mapIndexed { index, _ ->
            "Material de ${getMaterialName(index + 1)}"
        }
    }

    val texts: LiveData<List<String>> = _texts

    private fun getMaterialName(index: Int): String {
        return when (index) {
            1 -> "Plástico, juguete"
            2 -> "Cartón, decoración"
            3 -> "Aluminio, adorno"
            4 -> "Plástico, guardaútiles"
            5 -> "Plástico-tela, decoración"
            6 -> "Plástico, adorno"
            7 -> "Aluminio-plástico, juguete"
            8 -> "Aluminio, juguete"
            9 -> "Plástico, adorno"
            10 -> "Plástico, decoración"
            11 -> "Plástico-tela, guardaobjetos"
            12 -> "Plástico, juego de mesa"
            13 -> "Plástico, juego de mesa"
            14 -> "Cartón, decoración, guardaobjetos"

            // Puedes agregar más casos según sea necesario para los otros índices
            else -> "mixto"
        }
    }




}

