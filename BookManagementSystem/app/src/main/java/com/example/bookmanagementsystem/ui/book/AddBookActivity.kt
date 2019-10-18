package com.example.bookmanagementsystem.ui.book

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bookmanagementsystem.R
import com.example.bookmanagementsystem.data.NetConfig
import com.example.bookmanagementsystem.data.model.Book
import com.example.bookmanagementsystem.data.model.State
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_add_book.*
import okhttp3.*
import java.io.IOException

class AddBookActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)
        back_button.setOnClickListener {
            onBackPressed()
        }
        add_button.setOnClickListener {
            if (name_edit.text.isBlank()) {
                showToast("书名不能为空")
                return@setOnClickListener
            }
            val book = Book()
            book.name = name_edit.text.toString()
            book.cover = cover_edit.text.toString()
            book.author = author_edit.text.toString()
            book.clazz = clazz_edit.text.toString()
            book.publisher = publisher_edit.text.toString()
            book.publishDate = publish_date_edit.text.toString()
            book.price = price_edit.text.toString()
            book.isbn = isbn_edit.text.toString()
            book.description = content_edit.text.toString()
            addBook(book)
        }
    }

    private fun addBook(book: Book) {
        val client = NetConfig.client
        val formBody = FormBody.Builder()
            .add("name", book.name)
            .add("cover", book.cover)
            .add("author", book.author)
            .add("clazz", book.clazz)
            .add("publisher", book.publisher)
            .add("publishDate", book.publishDate)
            .add("price", book.price)
            .add("rate", book.rate.toString())
            .add("isbn", book.isbn)
            .add("description", book.description)
            .build()
        val request = Request.Builder().post(formBody)
            .url(NetConfig.BASE_URL + NetConfig.ADD_BOOK)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                showToast("添加失败")
            }

            override fun onResponse(call: Call, response: Response) {
                val json = response.body()!!.string()
                val state: State = Gson().fromJson(json, State::class.java)
                if (state.state == State.OK) {
                    showToast("添加成功")
                    finish()
                } else {
                    showToast("添加失败")
                }
            }
        })
    }

    private fun showToast(msg: String) {
        runOnUiThread {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }
}
