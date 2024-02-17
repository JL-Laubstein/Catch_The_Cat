package com.example.catchthecat.ui.imagefeed

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catchthecat.data.api.ReceiptApi
import com.example.catchthecat.data.model.GetImageData
import com.example.catchthecat.data.model.ImageData
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageFeedViewModel : ViewModel() {
    val imageList = MutableLiveData<List<ImageData>>()

    fun getCatImages(auth: String, query: String) {
        viewModelScope.launch {
            try {
                ReceiptApi.retrofitService.getImages(auth, query).enqueue(object :
                    Callback<GetImageData> {
                    override fun onResponse(
                        call: Call<GetImageData>,
                        response: Response<GetImageData>
                    ) {
                        if (response.body() != null) {
                            imageList.postValue(response.body()?.data)

                            Log.i("JAO", "Success: ${response.body()?.success}")
                            Log.i("JAO", "Status: ${response.body()?.status}")
                        } else {
                            Log.i("JAO", "Failure: response is null!")
                        }
                    }

                    override fun onFailure(call: Call<GetImageData>, t: Throwable) {
                        Log.i("JAO", "Cause: ${t.cause}")
                        Log.i("JAO", "Message: ${t.message}")
                    }
                })
            } catch (e: Exception) {
                Log.e("JAO", "Failure: ${e.message}")
            }
        }
    }
}