package com.ubaya.a160419129_uts_library.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.a160419129_uts_library.model.Library
import java.util.ArrayList

class DetailViewModel(application: Application): AndroidViewModel(application) {
    val libraryDetailLD = MutableLiveData<Library>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(id:String){
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://gist.githubusercontent.com/jerrielrhemaldy/6a132ef38b976d88ac8c000703d1467d/raw/e86ae4c3e3b2d3cacc41efce9a17040f678e8af3/UbayaLibrary.json"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Library>>() { }.type
                val result = Gson().fromJson<ArrayList<Library>>(it, sType)
                for (item in result){
                    if (id == item.id){
                        libraryDetailLD.value = item
                    }
                }
                Log.d("showvoley", it)
            },
            {
                Log.d("showvoley", it.toString())
            }).apply {
            tag = "TAG"
        }
        queue?.add(stringRequest)
    }
}