package com.example.bookmanagementsystem.ui.record

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookmanagementsystem.data.NetConfig
import com.example.bookmanagementsystem.data.model.Record
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class RecordViewModel : ViewModel() {

    private val _allRecords = MutableLiveData<ArrayList<Record>>()

    val allRecords = _allRecords as LiveData<ArrayList<Record>>

    // 发送获取借阅记录的请求
    fun fetchRecords() {
        val client = NetConfig.client
        val request = Request.Builder()
            .get()
            .url(NetConfig.BASE_URL + NetConfig.ALL_RECORDS)
            .build()
        val call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                _allRecords.postValue(arrayListOf())
            }

            override fun onResponse(call: Call, response: Response) {
                val json = response.body()!!.string()
                val readerType = object : TypeToken<ArrayList<Record>>() {}.type
                val readers: ArrayList<Record> = Gson().fromJson(json, readerType)
                _allRecords.postValue(readers)
            }
        })
    }
}