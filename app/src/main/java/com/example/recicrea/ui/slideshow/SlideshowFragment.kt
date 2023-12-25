package com.example.recicrea.ui.slideshow

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.scale
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import android.Manifest
import com.example.recicrea.R
import com.example.recicrea.databinding.FragmentSlideshowBinding
import java.io.File
import java.io.FileOutputStream
import android.app.AlertDialog
import kotlin.random.Random


class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null
    private lateinit var slideshowViewModel: SlideshowViewModel
    private val IMAGE_CAPTURE_CODE = 1001

    private val binding get() = _binding!!
    var fileName = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        slideshowViewModel = ViewModelProvider(this).get(SlideshowViewModel::class.java)

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_CODE)
        }
        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        _binding?.btnCaptura?.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            fileName = String.format("%d.jpg", System.currentTimeMillis())
            startForResult.launch(cameraIntent)
        }

        _binding?.btnGuardarCaptura?.setOnClickListener {
            val drawable = _binding?.imgCapturada?.drawable;
            val bitmap = drawable?.toBitmap(drawable.intrinsicHeight, drawable.intrinsicWidth)
            var outStream: FileOutputStream? = null

            val dir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Recicrea")
            dir.mkdirs()
            val outFile = File(dir, fileName)
            outStream = FileOutputStream(outFile)

            bitmap?.compress(Bitmap.CompressFormat.PNG, 100, outStream)
            outStream.flush()
            outStream.close()
            Toast.makeText(activity, "Archivo guardado exitosamente", Toast.LENGTH_LONG).show()

            val materials = arrayOf("Aluminio", "Metal", "Vidrio", "Cartón")
            val randomMaterial = materials[Random.nextInt(materials.size)]
            val randomPercentage = Random.nextInt(100)

            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Porcentaje de Material")
            builder.setMessage("El material es $randomMaterial y el porcentaje es $randomPercentage%")
            builder.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }


            builder.show()
        }

        return root
    }



    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireActivity(), "Permiso de cámara concedido", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireActivity(), "Permiso de cámara denegado", Toast.LENGTH_LONG).show()
            }
        }
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                val imageBitmap = intent?.extras?.get("data") as Bitmap
                val imageView = view?.findViewById<ImageView>(R.id.imgCapturada)
                imageView?.setImageBitmap(imageBitmap)
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun startCamera() {
        // TODO: Initialize and start the camera here
        // You can use CameraX or Camera2 API to manage the camera

    }

    companion object {
        const val CAMERA_PERMISSION_CODE = 100
    }
}
