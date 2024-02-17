package com.example.catchthecat.ui.imagefeed

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catchthecat.R
import com.example.catchthecat.databinding.FragmentImageFeedBinding
import com.example.catchthecat.ui.adapter.ImageFeedAdapter
import com.example.catchthecat.ui.adapter.ImageListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ImageFeedFragment : Fragment() {

    companion object {
        fun newInstance() = ImageFeedFragment()
    }

    private val viewModel: ImageFeedViewModel by viewModel()

    private val binding: FragmentImageFeedBinding by lazy {
        FragmentImageFeedBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.imageList.observe(viewLifecycleOwner) {
            val imageFeedAdapter = ImageFeedAdapter(ImageListener(
                clickListener = { imageData ->
                    // TODO
                }
            ))

            binding.feedImagesRecyclerview.adapter = imageFeedAdapter

            Log.i("JAO", "list: $it")
            imageFeedAdapter.submitList(it)
        }

        binding.btSearch.setOnClickListener {
            viewModel.getCatImages("Client-ID 1ceddedc03a5d71", binding.etQuery.text.toString())
        }

        viewModel.getCatImages("Client-ID 1ceddedc03a5d71", "cat")

        return binding.root
    }
}