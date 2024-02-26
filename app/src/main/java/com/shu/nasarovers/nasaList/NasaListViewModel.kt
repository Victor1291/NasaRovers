package com.shu.nasarovers.nasaList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shu.nasarovers.models.Photos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NasaListViewModel private constructor(
    private val repository: NasaListRepository
) : ViewModel() {
    constructor() : this(NasaListRepository())

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    //val filterEnabled = MutableStateFlow(false)

    private val _photos = MutableStateFlow<List<List<Photos>>>(emptyList())
    val photos=  _photos.asStateFlow()

    init {
        loadNasa()
    }

    private fun loadNasa() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                _isLoading.value = true
                repository.getNasaPhotos(1, "perseverance")
            }.fold(
                onSuccess = {
                    _photos.value = it
                    Log.d("errNo", it.size.toString() ?: "no data")
                            },
                onFailure = { Log.d("err", it.message ?: "") }
            )
            _isLoading.value = false
        }
    }

    fun refresh() {
        loadNasa()
    }


}
