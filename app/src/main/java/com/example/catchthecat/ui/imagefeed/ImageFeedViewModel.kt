package com.example.catchthecat.ui.imagefeed

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.catchthecat.data.api.ImgurApi
import com.example.catchthecat.data.db.dao.ImageFeedDao
import com.example.catchthecat.data.model.GetImageData
import com.example.catchthecat.data.model.ImageData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG = "ImageFeedViewModel"
class ImageFeedViewModel(private val imageFeedDao: ImageFeedDao) : ViewModel() {
    val imageList = MutableLiveData<List<ImageData>>()

    fun getImagesFromApi(query: String) {
        viewModelScope.launch {
            try {
                ImgurApi.retrofitService.getImages(query).enqueue(object :
                    Callback<GetImageData> {
                    override fun onResponse(
                        call: Call<GetImageData>,
                        response: Response<GetImageData>
                    ) {
                        if (response.body() != null) {
                            deleteImages()
                            imageList.postValue(response.body()?.data)

                            Log.i(TAG, "Success: ${response.body()?.success}")
                            Log.i(TAG, "Status: ${response.body()?.status}")
                        } else {
                            Log.i(TAG, "Failure: response is null!")
                        }
                    }

                    override fun onFailure(call: Call<GetImageData>, t: Throwable) {
                        Log.i(TAG, "Cause: ${t.cause}")
                        Log.i(TAG, "Message: ${t.message}")

                        // If it fails, load the images from the database
                        getImagesFromStorage()
                    }
                })
            } catch (e: Exception) {
                Log.e(TAG, "Failure: ${e.message}")
            }
        }
    }

    fun getImagesFromStorage() {
        viewModelScope.launch {
            imageFeedDao.getAll().collect {
                imageList.postValue(it)
            }
        }
    }

    fun setImages(imageData: List<ImageData>) {
        viewModelScope.launch(Dispatchers.IO) {
            imageFeedDao.insertAll(*imageData.toTypedArray())
        }
    }

    fun deleteImages() {
        viewModelScope.launch(Dispatchers.IO) {
            imageFeedDao.deleteTokens()
        }
    }
}

class ImageFeedViewModelFactory(
    private val imageFeedDao: ImageFeedDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ImageFeedViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ImageFeedViewModel(imageFeedDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}