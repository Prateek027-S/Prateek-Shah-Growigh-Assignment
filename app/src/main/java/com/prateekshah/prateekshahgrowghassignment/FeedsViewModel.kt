package com.prateekshah.prateekshahgrowghassignment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prateekshah.prateekshahgrowghassignment.network.ImageResponse
import com.prateekshah.prateekshahgrowghassignment.network.ImageApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

enum class ImageApiStatus{ LOADING, ERROR, DONE }

class FeedsViewModel: ViewModel() {
    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<ImageApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<ImageApiStatus> = _status

    private val _photos = MutableLiveData<List<ImageResponse>>()
    val photos: LiveData<List<ImageResponse>> = _photos

    var pageNum: Int = 1
    init {
        getTenPhotos()
    }

    fun getTenPhotos() {
        viewModelScope.launch { // A ViewModelScope is the built-in coroutine scope defined for each ViewModel in your app. Any coroutine launched in this scope is automatically canceled if the ViewModel is cleared.
            _status.value = ImageApiStatus.LOADING
            try {
                pageNum = (1..50).random()
                _photos.value = ImageApi.retrofitService.getPhotos(pageNum, 10)
                _status.value = ImageApiStatus.DONE
                Log.d("getTenPhotos", "Successful: ${_photos.value.toString()}")
            } catch (e: Exception){
                _status.value = ImageApiStatus.ERROR
                _photos.value = listOf()
                Log.d("getTenPhotos", "Error, $e")
            }
        }

    }
}