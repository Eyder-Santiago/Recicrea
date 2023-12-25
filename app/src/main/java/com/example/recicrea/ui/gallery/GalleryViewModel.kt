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
            1 -> "plástico, juguete"
            2 -> "cartón, decoración"
            3 -> "aluminio, adorno"
            4 -> "plástico, guardaútiles"
            5 -> "plástico-tela, decoración"
            6 -> "plástico, adorno"
            7 -> "aluminio-plástico, juguete"
            8 -> "aluminio, juguete"
            9 -> "plástico, adorno"
            10 -> "plástico, decoración"
            11 -> "plástico-tela, guardaobjetos"
            12 -> "plástico, juego de mesa"
            13 -> "plástico, juego de mesa"
            14 -> "Cartón, decoración, guardaobjetos"

            // Puedes agregar más casos según sea necesario para los otros índices
            else -> "mixto"
        }
    }




}

