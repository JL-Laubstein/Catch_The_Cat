package com.example.catchthecat.ui.imagedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.catchthecat.data.model.ImageData
import com.example.catchthecat.databinding.FragmentImageDetailBinding

class ImageDetailFragment(private val imageData: ImageData?) : DialogFragment() {

    private val binding: FragmentImageDetailBinding by lazy {
        FragmentImageDetailBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.imageData = imageData

        return binding.root
    }
}