package com.ubaya.a160419129_uts_library.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.a160419129_uts_library.model.Library
import java.util.ArrayList

class ListViewModel(application: Application): AndroidViewModel(application) {
    val libraryLD = MutableLiveData<List<Library>>()
    val libraryLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue:RequestQueue? = null
    fun refresh(){
        libraryLoadErrorLD.value = false
        loadingLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://gist.githubusercontent.com/jerrielrhemaldy/6a132ef38b976d88ac8c000703d1467d/raw/e86ae4c3e3b2d3cacc41efce9a17040f678e8af3/UbayaLibrary.json"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Library>>() { }.type
                val result = Gson().fromJson<ArrayList<Library>>(it, sType)
                libraryLD.value = result
                loadingLD.value = false
                Log.d("showvoley", it)
            },
            {
                Log.d("showvoley", it.toString())
                libraryLoadErrorLD.value = false
                loadingLD.value = false
            }).apply {
                tag = "TAG"
        }
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}