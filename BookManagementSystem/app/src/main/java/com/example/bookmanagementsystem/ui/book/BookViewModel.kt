package com.example.bookmanagementsystem.ui.book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookmanagementsystem.data.model.Book
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.example.bookmanagementsystem.data.NetConfig
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import com.google.gson.reflect.TypeToken
import android.icu.lang.UCharacter.GraphemeClusterBreak.T


class BookViewModel : ViewModel() {

    private val _allBooks = MutableLiveData<ArrayList<Book>>().apply {
        //        val list = arrayListOf<Book>()
//        for (i in 0..10) {
//            list.add(
//                Book(
//                    0,
//                    "aaa",
//                    "",
//                    "bbb",
//                    "ccc",
//                    true,
//                    "人民出版社",
//                    "2019-10-10",
//                    "99.0",
//                    8.8f,
//                    "999999999999",
//                    "这是描述。。。。。。"
//                )
//            )
//        }
//        value = list
    }
    val allBooks: LiveData<ArrayList<Book>> = _allBooks

    fun fetchBooks() {
        val client = NetConfig.client
        val request = Request.Builder()
            .get()
            .url(NetConfig.BASE_URL + NetConfig.ALL_BOOK)
            .build()
        val call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                _allBooks.postValue(arrayListOf())
            }

            override fun onResponse(call: Call, response: Response) {
                val json = response.body()!!.string()
                val bookType = object : TypeToken<ArrayList<Book>>() {}.type
                val books: ArrayList<Book> = Gson().fromJson(json, bookType)
                _allBooks.postValue(books)
            }
        })
    }

    fun fetchBooks(name: String) {
        val url = "${NetConfig.BASE_URL}${NetConfig.QUERY_BOOK}?name=$name"
        val client = NetConfig.client
        val request = Request.Builder()
            .get()
            .url(url)
            .build()
        val call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                _allBooks.postValue(arrayListOf())
            }

            override fun onResponse(call: Call, response: Response) {
                val json = response.body()!!.string()
                val bookType = object : TypeToken<ArrayList<Book>>() {}.type
                val books: ArrayList<Book> = Gson().fromJson(json, bookType)
                _allBooks.postValue(books)
            }
        })
    }
}