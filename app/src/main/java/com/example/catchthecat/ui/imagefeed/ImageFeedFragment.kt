package com.example.catchthecat.ui.imagefeed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.catchthecat.application.AppApplication
import com.example.catchthecat.databinding.FragmentImageFeedBinding
import com.example.catchthecat.ui.adapter.ImageFeedAdapter
import com.example.catchthecat.ui.adapter.ImageListener
import com.example.catchthecat.ui.imagedetail.ImageDetailFragment
import com.example.catchthecat.util.Utils

private const val FETCH_THE_CAT = "cat"
class ImageFeedFragment : Fragment() {

    companion object {
        fun newInstance() = ImageFeedFragment()
    }

    private val viewModel: ImageFeedViewModel by activityViewModels {
        ImageFeedViewModelFactory(
            (requireActivity().application as AppApplication).database.imageFeedDao()
        )
    }

    private val binding: FragmentImageFeedBinding by lazy {
        FragmentImageFeedBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.imageList.observe(viewLifecycleOwner) {
            viewModel.setImages(it)

            val imageFeedAdapter = ImageFeedAdapter(ImageListener(
                clickListener = { imageData ->
                    val imageDetailFragment = ImageDetailFragment(imageData)
                    imageDetailFragment.show(requireActivity().supportFragmentManager, "detail")
                }
            ))

            binding.feedImagesRecyclerview.adapter = imageFeedAdapter

            imageFeedAdapter.submitList(it)
        }

        binding.btSearch.setOnClickListener {
            if (Utils.isNetworkAvailable(requireContext())) {
                viewModel.getImagesFromApi(binding.etQuery.text.toString())
            } else {
                Toast.makeText(
                    requireContext(),
                    "The device does not have an internet connection.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        viewModel.getImagesFromApi(FETCH_THE_CAT)

        return binding.root
    }
}