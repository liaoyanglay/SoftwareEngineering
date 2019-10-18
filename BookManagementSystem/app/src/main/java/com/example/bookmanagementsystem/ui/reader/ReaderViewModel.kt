package com.example.bookmanagementsystem.ui.reader

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookmanagementsystem.data.NetConfig
import com.example.bookmanagementsystem.data.model.Reader
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class ReaderViewModel : ViewModel() {

    private val _allReaders = MutableLiveData<ArrayList<Reader>>()

    val allReaders = _allReaders as LiveData<ArrayList<Reader>>

    // 发送获取读者的网络请求
    fun fetchReaders() {
        val client = NetConfig.client
        val request = Request.Builder()
            .get()
            .url(NetConfig.BASE_URL + NetConfig.ALL_READERS)
            .build()
        val call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                _allReaders.postValue(arrayListOf())
            }

            override fun onResponse(call: Call, response: Response) {
                val json = response.body()!!.string()
                val readerType = object : TypeToken<ArrayList<Reader>>() {}.type
                val readers: ArrayList<Reader> = Gson().fromJson(json, readerType)
                _allReaders.postValue(readers)
            }
        })
    }
}