package com.example.bookmanagementsystem.ui.book

import android.annotation.SuppressLint
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.example.bookmanagementsystem.R
import com.example.bookmanagementsystem.data.NetConfig
import com.example.bookmanagementsystem.data.model.Book
import com.example.bookmanagementsystem.data.model.State
import com.example.bookmanagementsystem.utils.UIUtils
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_book.*
import kotlinx.android.synthetic.main.dialog_edit_book.view.*
import okhttp3.*
import java.io.IOException
import java.lang.Exception

class BookInfoActivity : AppCompatActivity() {
    companion object {
        const val BOOK_DATA = "BOOK"
    }

    private lateinit var book: Book

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)
        book = intent.getSerializableExtra(BOOK_DATA) as Book
        initView()
        initEvent()
    }

    private fun initView() {
        setBook(book)
        toolbar.inflateMenu(R.menu.edit_book_menu)
        Glide.with(cover_image).load(book.cover).into(cover_image)
    }

    private fun initEvent() {
        back_button.setOnClickListener {
            onBackPressed()
        }
        toolbar.menu.findItem(R.id.edit_book)
            .setOnMenuItemClickListener { onOptionsItemSelected(it) }
        toolbar.menu.findItem(R.id.delete_book)
            .setOnMenuItemClickListener { onOptionsItemSelected(it) }
        rend_button.setOnClickListener {
            if (!book.rent) {
                val editLayout = layoutInflater.inflate(R.layout.dialog_reader_id, null, false)
                val editText = editLayout.findViewById<EditText>(R.id.reader_id_edit)
                AlertDialog.Builder(this)
                    .setView(editLayout)
                    .setCancelable(true)
                    .setMessage("请输入读者ID：")
                    .setPositiveButton("确定") { _, _ ->
                        if (editText.text.isBlank()) {
                            showToast("ID不能为空")
                            return@setPositiveButton
                        }
                        val formBody = FormBody.Builder()
                            .add("bookId", book.id.toString())
                            .add("readerId", editText.text.toString())
                            .build()
                        val request = Request.Builder().post(formBody)
                            .url(NetConfig.BASE_URL + NetConfig.LEND_BOOK)
                            .build()
                        NetConfig.client.newCall(request).enqueue(object : Callback {
                            override fun onFailure(call: Call, e: IOException) {
                                showToast("借阅失败")
                            }

                            override fun onResponse(call: Call, response: Response) {
                                val json = response.body()!!.string()
                                val state: State = Gson().fromJson(json, State::class.java)
                                if (state.state == State.OK) {
                                    showToast("借阅成功")
                                    runOnUiThread {
                                        book.rent = true
                                        setBook(book)
                                    }
                                } else {
                                    showToast("借阅失败")
                                }
                            }
                        })
                    }
                    .show()
            } else {
                val formBody = FormBody.Builder()
                    .add("bookId", book.id.toString())
                    .build()
                val request = Request.Builder().post(formBody)
                    .url(NetConfig.BASE_URL + NetConfig.RETURN_BOOK)
                    .build()
                NetConfig.client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        showToast("归还失败")
                    }

                    override fun onResponse(call: Call, response: Response) {
                        val json = response.body()!!.string()
                        val state: State = Gson().fromJson(json, State::class.java)
                        if (state.state == State.OK) {
                            showToast("归还成功")
                            runOnUiThread {
                                book.rent = false
                                setBook(book)
                            }
                        } else {
                            showToast("归还失败")
                        }
                    }
                })
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.edit_book -> {
                val view = LayoutInflater.from(this).inflate(R.layout.dialog_edit_book, null, false)
                val editDialog = Dialog(this, R.style.CustomDimDialog)
                editDialog.setContentView(view)
                editDialog.setCanceledOnTouchOutside(false)
                editDialog.setCancelable(true)
                val params = editDialog.window!!.attributes
                params.gravity = Gravity.CENTER
                params.width = UIUtils.dp2px(350F).toInt()
                params.height = UIUtils.dp2px(500F).toInt()
                editDialog.window!!.attributes = params
                editDialog.show()
                with(view) {
                    name_edit.setText(this@BookInfoActivity.book.name)
                    cover_edit.setText(this@BookInfoActivity.book.cover)
                    author_edit.setText(this@BookInfoActivity.book.author)
                    clazz_edit.setText(this@BookInfoActivity.book.clazz)
                    publisher_edit.setText(this@BookInfoActivity.book.publisher)
                    publish_date_edit.setText(this@BookInfoActivity.book.publishDate)
                    price_edit.setText(this@BookInfoActivity.book.price)
                    isbn_edit.setText(this@BookInfoActivity.book.isbn)
                    content_edit.setText(this@BookInfoActivity.book.description)
                    cancel_button.setOnClickListener {
                        editDialog.dismiss()
                    }
                    edit_button.setOnClickListener {
                        if (name_edit.text.isBlank()) {
                            showToast("书名不能为空")
                            return@setOnClickListener
                        }
                        val book = Book()
                        book.id = this@BookInfoActivity.book.id
                        book.name = name_edit.text.toString()
                        book.cover = cover_edit.text.toString()
                        book.author = author_edit.text.toString()
                        book.clazz = clazz_edit.text.toString()
                        book.rent = this@BookInfoActivity.book.rent
                        book.publisher = publisher_edit.text.toString()
                        book.publishDate = publish_date_edit.text.toString()
                        book.price = price_edit.text.toString()
                        book.rate = this@BookInfoActivity.book.rate
                        book.isbn = isbn_edit.text.toString()
                        book.description = content_edit.text.toString()
                        editBook(book)
                        editDialog.dismiss()
                    }
                }
            }
            R.id.delete_book -> {
                val client = NetConfig.client
                val formBody = FormBody.Builder()
                    .add("id", book.id.toString())
                    .build()
                val request = Request.Builder().post(formBody)
                    .url(NetConfig.BASE_URL + NetConfig.DELETE_BOOK)
                    .build()
                client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        showToast("删除失败")
                    }

                    override fun onResponse(call: Call, response: Response) {
                        val json = response.body()!!.string()
                        try {
                            val state: State = Gson().fromJson(json, State::class.java)
                            if (state.state == State.OK) {
                                showToast("删除成功")
                                finish()
                            } else {
                                showToast("删除失败")
                            }
                        } catch (e: Exception) {
                            showToast("删除失败")
                        }
                    }
                })
            }
        }
        return true
    }

    private fun editBook(book: Book) {
        val client = NetConfig.client
        val formBody = FormBody.Builder()
            .add("id", book.id.toString())
            .add("name", book.name)
            .add("cover", book.cover)
            .add("author", book.author)
            .add("clazz", book.clazz)
            .add("rent", book.rent.toString())
            .add("publisher", book.publisher)
            .add("publishDate", book.publishDate)
            .add("price", book.price)
            .add("rate", book.rate.toString())
            .add("isbn", book.isbn)
            .add("description", book.description)
            .build()
        val request = Request.Builder().post(formBody)
            .url(NetConfig.BASE_URL + NetConfig.EDIT_BOOK)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                showToast("编辑失败")
            }

            override fun onResponse(call: Call, response: Response) {
                val json = response.body()!!.string()
                val state: State = Gson().fromJson(json, State::class.java)
                if (state.state == State.OK) {
                    showToast("编辑成功")
                    runOnUiThread {
                        setBook(book)
                    }
                } else {
                    showToast("编辑失败")
                }
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun setBook(book: Book) {
        this.book = book
        book_name_text.text = book.name
        author_text.text = "作者：" + book.author
        publisher_text.text = "出版社：" + book.publisher
        publish_date_text.text = "出版年份：" + book.publishDate
        price_text.text = "价格：" + book.price
        ISBN_text.text = "ISBN：" + book.isbn
        description_text.text = book.description
        rend_button.text = if (book.rent) "归 还" else "借 阅"
    }

    private fun showToast(msg: String) {
        runOnUiThread {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }
}
