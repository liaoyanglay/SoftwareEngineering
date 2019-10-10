package com.example.bookmanagementsystem.ui.book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookmanagementsystem.data.model.Book

class BookViewModel : ViewModel() {

    private val _allBooks = MutableLiveData<ArrayList<Book>>().apply {
        val list = arrayListOf<Book>()
        for (i in 0..10) {
            list.add(
                Book(
                    0,
                    "aaa",
                    "",
                    "bbb",
                    "ccc",
                    true,
                    "人民出版社",
                    "2019-10-10",
                    "99.0",
                    8.8f,
                    "999999999999",
                    "这是描述。。。。。。"
                )
            )
        }
        value = list
    }
    val allBooks: LiveData<ArrayList<Book>> = _allBooks

}